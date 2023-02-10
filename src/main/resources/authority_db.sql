SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
                        `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                        `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '别名',
                        `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
                        `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `user_username_uk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2096112381102595 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
SET FOREIGN_KEY_CHECKS = 1;

-- 用户表数据初始化(密码明文都是 123456)
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `email`, `phone`, `create_time`, `update_time`) VALUES (1, 'admin', '$2a$10$P7bhFo9shI7Sr3biVKB6aOMp.ZlhhU5zVv.D7L1MoAUi.xHJ7ibom', '管理员', 'test@test.com', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `email`, `phone`, `create_time`, `update_time`) VALUES (2, 'user', '$2a$10$VkMpaXsEpGs06IicbFyvyOyBdTL6KtSMujVSPmNzfiI9YryWOCnYm', '我是用户', 'user@test.com', NULL, NULL, NULL);

-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
                        `creator_id` bigint NOT NULL COMMENT '创建人',
                        `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
                        `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';
SET FOREIGN_KEY_CHECKS = 1;

-- 角色表数据初始化
INSERT INTO `role` (`id`, `name`, `creator_id`, `description`, `status`, `create_time`, `update_time`) VALUES (1, '超级管理员', 1, '系统超级管理员的角色', 'enable', '2022-06-11 21:03:26', '2022-06-11 21:03:26');


-- ----------------------------
-- 权限表
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
                             `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址',
                             `type` int NOT NULL COMMENT '权限类型 1:页面，2:按钮',
                             `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限表达式',
                             `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '后端接口访问方式',
                             `sort` int NOT NULL COMMENT '排序',
                             `parent_id` bigint NOT NULL COMMENT '父级id',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';
SET FOREIGN_KEY_CHECKS = 1;

-- 权限表数据初始化
INSERT INTO `authority` (`id`, `name`, `url`, `type`, `permission`, `method`, `sort`, `parent_id`) VALUES (1, 'test', '/test', 2, 'btn:test', 'GET', 0, 0);


-- ----------------------------
-- 用户角色关联表
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_id` bigint NOT NULL COMMENT '用户id',
                             `role_id` bigint NOT NULL COMMENT '角色id',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户-角色表';
SET FOREIGN_KEY_CHECKS = 1;

-- 用户角色关联表数据初始化
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1);


-- ----------------------------
-- 角色权限关联表
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `role_id` bigint NOT NULL COMMENT '角色id',
                                  `authority_id` bigint NOT NULL COMMENT '权限id',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色-权限表';
SET FOREIGN_KEY_CHECKS = 1;

-- 角色权限关联表数据初始化
INSERT INTO `role_authority` (`id`, `role_id`, `authority_id`) VALUES (2, 1, 1);

