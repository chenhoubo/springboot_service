CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT '0',
  `json` json DEFAULT NULL,
  `create_by` varchar(40) DEFAULT NULL,
  `update_by` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;

--模拟数据
INSERT INTO user
(name, status, is_deleted, json, create_by, update_by, create_time, update_time, end_time)
VALUES('张三', 0, 0, '{
  "age": "25",
  "tel": "123456789090",
  "style": "哈哈",
  "gender": "男",
  "address": "广东省深圳市",
  "username": "admin",
  "password": "123456"
}', 'default', 'default', 1639990424276, 1639990424276, 0);