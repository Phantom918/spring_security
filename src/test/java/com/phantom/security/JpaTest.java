package com.phantom.security;

import com.alibaba.fastjson2.JSON;
import com.phantom.security.repository.UserRepository;
import com.phantom.security.entity.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * TODO
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/2/9 10:53
 */
@Slf4j
@SpringBootTest
public class JpaTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Test
    public void queryUser() {
//        List<User> users = userRepository.findAll();
//        log.info(JSON.toJSONString(users));
        Optional<User> user = userRepository.findUserByUsername("phantom");
        log.info(JSON.toJSONString(user.get()));
    }

    @Test
    public void addUser() {
        User user = User.builder()
                .username("bbb")
                .email("phantom918xl@live.com")
                .phone("18175795319")
                .password(passwordEncoder.encode("password"))
                .build();
        log.info("save before: {}", JSON.toJSONString(user));
        User save = userRepository.saveAndFlush(user);
        log.info("save after: {}", JSON.toJSONString(save));
    }
}
