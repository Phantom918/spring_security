package com.phantom.security.repository;

import com.phantom.security.entity.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色(Role)表数据库访问层
 *
 * @author lei.tan
 * @since  2023-02-09 10:21:35
 */
public interface RoleRepository extends JpaRepository<Role, Long>, CrudRepository<Role, Long> {


}

