package com.phantom.security.repository;

import com.phantom.security.entity.RoleAuthority;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色-权限表(RoleAuthority)表数据库访问层
 *
 * @author lei.tan
 * @since  2023-02-09 10:21:35
 */
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long>, CrudRepository<RoleAuthority, Long> {


    /**
     * 通过角色id批量查询出角色权限信息
     * @param roleIds
     * @return
     */
    List<RoleAuthority> findRoleAuthoritiesByRoleIdIn(List<Long> roleIds);

}

