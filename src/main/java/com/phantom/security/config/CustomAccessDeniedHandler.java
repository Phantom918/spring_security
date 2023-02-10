package com.phantom.security.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求的 url 权限异常自定义处理
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/2/9 23:51
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("[请求的url授权异常]自定义处理.....");
        // 设置状态码为 401 以及请求头
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        // 组装数据返回
        Map<String, Object> result = new HashMap<>(3);
        result.put("status", HttpStatus.UNAUTHORIZED.value());
        result.put("error", accessDeniedException.getMessage());
        result.put("message", "当前接口无权限，请获取权限后重新尝试！");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
