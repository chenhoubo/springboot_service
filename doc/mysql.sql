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