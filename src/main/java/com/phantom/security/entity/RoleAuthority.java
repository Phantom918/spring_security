package com.phantom.security.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-权限表(RoleAuthority)表实体类
 *
 * @author lei.tan
 * @since 2023-02-09 10:28:14
 */
@Data
@Entity
@Table(name = "role_authority")
public class RoleAuthority implements Serializable {

    private static final long serialVersionUID = 310115299565665802L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Column(name = "authority_id")
    private Long authorityId;

}

