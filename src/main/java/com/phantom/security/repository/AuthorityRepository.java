package com.phantom.security.repository;

import com.phantom.security.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 权限表(Authority)表数据库访问层
 *
 * @author lei.tan
 * @since 2023-02-09 10:21:35
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>, CrudRepository<Authority, Long> {


}

