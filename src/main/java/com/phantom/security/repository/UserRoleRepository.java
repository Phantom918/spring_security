package com.phantom.security.repository;

import com.phantom.security.entity.UserRole;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户-角色表(UserRole)表数据库访问层
 *
 * @author lei.tan
 * @since  2023-02-09 10:21:35
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long>, CrudRepository<UserRole, Long> {

    /**
     * 通过用户id查询到用户角色关联的信息
     * @param userid 用户id
     * @return
     */
    List<UserRole> findUserRolesByUserId(Long userid);
}

