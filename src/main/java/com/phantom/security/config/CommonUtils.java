package com.phantom.security.config;

import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.nio.charset.StandardCharsets;

/**
 * 权限常量配置类
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/13 11:43
 */
public class CommonUtils {

    /**
     * JWT 秘钥key
     */
    public static final String JWT_KEY = "miyao";

    /**
     * JWT 签名
     */
    public static final JWTSigner JWT_SIGNER = JWTSignerUtil.hs512(JWT_KEY.getBytes(StandardCharsets.UTF_8));

    /**
     * token的前缀
     */
    public static final String TOKEN_HEADER_KEY = "Authorization";

    /**
     * token的前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * token 存储到redis的 hash key
     */
    public static final String TOKEN_HASH_KEY = "login_token";


}
