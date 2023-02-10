package com.phantom.security;

import cn.hutool.jwt.JWTUtil;
import com.phantom.security.config.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/13 11:40
 */
@Slf4j
@SpringBootTest
public class JwtTest {



    @Test
    public void jwtTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJsb2dpblRpbWUiOjE2NzM1MTg3MTB9.z9h73KxZLTcBeagGxpfcOg5Dl31CRK1_f8RWFA1jLQwq_Zc9SjQm6WhRhJRfi2lFoIVuSVQSb--_88AFv-9ehA";
        boolean verify = JWTUtil.verify(token, CommonUtils.JWT_SIGNER);
       log.info("秘钥验证: {}", verify);
    }

}
