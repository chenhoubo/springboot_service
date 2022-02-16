--用户表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10018 DEFAULT CHARSET=utf8 COMMENT='用户表';

--角色表
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10018 DEFAULT CHARSET=utf8 COMMENT='角色表';
INSERT INTO starter.`role`
(id, name, status, is_deleted, json, create_by, update_by, create_time, update_time, end_time)
VALUES(1475377063158071298, '管理员', 0, 0, '{}', 'default', 'default', 1640592277350, 1640592277350, NULL);
INSERT INTO starter.`role`
(id, name, status, is_deleted, json, create_by, update_by, create_time, update_time, end_time)
VALUES(1475377848902205441, '普通用户', 0, 0, '{}', 'default', 'default', 1640592459067, 1640592459067, NULL);
INSERT INTO starter.`role`
(id, name, status, is_deleted, json, create_by, update_by, create_time, update_time, end_time)
VALUES(1475387934500335617, '访客', 0, 0, '{"count": 0}', 'default', 'default', 1640594869281, 1640594912922, NULL);

--菜单表
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10018 DEFAULT CHARSET=utf8 COMMENT='菜单表';

--项目详情表
CREATE TABLE `info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10018 DEFAULT CHARSET=utf8 COMMENT='项目详情表';

--用户表数据
INSERT INTO user
(name, status, is_deleted, json, create_by, update_by, create_time, update_time, end_time)
VALUES('莫语', 0, 0, '{
  "age": "25",
  "role": 1475377063158071298,
  "menus": [],
  "tel": "123456789090",
  "style": "哈哈",
  "gender": "男",
  "address": "广东省深圳市",
  "username": "admin",
  "password": "123456"
}', 'default', 'default', 1639990424276, 1639990424276, 0);
INSERT INTO user
(name, status, is_deleted, json, create_by, update_by, create_time, update_time, end_time)
VALUES('访客', 0, 0, '{"role": 1475387934500335617, "menus": [], "password": "123456", "username": "user"}', 'default', 'default', 1639990424276, 1639990424276, 0);

--留言表
CREATE TABLE `speak` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='留言表';

--订单表
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='订单表';

--产品表
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='产品表';

--字典表
CREATE TABLE `dictionaries` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='字典表';

--年收益汇总表
CREATE TABLE `profit_2019` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='年收益汇总表';

CREATE TABLE `profit_2020` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='年收益汇总表';

CREATE TABLE `profit_2021` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='年收益汇总表';

CREATE TABLE starter.work_type (
   id bigint(20) auto_increment NOT NULL,
   name varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   status int(11) DEFAULT 0 NOT NULL,
   is_deleted int(11) DEFAULT 0 NOT NULL,
   json json NULL,
   create_by varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   update_by varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   create_time bigint(20) NULL,
   update_time bigint(20) NULL,
   end_time bigint(20) NULL,
   CONSTRAINT `PRIMARY` PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='工单类型';

CREATE TABLE starter.work_pool (
   id bigint(20) auto_increment NOT NULL,
   name varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   status int(11) DEFAULT 0 NOT NULL,
   is_deleted int(11) DEFAULT 0 NOT NULL,
   json json NULL,
   create_by varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   update_by varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   create_time bigint(20) NULL,
   update_time bigint(20) NULL,
   end_time bigint(20) NULL,
   CONSTRAINT `PRIMARY` PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='工单池';

