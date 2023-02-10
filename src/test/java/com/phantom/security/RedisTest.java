package com.phantom.security;

import com.alibaba.fastjson2.JSON;
import com.phantom.security.config.RedisUtils;
import com.phantom.security.entity.User;
import com.phantom.security.model.CustomUserDetails;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/12 17:19
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Resource
    private RedisUtils redisUtils;

    @Test
    public void test() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test1");
        user.setPassword("password");
        user.setNickname("测试同学");
        CustomUserDetails userDetails = new CustomUserDetails(user);
        redisUtils.set(userDetails.getUsername(), userDetails, 100);
        Object res = redisUtils.get(userDetails.getUsername());
        log.info("redisTest: {}", JSON.toJSONString(res));
    }


}
