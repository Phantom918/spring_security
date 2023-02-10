package com.phantom.security.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-角色表(UserRole)表实体类
 *
 * @author lei.tan
 * @since 2023-02-09 10:28:14
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -13333856280695676L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

}

