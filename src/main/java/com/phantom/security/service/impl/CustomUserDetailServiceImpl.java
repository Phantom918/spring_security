package com.phantom.security.service.impl;

import com.phantom.security.entity.User;
import com.phantom.security.model.CustomUserDetails;
import com.phantom.security.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * TODO
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/11 17:36
 */
@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example<User> userExample = Example.of(User.builder().username(username).build());
        Optional<User> user = userRepository.findOne(userExample);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("用户 " + username + " 未查询到相关信息！");
        }
        return new CustomUserDetails(user.get());
    }

}
