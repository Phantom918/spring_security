package com.phantom.security.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.phantom.security.model.CustomUserDetails;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * 自定义 token 认证过滤器
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/13 14:55
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private WhitelistUrl whitelistUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 白名单校验
        if (!requireAuthentication(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(CommonUtils.TOKEN_HEADER_KEY);
        log.info("JwtAuthenticationFilter ==> token: {}", token);
        if (StrUtil.isEmpty(token) || !StrUtil.startWith(token, CommonUtils.TOKEN_PREFIX)) {
            throw new BadCredentialsException("token认证异常: 令牌为空，请重新登录！");
        }
        token = token.substring(CommonUtils.TOKEN_PREFIX.length());
        if (!JWTUtil.verify(token, CommonUtils.JWT_SIGNER)) {
            throw new BadCredentialsException("token认证异常: 令牌无效，请重新登录！");
        }
        JWT jwt = JWTUtil.parseToken(token);
        String username = (String) jwt.getPayload().getClaim("username");
        CustomUserDetails userDetails = (CustomUserDetails) redisUtils.hget(CommonUtils.TOKEN_HASH_KEY, username);
        log.info("JwtAuthenticationFilter ==> userDetails: {}", userDetails);
        if (userDetails == null) {
            throw new BadCredentialsException("token认证异常: 令牌已过期，请重新登录！");
        }
        // 校验通过后构造一个已经授权的认证信息放进security上下文
        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        // 验证通过
        filterChain.doFilter(request, response);
    }


    /**
     * 请求的 url 是否需要认证
     *
     * @param requestUri 请求的 url
     * @return true: 需要认证  false: 不需要认证，直接放行
     */
    private boolean requireAuthentication(String requestUri) {
        boolean result = true;
        List<String> urls = whitelistUrl.getUrls();
        log.info("当前白名单有: {}", urls.toString());
        if (CollUtil.isNotEmpty(urls)) {
            for (String url : urls) {
                if (requestUri.equals(url)) {
                    log.info("当前请求[{}]属于白名单，直接放行...", requestUri);
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
