user-service：用户的增删改查，新增用户通过rabbitMq调用message-service发送欢迎信息。

链接：http://212.64.1.106:8001/swagger-ui.html

message-service：用户消息的查看和新增。

链接：http://212.64.1.106:8002/swagger-ui.html

```mysql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `phone` (`phone`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `gmt_create` (`gmt_create`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiver_id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT '',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `receiver_id` (`receiver_id`) USING BTREE
  KEY `content` (`name`) USING BTREE,
  KEY `gmt_create` (`gmt_create`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

```

脚手架构建：https://github.com/deepexi/generator-deepexi-spring-cloud

分页插件：https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md