package com.phantom.security.controller;

import cn.hutool.jwt.JWTUtil;
import com.phantom.security.config.CommonUtils;
import com.phantom.security.model.CustomUserDetails;
import com.phantom.security.model.LoginModel;
import com.phantom.security.config.RedisUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录controller
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/10 17:21
 */
@Slf4j
@RestController
public class LoginController {


    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisUtils redisUtils;


    @PostMapping("/login")
    public String login(@RequestBody LoginModel loginModel) {
        log.info("HelloController => login()....");
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginModel.getUsername(), loginModel.getPassword());
        // 开始认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        CustomUserDetails userDetail = (CustomUserDetails) authenticate.getPrincipal();
        // token放到 redis
        redisUtils.hset(CommonUtils.TOKEN_HASH_KEY, userDetail.getUsername(), userDetail, 120);

        // 消息体
        Map<String, Object> payload = new HashMap<>(3);
        payload.put("username", userDetail.getUsername());
        payload.put("loginTime", LocalTime.now());
        String token = JWTUtil.createToken(payload, CommonUtils.JWT_SIGNER);
        log.info("生成token: {}" ,token);
        return token;
    }



}
