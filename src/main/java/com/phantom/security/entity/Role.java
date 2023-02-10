package com.phantom.security.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色(Role)表实体类
 *
 * @author lei.tan
 * @since 2023-02-09 10:28:14
 */
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = -53836684096728668L;

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 创建人
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 创建时间
     */
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

}

