package com.phantom.security.config;

import com.alibaba.fastjson2.JSON;
import com.phantom.security.entity.Authority;
import com.phantom.security.entity.RoleAuthority;
import com.phantom.security.entity.UserRole;
import com.phantom.security.model.CustomUserDetails;
import com.phantom.security.repository.AuthorityRepository;
import com.phantom.security.repository.RoleAuthorityRepository;
import com.phantom.security.repository.UserRoleRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 自定义授权拦截处理(登录认证通过后才有授权)
 * --- Authentication: 认证
 * --- Authorization: 授权
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/2/9 14:44
 */
@Slf4j
@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private RoleAuthorityRepository roleAuthorityRepository;

    @Resource
    private AuthorityRepository authorityRepository;


    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        AuthorizationManager.super.verify(authentication, requestAuthorizationContext);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        Object principal = authentication.get().getPrincipal();
        if (principal == null) {
            throw new AccessDeniedException("授权校验失败: 认证信息为空，请重新登录获取token！");
        }
        // 拿到之前登录认证后的用户信息
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        // 通过用户id查询用户角色信息
        List<UserRole> userRoles = userRoleRepository.findUserRolesByUserId(userDetails.getId());
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 通过角色id查询角色权限信息
        List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findRoleAuthoritiesByRoleIdIn(roleIds);
        List<Long> authorityIds = roleAuthorities.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        // 通过权限id查询权限信息
        List<Authority> authorities = authorityRepository.findAllById(authorityIds);
        log.info("当前用户权限如下: {}", JSON.toJSONString(authorities));
        // 权限比对
        HttpServletRequest request = requestAuthorizationContext.getRequest();
        // 默认没有权限
        boolean hasPermission = false;
        for (Authority authority : authorities) {
            AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(authority.getUrl());
            // 可以同时比对url的方法是否都符合
            // 啊啊AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(authority.getUrl(), authority.getMethod());啊啊
            // 授权校验成功
            if (requestMatcher.matches(request)) {
                log.info("用户[{}]授权校验通过！！！", userDetails.getUsername());
                hasPermission = true;
                break;
            }
        }
        if (hasPermission){
            return new AuthorizationDecision(true);
        }else  {
            log.info("用户[{}]授权校验失败！！！", userDetails.getUsername());
            throw new AccessDeniedException("授权校验失败: 当前请求无权访问！");
        }
    }
}
