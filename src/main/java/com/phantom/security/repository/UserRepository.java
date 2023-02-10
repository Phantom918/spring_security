package com.phantom.security.repository;

import com.phantom.security.entity.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户表(User)表数据库访问层
 *
 * @author lei.tan
 * @since  2023-02-09 10:21:35
 */
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    /**
     *根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findUserByUsername(String username);
  
}

