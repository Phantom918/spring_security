package com.phantom.security.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限表(Authority)表实体类
 *
 * @author lei.tan
 * @since 2023-02-09 10:28:14
 */
@Data
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = -91826952631191717L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限名
     */
    @Column(name = "name")
    private String name;

    /**
     * 类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 权限类型 1:页面，2:按钮
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 权限表达式
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 后端接口访问方式
     */
    @Column(name = "method")
    private String method;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 父级id
     */
    @Column(name = "parent_id")
    private Long parentId;

}

