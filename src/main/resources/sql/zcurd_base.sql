# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.17-log)
# Database: zcurd_base
# Generation Time: 2017-11-22 15:47:39 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table common_file
# ------------------------------------------------------------

CREATE TABLE `common_file` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '分类（1：图片，2：文件）',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '图片地址',
  `sys_user_id` int(11) NOT NULL COMMENT '系统用户',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件管理';

LOCK TABLES `common_file` WRITE;
/*!40000 ALTER TABLE `common_file` DISABLE KEYS */;

INSERT INTO `common_file` (`id`, `type`, `path`, `sys_user_id`, `create_time`)
VALUES
	(1,1,'P41025-1754072.jpg',8,'2016-12-21 00:36:24'),
	(2,1,'P41025-154800.jpg',8,'2016-12-21 00:40:14'),
	(3,1,'P41025-1753192.jpg',8,'2016-12-21 00:41:00'),
	(4,1,'P41025-1758078.jpg',8,'2016-12-21 00:45:39'),
	(5,1,'P41025-1757555.jpg',8,'2016-12-21 00:45:42'),
	(6,1,'P41025-1757528.jpg',8,'2016-12-21 00:45:45'),
	(7,1,'P41025-1754153.jpg',8,'2016-12-21 00:45:48'),
	(8,1,'P41025-1757529.jpg',8,'2016-12-21 00:54:42'),
	(9,1,'P41025-1754154.jpg',8,'2016-12-21 00:55:20');

/*!40000 ALTER TABLE `common_file` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_dict
# ------------------------------------------------------------

CREATE TABLE `sys_dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_type` varchar(50) NOT NULL DEFAULT '' COMMENT '类型',
  `dict_key` varchar(50) NOT NULL DEFAULT '' COMMENT '键',
  `dict_value` varchar(100) NOT NULL DEFAULT '' COMMENT '值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;

INSERT INTO `sys_dict` (`id`, `dict_type`, `dict_key`, `dict_value`, `create_time`)
VALUES
	(1,'datarule_symbol','=','=','2016-03-01 22:42:05'),
	(2,'datarule_symbol','>','>','2016-03-01 22:42:21'),
	(3,'datarule_symbol','<','<','2016-03-01 22:42:28'),
	(6,'db_source','zcurd_base','基本库','2016-09-18 17:25:49'),
	(7,'db_source','zcurd_busi','业务库','2016-09-18 17:25:54'),
	(8,'task_type','1','url','2017-03-12 23:29:43'),
	(9,'task_type','2','sql','2017-03-12 23:29:51'),
	(10,'task_statu','1','等待运行','2017-03-12 23:31:55'),
	(11,'task_statu','2','已停止','2017-03-12 23:32:12'),
	(12,'task_type','3','ITask','2017-03-18 14:47:57'),
	(13,'task_statu','3','运行中','2017-03-28 23:26:26');

/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_menu
# ------------------------------------------------------------

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `parent_id` int(11) DEFAULT NULL COMMENT '所属菜单',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `order_num` int(11) DEFAULT NULL COMMENT '顺序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;

INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_url`, `parent_id`, `icon`, `order_num`, `create_time`)
VALUES
	(1,'系统管理',NULL,0,'glyphicon-cog',6,'2016-01-06 19:37:31'),
	(2,'在线表单','/zcurdHead/list',1,'glyphicon-cloud',1,'2016-01-07 21:41:21'),
	(3,'菜单管理','/menu/list',1,'glyphicon-menu-hamburger',5,'2016-01-06 19:37:38'),
	(4,'字典管理','/zcurd/7/listPage',1,'glyphicon-book',6,'2016-02-29 11:44:07'),
	(5,'用户管理',NULL,0,'glyphicon-user',5,'2016-01-06 19:37:31'),
	(6,'角色管理','/zcurd/8/listPage',5,'glyphicon-user',1,'2016-01-07 03:32:08'),
	(7,'系统用户','/zcurd/12/listPage',5,'glyphicon-king',2,'2016-02-16 03:59:22'),
	(9,'内容管理',NULL,0,'glyphicon-search',2,'2016-09-12 22:10:51'),
	(10,'客户管理','/zcurd/15/listPage',9,'glyphicon-th-list',1,'2016-09-12 22:11:26'),
	(11,'大盘数据','/zcurd/16/listPage',9,'glyphicon-flag',2,'2016-09-12 22:12:33'),
	(12,'小说采集','/zcurd/17/listPage',9,'glyphicon-cd',0,'2016-09-18 18:55:28'),
	(13,'操作日志','/oplog/listPage',1,'glyphicon-time',7,'2016-10-16 10:08:02'),
	(14,'图片管理','/zcurd/18/listPage',9,'glyphicon-picture',3,'2016-12-21 01:12:05'),
	(15,'定时任务','/task/listPage',1,'glyphicon-tasks',2,'2017-03-15 23:55:04');

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_menu_btn
# ------------------------------------------------------------

CREATE TABLE `sys_menu_btn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` int(11) DEFAULT NULL COMMENT '所属菜单',
  `btn_name` varchar(100) DEFAULT NULL COMMENT '按钮名称',
  `class_name` varchar(100) DEFAULT NULL COMMENT '页面class名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '后台method名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单按钮';

LOCK TABLES `sys_menu_btn` WRITE;
/*!40000 ALTER TABLE `sys_menu_btn` DISABLE KEYS */;

INSERT INTO `sys_menu_btn` (`id`, `menu_id`, `btn_name`, `class_name`, `method_name`, `create_time`)
VALUES
	(3,12,'增加','addBtn','add,addPage','2016-10-16 13:55:52'),
	(4,12,'修改','updateBtn','update,updatePage','2016-10-16 13:55:52'),
	(5,12,'删除','delBtn','delete','2016-10-16 13:55:52'),
	(6,12,'导出','exportBtn','exportCsv','2016-10-16 13:55:52'),
	(7,10,'增加','addBtn','add,addPage','2016-10-16 14:11:44'),
	(8,10,'修改','updateBtn','update,updatePage','2016-10-16 14:11:44'),
	(9,10,'删除','delBtn','delete','2016-10-16 14:11:44'),
	(10,10,'导出','exportBtn','exportCsv','2016-10-16 14:11:44'),
	(11,11,'增加','addBtn','add,addPage','2016-10-16 14:14:28'),
	(12,11,'修改','updateBtn','update,updatePage','2016-10-16 14:14:28'),
	(13,11,'删除','delBtn','delete','2016-10-16 14:14:28'),
	(14,11,'导出','exportBtn','exportCsv','2016-10-16 14:14:28'),
	(15,14,'增加','addBtn','add,addPage','2016-12-21 22:52:59'),
	(16,14,'修改','updateBtn','update,updatePage','2016-12-21 22:52:59'),
	(17,14,'删除','delBtn','delete','2016-12-21 22:52:59'),
	(18,14,'导出','exportBtn','exportCsv','2016-12-21 22:52:59'),
	(19,6,'增加','addBtn','add,addPage','2016-12-21 22:55:21'),
	(20,6,'修改','updateBtn','update,updatePage','2016-12-21 22:55:21'),
	(21,6,'删除','delBtn','delete','2016-12-21 22:55:21'),
	(22,6,'导出','exportBtn','exportCsv','2016-12-21 22:55:21'),
	(23,7,'增加','addBtn','add,addPage','2016-12-21 22:56:44'),
	(24,7,'修改','updateBtn','update,updatePage','2016-12-21 22:56:44'),
	(25,7,'删除','delBtn','delete','2016-12-21 22:56:44'),
	(26,7,'导出','exportBtn','exportCsv','2016-12-21 22:56:44'),
	(29,2,'删除','delBtn','delete','2016-12-21 22:58:28'),
	(31,3,'增加','addBtn','add,addPage','2016-12-21 23:01:15'),
	(32,3,'修改','updateBtn','update,updatePage','2016-12-21 23:01:15'),
	(33,3,'删除','delBtn','delete','2016-12-21 23:01:15'),
	(34,3,'导出','exportBtn','exportCsv','2016-12-21 23:01:15'),
	(35,4,'增加','addBtn','add,addPage','2016-12-21 23:01:28'),
	(36,4,'修改','updateBtn','update,updatePage','2016-12-21 23:01:28'),
	(37,4,'删除','delBtn','delete','2016-12-21 23:01:28'),
	(38,4,'导出','exportBtn','exportCsv','2016-12-21 23:01:28'),
	(39,7,'重置密码','exBtn4','/user/resetPassword','2017-01-27 15:07:20'),
	(44,15,'增加','addBtn','add,addPage','2017-11-22 23:28:19'),
	(45,15,'修改','updateBtn','update,updatePage','2017-11-22 23:28:19'),
	(46,15,'删除','delBtn','delete','2017-11-22 23:28:19'),
	(47,15,'导出','exportBtn','exportCsv','2017-11-22 23:28:19');

/*!40000 ALTER TABLE `sys_menu_btn` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_menu_datarule
# ------------------------------------------------------------

CREATE TABLE `sys_menu_datarule` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单',
  `field_name` varchar(50) DEFAULT NULL COMMENT '字段名称',
  `symbol` varchar(50) DEFAULT NULL COMMENT '符号',
  `value` varchar(100) DEFAULT NULL COMMENT 'sql条件值',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_menu_datarule` WRITE;
/*!40000 ALTER TABLE `sys_menu_datarule` DISABLE KEYS */;

INSERT INTO `sys_menu_datarule` (`id`, `menu_id`, `field_name`, `symbol`, `value`, `create_time`)
VALUES
	(3,12,'status','<','${user.id}','2016-09-27 00:16:00'),
	(4,6,'id','>','2','2016-12-21 22:56:25'),
	(5,7,'id','>','10','2016-12-21 22:57:56'),
	(6,2,'id','>','18','2016-12-21 23:01:01'),
	(8,15,'id','>','10','2017-11-22 23:28:35');

/*!40000 ALTER TABLE `sys_menu_datarule` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_oplog
# ------------------------------------------------------------

CREATE TABLE `sys_oplog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `op_content` varchar(1000) DEFAULT NULL COMMENT '操作内容',
  `ip` varchar(100) DEFAULT NULL COMMENT 'ip',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统操作日志';



# Dump of table sys_role
# ------------------------------------------------------------

CREATE TABLE `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(11) DEFAULT NULL COMMENT '角色名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `role_name`, `create_time`)
VALUES
	(1,'系统管理员','2016-02-04 21:05:45'),
	(2,'内容运营','2016-02-04 21:06:12'),
	(4,'readonly','2016-10-16 14:22:10');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_btn
# ------------------------------------------------------------

CREATE TABLE `sys_role_btn` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `btn_id` int(11) DEFAULT NULL COMMENT '按钮id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_role_btn` WRITE;
/*!40000 ALTER TABLE `sys_role_btn` DISABLE KEYS */;

INSERT INTO `sys_role_btn` (`id`, `role_id`, `btn_id`, `create_time`)
VALUES
	(450,2,34,'2016-12-21 23:05:22'),
	(451,2,32,'2016-12-21 23:05:22'),
	(452,2,31,'2016-12-21 23:05:22'),
	(453,2,36,'2016-12-21 23:05:22'),
	(454,2,35,'2016-12-21 23:05:22'),
	(455,2,22,'2016-12-21 23:05:22'),
	(456,2,21,'2016-12-21 23:05:22'),
	(457,2,24,'2016-12-21 23:05:22'),
	(458,2,23,'2016-12-21 23:05:22'),
	(459,2,10,'2016-12-21 23:05:22'),
	(460,2,9,'2016-12-21 23:05:22'),
	(461,2,8,'2016-12-21 23:05:22'),
	(462,2,7,'2016-12-21 23:05:22'),
	(463,2,14,'2016-12-21 23:05:22'),
	(464,2,13,'2016-12-21 23:05:22'),
	(465,2,12,'2016-12-21 23:05:22'),
	(466,2,11,'2016-12-21 23:05:22'),
	(467,2,6,'2016-12-21 23:05:22'),
	(468,2,5,'2016-12-21 23:05:22'),
	(469,2,4,'2016-12-21 23:05:22'),
	(470,2,3,'2016-12-21 23:05:22'),
	(471,2,18,'2016-12-21 23:05:22'),
	(472,2,17,'2016-12-21 23:05:22'),
	(473,2,16,'2016-12-21 23:05:22'),
	(474,2,15,'2016-12-21 23:05:22'),
	(521,1,32,'2017-11-22 23:28:40'),
	(522,1,31,'2017-11-22 23:28:40'),
	(523,1,38,'2017-11-22 23:28:40'),
	(524,1,36,'2017-11-22 23:28:40'),
	(525,1,35,'2017-11-22 23:28:40'),
	(526,1,22,'2017-11-22 23:28:40'),
	(527,1,20,'2017-11-22 23:28:40'),
	(528,1,19,'2017-11-22 23:28:40'),
	(529,1,10,'2017-11-22 23:28:40'),
	(530,1,9,'2017-11-22 23:28:40'),
	(531,1,8,'2017-11-22 23:28:40'),
	(532,1,7,'2017-11-22 23:28:40'),
	(533,1,14,'2017-11-22 23:28:40'),
	(534,1,13,'2017-11-22 23:28:40'),
	(535,1,12,'2017-11-22 23:28:40'),
	(536,1,11,'2017-11-22 23:28:40'),
	(537,1,6,'2017-11-22 23:28:40'),
	(538,1,5,'2017-11-22 23:28:40'),
	(539,1,4,'2017-11-22 23:28:40'),
	(540,1,3,'2017-11-22 23:28:40');

/*!40000 ALTER TABLE `sys_role_btn` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_datarule
# ------------------------------------------------------------

CREATE TABLE `sys_role_datarule` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色',
  `datarule_id` int(11) NOT NULL COMMENT '数据规则',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色数据规则';

LOCK TABLES `sys_role_datarule` WRITE;
/*!40000 ALTER TABLE `sys_role_datarule` DISABLE KEYS */;

INSERT INTO `sys_role_datarule` (`id`, `role_id`, `datarule_id`, `create_time`)
VALUES
	(18,3,3,'2016-10-16 15:01:17'),
	(23,4,3,'2016-12-21 22:53:05'),
	(44,2,6,'2016-12-21 23:05:22'),
	(45,2,4,'2016-12-21 23:05:22'),
	(46,2,5,'2016-12-21 23:05:22'),
	(56,1,6,'2017-11-22 23:28:40'),
	(57,1,4,'2017-11-22 23:28:40'),
	(58,1,5,'2017-11-22 23:28:40'),
	(59,1,3,'2017-11-22 23:28:40'),
	(60,1,8,'2017-11-22 23:28:40');

/*!40000 ALTER TABLE `sys_role_datarule` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_menu
# ------------------------------------------------------------

CREATE TABLE `sys_role_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单';

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;

INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_time`)
VALUES
	(472,4,12,'2016-12-21 22:53:05'),
	(473,4,10,'2016-12-21 22:53:05'),
	(474,4,11,'2016-12-21 22:53:05'),
	(475,4,14,'2016-12-21 22:53:05'),
	(578,2,12,'2016-12-21 23:05:22'),
	(579,2,10,'2016-12-21 23:05:22'),
	(580,2,11,'2016-12-21 23:05:22'),
	(581,2,14,'2016-12-21 23:05:22'),
	(596,1,6,'2017-11-22 23:28:40'),
	(597,1,7,'2017-11-22 23:28:40'),
	(598,1,2,'2017-11-22 23:28:40'),
	(599,1,15,'2017-11-22 23:28:40'),
	(600,1,3,'2017-11-22 23:28:40'),
	(601,1,4,'2017-11-22 23:28:40'),
	(602,1,13,'2017-11-22 23:28:40');

/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user
# ------------------------------------------------------------

CREATE TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `roles` varchar(100) DEFAULT NULL COMMENT '角色',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `user_name`, `password`, `roles`, `create_time`)
VALUES
	(1,'admin','25d55ad283aa400af464c76d713c07ad',NULL,'2016-01-08 15:33:05'),
	(2,'zcurd','-1ef523c6b645a65441a91fa80df077c2','2,1','2016-10-29 16:59:44'),
	(3,'readonly','-1ef523c6b645a65441a91fa80df077c2','4','2016-10-16 14:22:46'),
	(11,'zhong','-1ef523c6b645a65441a91fa80df077c2','2,1','2016-02-21 01:19:47');

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_base
# ------------------------------------------------------------

CREATE TABLE `task_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `target_type` int(11) DEFAULT NULL COMMENT '任务类型',
  `target_value` text COMMENT '任务值',
  `cron` varchar(50) DEFAULT NULL COMMENT 'cron表达式',
  `last_run_result` varchar(50) DEFAULT NULL COMMENT '上次执行结果',
  `last_run_time` datetime DEFAULT NULL COMMENT '上次执行时间',
  `last_run_time_cost` int(11) DEFAULT NULL COMMENT '上次执行耗时',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

LOCK TABLES `task_base` WRITE;
/*!40000 ALTER TABLE `task_base` DISABLE KEYS */;

INSERT INTO `task_base` (`id`, `name`, `target_type`, `target_value`, `cron`, `last_run_result`, `last_run_time`, `last_run_time_cost`, `status`, `create_user_id`, `create_time`)
VALUES
	(1,'重置账号数据',2,'delete from sys_menu;\nINSERT INTO `sys_menu` (`id`, `menu_name`, `menu_url`, `parent_id`, `icon`, `order_num`, `create_time`)\nVALUES\n	(1,\'系统管理\',NULL,0,\'glyphicon-cog\',6,\'2016-01-06 19:37:31\'),\n	(2,\'在线表单\',\'/zcurdHead/list\',1,\'glyphicon-cloud\',1,\'2016-01-07 21:41:21\'),\n	(3,\'菜单管理\',\'/menu/list\',1,\'glyphicon-menu-hamburger\',5,\'2016-01-06 19:37:38\'),\n	(4,\'字典管理\',\'/zcurd/7/listPage\',1,\'glyphicon-book\',6,\'2016-02-29 11:44:07\'),\n	(5,\'用户管理\',NULL,0,\'glyphicon-user\',5,\'2016-01-06 19:37:31\'),\n	(6,\'角色管理\',\'/zcurd/8/listPage\',5,\'glyphicon-user\',1,\'2016-01-07 03:32:08\'),\n	(7,\'系统用户\',\'/zcurd/12/listPage\',5,\'glyphicon-king\',2,\'2016-02-16 03:59:22\'),\n	(9,\'内容管理\',NULL,0,\'glyphicon-search\',2,\'2016-09-12 22:10:51\'),\n	(10,\'客户管理\',\'/zcurd/15/listPage\',9,\'glyphicon-th-list\',1,\'2016-09-12 22:11:26\'),\n	(11,\'大盘数据\',\'/zcurd/16/listPage\',9,\'glyphicon-flag\',2,\'2016-09-12 22:12:33\'),\n	(12,\'小说采集\',\'/zcurd/17/listPage\',9,\'glyphicon-cd\',0,\'2016-09-18 18:55:28\'),\n	(13,\'操作日志\',\'/oplog/listPage\',1,\'glyphicon-time\',7,\'2016-10-16 10:08:02\'),\n	(14,\'图片管理\',\'/zcurd/18/listPage\',9,\'glyphicon-picture\',3,\'2016-12-21 01:12:05\'),\n	(15,\'定时任务\',\'/task/listPage\',1,\'glyphicon-tasks\',2,\'2017-03-15 23:55:04\');\n\ndelete from sys_menu_btn;\nINSERT INTO `sys_menu_btn` (`id`, `menu_id`, `btn_name`, `class_name`, `method_name`, `create_time`)\nVALUES\n	(3,12,\'增加\',\'addBtn\',\'add,addPage\',\'2016-10-16 13:55:52\'),\n	(4,12,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-10-16 13:55:52\'),\n	(5,12,\'删除\',\'delBtn\',\'delete\',\'2016-10-16 13:55:52\'),\n	(6,12,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-10-16 13:55:52\'),\n	(7,10,\'增加\',\'addBtn\',\'add,addPage\',\'2016-10-16 14:11:44\'),\n	(8,10,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-10-16 14:11:44\'),\n	(9,10,\'删除\',\'delBtn\',\'delete\',\'2016-10-16 14:11:44\'),\n	(10,10,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-10-16 14:11:44\'),\n	(11,11,\'增加\',\'addBtn\',\'add,addPage\',\'2016-10-16 14:14:28\'),\n	(12,11,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-10-16 14:14:28\'),\n	(13,11,\'删除\',\'delBtn\',\'delete\',\'2016-10-16 14:14:28\'),\n	(14,11,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-10-16 14:14:28\'),\n	(15,14,\'增加\',\'addBtn\',\'add,addPage\',\'2016-12-21 22:52:59\'),\n	(16,14,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-12-21 22:52:59\'),\n	(17,14,\'删除\',\'delBtn\',\'delete\',\'2016-12-21 22:52:59\'),\n	(18,14,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-12-21 22:52:59\'),\n	(19,6,\'增加\',\'addBtn\',\'add,addPage\',\'2016-12-21 22:55:21\'),\n	(20,6,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-12-21 22:55:21\'),\n	(21,6,\'删除\',\'delBtn\',\'delete\',\'2016-12-21 22:55:21\'),\n	(22,6,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-12-21 22:55:21\'),\n	(23,7,\'增加\',\'addBtn\',\'add,addPage\',\'2016-12-21 22:56:44\'),\n	(24,7,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-12-21 22:56:44\'),\n	(25,7,\'删除\',\'delBtn\',\'delete\',\'2016-12-21 22:56:44\'),\n	(26,7,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-12-21 22:56:44\'),\n	(29,2,\'删除\',\'delBtn\',\'delete\',\'2016-12-21 22:58:28\'),\n	(31,3,\'增加\',\'addBtn\',\'add,addPage\',\'2016-12-21 23:01:15\'),\n	(32,3,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-12-21 23:01:15\'),\n	(33,3,\'删除\',\'delBtn\',\'delete\',\'2016-12-21 23:01:15\'),\n	(34,3,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-12-21 23:01:15\'),\n	(35,4,\'增加\',\'addBtn\',\'add,addPage\',\'2016-12-21 23:01:28\'),\n	(36,4,\'修改\',\'updateBtn\',\'update,updatePage\',\'2016-12-21 23:01:28\'),\n	(37,4,\'删除\',\'delBtn\',\'delete\',\'2016-12-21 23:01:28\'),\n	(38,4,\'导出\',\'exportBtn\',\'exportCsv\',\'2016-12-21 23:01:28\'),\n	(39,7,\'重置密码\',\'exBtn4\',\'/user/resetPassword\',\'2017-01-27 15:07:20\'),\n	(44,15,\'增加\',\'addBtn\',\'add,addPage\',\'2017-11-22 23:28:19\'),\n	(45,15,\'修改\',\'updateBtn\',\'update,updatePage\',\'2017-11-22 23:28:19\'),\n	(46,15,\'删除\',\'delBtn\',\'delete\',\'2017-11-22 23:28:19\'),\n	(47,15,\'导出\',\'exportBtn\',\'exportCsv\',\'2017-11-22 23:28:19\');\n\ndelete from sys_role;\nINSERT INTO `sys_role` (`id`, `role_name`, `create_time`)\nVALUES\n	(1,\'系统管理员\',\'2016-02-04 21:05:45\'),\n	(2,\'内容运营\',\'2016-02-04 21:06:12\'),\n	(4,\'readonly\',\'2016-10-16 14:22:10\');\n\ndelete from sys_role_btn;\nINSERT INTO `sys_role_btn` (`id`, `role_id`, `btn_id`, `create_time`)\nVALUES\n	(450,2,34,\'2016-12-21 23:05:22\'),\n	(451,2,32,\'2016-12-21 23:05:22\'),\n	(452,2,31,\'2016-12-21 23:05:22\'),\n	(453,2,36,\'2016-12-21 23:05:22\'),\n	(454,2,35,\'2016-12-21 23:05:22\'),\n	(455,2,22,\'2016-12-21 23:05:22\'),\n	(456,2,21,\'2016-12-21 23:05:22\'),\n	(457,2,24,\'2016-12-21 23:05:22\'),\n	(458,2,23,\'2016-12-21 23:05:22\'),\n	(459,2,10,\'2016-12-21 23:05:22\'),\n	(460,2,9,\'2016-12-21 23:05:22\'),\n	(461,2,8,\'2016-12-21 23:05:22\'),\n	(462,2,7,\'2016-12-21 23:05:22\'),\n	(463,2,14,\'2016-12-21 23:05:22\'),\n	(464,2,13,\'2016-12-21 23:05:22\'),\n	(465,2,12,\'2016-12-21 23:05:22\'),\n	(466,2,11,\'2016-12-21 23:05:22\'),\n	(467,2,6,\'2016-12-21 23:05:22\'),\n	(468,2,5,\'2016-12-21 23:05:22\'),\n	(469,2,4,\'2016-12-21 23:05:22\'),\n	(470,2,3,\'2016-12-21 23:05:22\'),\n	(471,2,18,\'2016-12-21 23:05:22\'),\n	(472,2,17,\'2016-12-21 23:05:22\'),\n	(473,2,16,\'2016-12-21 23:05:22\'),\n	(474,2,15,\'2016-12-21 23:05:22\'),\n	(521,1,32,\'2017-11-22 23:28:40\'),\n	(522,1,31,\'2017-11-22 23:28:40\'),\n	(523,1,38,\'2017-11-22 23:28:40\'),\n	(524,1,36,\'2017-11-22 23:28:40\'),\n	(525,1,35,\'2017-11-22 23:28:40\'),\n	(526,1,22,\'2017-11-22 23:28:40\'),\n	(527,1,20,\'2017-11-22 23:28:40\'),\n	(528,1,19,\'2017-11-22 23:28:40\'),\n	(529,1,10,\'2017-11-22 23:28:40\'),\n	(530,1,9,\'2017-11-22 23:28:40\'),\n	(531,1,8,\'2017-11-22 23:28:40\'),\n	(532,1,7,\'2017-11-22 23:28:40\'),\n	(533,1,14,\'2017-11-22 23:28:40\'),\n	(534,1,13,\'2017-11-22 23:28:40\'),\n	(535,1,12,\'2017-11-22 23:28:40\'),\n	(536,1,11,\'2017-11-22 23:28:40\'),\n	(537,1,6,\'2017-11-22 23:28:40\'),\n	(538,1,5,\'2017-11-22 23:28:40\'),\n	(539,1,4,\'2017-11-22 23:28:40\'),\n	(540,1,3,\'2017-11-22 23:28:40\');\n\ndelete from sys_role_datarule;\nINSERT INTO `sys_role_datarule` (`id`, `role_id`, `datarule_id`, `create_time`)\nVALUES\n	(18,3,3,\'2016-10-16 15:01:17\'),\n	(23,4,3,\'2016-12-21 22:53:05\'),\n	(44,2,6,\'2016-12-21 23:05:22\'),\n	(45,2,4,\'2016-12-21 23:05:22\'),\n	(46,2,5,\'2016-12-21 23:05:22\'),\n	(56,1,6,\'2017-11-22 23:28:40\'),\n	(57,1,4,\'2017-11-22 23:28:40\'),\n	(58,1,5,\'2017-11-22 23:28:40\'),\n	(59,1,3,\'2017-11-22 23:28:40\'),\n	(60,1,8,\'2017-11-22 23:28:40\');\n\ndelete from sys_role_menu;\nINSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_time`)\nVALUES\n	(472,4,12,\'2016-12-21 22:53:05\'),\n	(473,4,10,\'2016-12-21 22:53:05\'),\n	(474,4,11,\'2016-12-21 22:53:05\'),\n	(475,4,14,\'2016-12-21 22:53:05\'),\n	(578,2,12,\'2016-12-21 23:05:22\'),\n	(579,2,10,\'2016-12-21 23:05:22\'),\n	(580,2,11,\'2016-12-21 23:05:22\'),\n	(581,2,14,\'2016-12-21 23:05:22\'),\n	(596,1,6,\'2017-11-22 23:28:40\'),\n	(597,1,7,\'2017-11-22 23:28:40\'),\n	(598,1,2,\'2017-11-22 23:28:40\'),\n	(599,1,15,\'2017-11-22 23:28:40\'),\n	(600,1,3,\'2017-11-22 23:28:40\'),\n	(601,1,4,\'2017-11-22 23:28:40\'),\n	(602,1,13,\'2017-11-22 23:28:40\');\n\ndelete from sys_user;\nINSERT INTO `sys_user` (`id`, `user_name`, `password`, `roles`, `create_time`)\nVALUES\n	(1,\'admin\',\'25d55ad283aa400af464c76d713c07ad\',NULL,\'2016-01-08 15:33:05\'),\n	(2,\'zcurd\',\'-1ef523c6b645a65441a91fa80df077c2\',\'2,1\',\'2016-10-29 16:59:44\'),\n	(3,\'readonly\',\'-1ef523c6b645a65441a91fa80df077c2\',\'4\',\'2016-10-16 14:22:46\'),\n	(11,\'zhong\',\'-1ef523c6b645a65441a91fa80df077c2\',\'2,1\',\'2016-02-21 01:19:47\');','* * 2 * *','成功','2017-11-22 23:46:17',9,1,NULL,'2017-11-22 00:07:23'),
	(11,'测试-请示URL',1,'https://www.baidu.com','* * */1 * *','成功','2017-11-22 23:23:00',3,2,1,'2017-03-04 16:08:06'),
	(12,'测试-查询',2,'select 2','* * */1 * *','成功','2017-11-22 23:47:00',4,1,NULL,'2017-03-04 17:32:02'),
	(13,'测试-存储过程（体验地址不支持）',2,'call zcurd_base.pro_test()','* * */1 * *','成功','2017-04-09 09:31:24',2,2,NULL,'2017-03-05 00:30:20'),
	(14,'测试-ITask',3,'com.zcurd.common.task.DemoTask','* * */1 * *','成功','2017-03-26 22:50:00',3,2,NULL,'2017-03-18 14:43:55');

/*!40000 ALTER TABLE `task_base` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_log
# ------------------------------------------------------------

CREATE TABLE `task_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` int(11) DEFAULT NULL COMMENT '所属任务',
  `result` varchar(50) DEFAULT NULL COMMENT '执行结果',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `cost_time` int(11) DEFAULT NULL COMMENT '耗时',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务-日志';



# Dump of table tb_proc_log
# ------------------------------------------------------------

CREATE TABLE `tb_proc_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `proc_name` varchar(50) DEFAULT NULL COMMENT '过程名称',
  `statu` varchar(20) DEFAULT NULL COMMENT '状态',
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `cost_time` int(11) DEFAULT NULL COMMENT '耗时',
  `error_code` varchar(50) DEFAULT NULL COMMENT 'error_code',
  `error_msg` varchar(200) DEFAULT NULL COMMENT 'error_msg',
  `step` int(11) DEFAULT NULL COMMENT 'step',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储过程运行日志';



# Dump of table zcurd_field
# ------------------------------------------------------------

CREATE TABLE `zcurd_field` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `head_id` int(11) DEFAULT NULL COMMENT '所属表',
  `field_name` varchar(100) DEFAULT NULL COMMENT '字段名称',
  `column_name` varchar(100) DEFAULT NULL COMMENT '列表列名',
  `column_length` int(11) DEFAULT '120' COMMENT '列宽',
  `data_type` varchar(50) DEFAULT NULL COMMENT '数据类型',
  `input_type` varchar(50) DEFAULT 'easyui-textbox' COMMENT '控件类型',
  `is_show_list` int(11) DEFAULT '1' COMMENT '是否列表显示',
  `is_allow_detail` int(11) DEFAULT '1' COMMENT '是否详情显示',
  `is_allow_add` int(11) DEFAULT '1' COMMENT '是否允许增加',
  `is_allow_update` int(11) DEFAULT '1' COMMENT '是否允许编辑',
  `is_search` int(11) DEFAULT '0' COMMENT '是否搜索',
  `is_allow_null` int(11) DEFAULT '1' COMMENT '是否允许为空',
  `dict_sql` varchar(1000) DEFAULT NULL COMMENT '字典sql',
  `order_num` int(11) DEFAULT '0' COMMENT '顺序',
  `search_type` int(11) DEFAULT '1' COMMENT '搜索类型(1:值,2:范围)',
  `is_sum` int(11) DEFAULT '0' COMMENT '是否汇总',
  `default_value` varchar(100) DEFAULT NULL COMMENT '默认值',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在线表单字段';

LOCK TABLES `zcurd_field` WRITE;
/*!40000 ALTER TABLE `zcurd_field` DISABLE KEYS */;

INSERT INTO `zcurd_field` (`id`, `head_id`, `field_name`, `column_name`, `column_length`, `data_type`, `input_type`, `is_show_list`, `is_allow_detail`, `is_allow_add`, `is_allow_update`, `is_search`, `is_allow_null`, `dict_sql`, `order_num`, `search_type`, `is_sum`, `default_value`, `create_time`)
VALUES
	(2,1,'func_content','方法内容',120,'text','textarea',0,1,1,1,0,0,'',6,1,0,NULL,'2016-01-11 21:58:48'),
	(1,1,'create_time','创建时间',130,'timestamp','easyui-textbox',1,1,0,0,0,1,'',7,1,0,NULL,'2016-01-11 21:58:48'),
	(4,1,'location','按钮位置',80,'int','easyui-combobox',1,1,1,1,0,0,'select 1, \'顶部\' union all select 2, \'行内\'',4,1,0,NULL,'2016-01-11 21:58:48'),
	(5,1,'action','行为',80,'int','easyui-combobox',1,1,1,1,0,0,'select 0, \'自定义\' union all select 1, \'打开子页面\'',5,1,0,NULL,'2016-01-11 21:58:48'),
	(190,15,'archremark','资料归档备注',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,119,1,0,NULL,'2016-09-18 17:31:32'),
	(189,15,'archtime','资料归档时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,118,1,0,NULL,'2016-09-18 17:31:32'),
	(188,15,'archstatus','资料归档情况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,117,1,0,NULL,'2016-09-18 17:31:32'),
	(187,15,'approvalopinion','审批意见',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,116,1,0,NULL,'2016-09-18 17:31:32'),
	(186,15,'gjrelation','共借人关系',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,115,1,0,NULL,'2016-09-18 17:31:32'),
	(7,2,'js_content','扩展js',120,'text','textarea',0,1,1,1,0,0,'',4,1,0,NULL,'2016-01-12 15:35:01'),
	(6,2,'create_time','创建时间',130,'timestamp','easyui-textbox',1,1,0,0,0,1,'',5,1,0,NULL,'2016-01-12 15:35:01'),
	(8,2,'page','页面',120,'varchar','easyui-combobox',1,1,1,1,0,0,'select \'list\' as \'key\', \'list\' as \'value\' union all select \'add\', \'add\' union all select \'update\', \'update\' union all select \'detail\', \'detail\'',2,1,0,NULL,'2016-01-12 15:35:01'),
	(209,16,'turnover_volume','成交量',120,'bigint','easyui-textbox',1,1,1,1,0,1,'',12,1,0,NULL,'2016-09-18 17:31:55'),
	(210,16,'turnover_money','成交金额',120,'bigint','easyui-textbox',1,1,1,1,0,1,'',13,1,0,NULL,'2016-09-18 17:31:55'),
	(182,15,'refundtime','退款时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,111,1,0,NULL,'2016-09-18 17:31:32'),
	(183,15,'loanMoney','loanMoney',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,112,1,0,NULL,'2016-09-18 17:31:32'),
	(184,15,'creditLevel','信用卡等级',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,113,1,0,NULL,'2016-09-18 17:31:32'),
	(185,15,'buyhouseaddress','客户新买房新地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,114,1,0,NULL,'2016-09-18 17:31:32'),
	(26,5,'menu_id','所属菜单',100,'int','easyui-combobox',1,1,1,1,1,0,'select id, menu_name from sys_menu',1,1,0,NULL,'2016-02-14 16:35:56'),
	(28,5,'class_name','页面class名称',100,'varchar','easyui-textbox',1,1,1,1,0,0,'',3,1,0,NULL,'2016-02-14 16:35:56'),
	(27,5,'btn_name','按钮名称',100,'varchar','easyui-textbox',1,1,1,1,0,0,'',2,1,0,NULL,'2016-02-14 16:35:56'),
	(34,6,'value','字段件值',80,'varchar','easyui-textbox',1,1,1,1,0,0,'',5,1,0,NULL,'2016-02-25 23:55:58'),
	(36,6,'symbol','符号',80,'varchar','easyui-combobox',1,1,1,1,0,0,'select dict_key, dict_value from sys_dict where dict_type=\'datarule_symbol\'',4,1,0,NULL,'2016-02-28 03:20:56'),
	(33,6,'field_name','字段名称',100,'varchar','easyui-textbox',1,1,1,1,0,0,'',3,1,0,NULL,'2016-02-25 23:55:58'),
	(32,6,'menu_id','菜单',100,'int','easyui-combobox',1,1,1,1,1,0,'select id, menu_name from sys_menu',2,1,0,NULL,'2016-02-25 23:55:58'),
	(194,15,'overduecount','逾期次数',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,123,1,0,NULL,'2016-09-18 17:31:32'),
	(193,15,'repaystatus','还款情况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,122,1,0,NULL,'2016-09-18 17:31:32'),
	(192,15,'mortgageremark','按揭备注',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,121,1,0,NULL,'2016-09-18 17:31:32'),
	(191,15,'mortgagestatus','按揭情况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,120,1,0,NULL,'2016-09-18 17:31:32'),
	(181,15,'refundamount','退款金额',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,110,1,0,NULL,'2016-09-18 17:31:32'),
	(43,8,'role_name','角色名称',120,'varchar','easyui-textbox',1,1,1,1,0,0,'',1,1,0,NULL,'2016-02-12 01:58:39'),
	(42,8,'id','id',120,'int','easyui-numberspinner',1,1,1,1,0,0,'',0,1,0,NULL,'2016-02-12 01:58:39'),
	(44,8,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,0,0,0,1,'',2,1,0,NULL,'2016-02-12 01:58:39'),
	(30,5,'create_time','创建时间',120,'timestamp','easyui-datebox',0,0,0,0,0,0,'',5,1,0,NULL,'2016-02-14 16:35:56'),
	(37,7,'id','id',120,'int','easyui-numberspinner',1,1,1,1,0,0,'',0,1,0,NULL,'2016-03-01 04:39:14'),
	(41,7,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,0,0,1,0,'',4,2,0,NULL,'2016-03-01 04:39:14'),
	(40,7,'dict_value','值',120,'varchar','easyui-textbox',1,1,1,1,1,0,'',2,1,0,NULL,'2016-03-01 04:39:14'),
	(39,7,'dict_key','键',120,'varchar','easyui-textbox',1,1,1,1,1,0,'',3,1,0,NULL,'2016-03-01 04:39:14'),
	(38,7,'dict_type','类型',120,'varchar','easyui-combobox',1,1,1,1,1,0,'select distinct dict_type as \'key\', dict_type as \'value\' from sys_dict',1,1,0,NULL,'2016-03-01 04:39:14'),
	(61,12,'roles','角色',120,'varchar','easyui-combobox',1,1,1,1,0,1,'select id, role_name from sys_role',4,1,0,NULL,'2016-02-23 04:31:08'),
	(60,12,'create_time','创建时间',120,'timestamp','easyui-textbox',1,1,0,0,1,1,'',5,2,0,NULL,'2016-01-07 21:31:45'),
	(59,12,'password','密码',120,'varchar','easyui-textbox',0,0,1,0,0,1,'',3,1,0,NULL,'2016-01-07 21:31:45'),
	(57,12,'id','id',120,'int','easyui-textbox',1,1,1,1,0,1,'',1,1,0,NULL,'2016-01-07 21:31:45'),
	(58,12,'user_name','用户名',120,'varchar','easyui-textbox',1,1,1,1,1,1,'',2,1,0,NULL,'2016-01-07 21:31:45'),
	(180,15,'consultantphone','顾问号码',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,109,1,0,NULL,'2016-09-18 17:31:32'),
	(179,15,'houseConsultant','职业顾问',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,108,1,0,NULL,'2016-09-18 17:31:32'),
	(178,15,'loanbankaccount','还款银行卡号',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,107,1,0,NULL,'2016-09-18 17:31:32'),
	(177,15,'accountbranch','开户支行',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,106,1,0,NULL,'2016-09-18 17:31:32'),
	(31,6,'id','id',120,'int','easyui-numberspinner',1,1,1,1,0,0,'',1,1,0,NULL,'2016-02-25 23:55:58'),
	(176,15,'applyloandate','申请放款时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,105,1,0,NULL,'2016-09-18 17:31:32'),
	(175,15,'urgentlevel','紧急程度',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,104,1,0,NULL,'2016-09-18 17:31:32'),
	(174,15,'othertime','othertime',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,103,1,0,NULL,'2016-09-18 17:31:32'),
	(173,15,'otheramount','加他付款方式',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,102,1,0,NULL,'2016-09-18 17:31:32'),
	(172,15,'transfertime','转账时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,101,1,0,NULL,'2016-09-18 17:31:32'),
	(171,15,'transferamount','转账金额',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,100,1,0,NULL,'2016-09-18 17:31:32'),
	(170,15,'cashtime','现金付款时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,99,1,0,NULL,'2016-09-18 17:31:32'),
	(169,15,'cashamount','现金金额',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,98,1,0,NULL,'2016-09-18 17:31:32'),
	(168,15,'postime','pos刷卡时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,97,1,0,NULL,'2016-09-18 17:31:32'),
	(167,15,'posamount','放款金额',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,96,1,0,NULL,'2016-09-18 17:31:32'),
	(166,15,'recviway','还款方式1 pos机器 2现金  3转账  4其他',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,95,1,0,NULL,'2016-09-18 17:31:32'),
	(165,15,'loantime','放款时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,94,1,0,NULL,'2016-09-18 17:31:32'),
	(164,15,'loanstatus','放款状态',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,93,1,0,NULL,'2016-09-18 17:31:32'),
	(163,15,'signtime','签约时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,92,1,0,NULL,'2016-09-18 17:31:32'),
	(162,15,'signstatus','签约状态',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,91,1,0,NULL,'2016-09-18 17:31:32'),
	(161,15,'repay','repay',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,90,1,0,NULL,'2016-09-18 17:31:32'),
	(160,15,'period','period',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,89,1,0,NULL,'2016-09-18 17:31:32'),
	(159,15,'personPath','personPath',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,88,1,0,NULL,'2016-09-18 17:31:32'),
	(158,15,'fkid','fkid',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,87,1,0,NULL,'2016-09-18 17:31:32'),
	(157,15,'examindate','报审时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,86,1,0,NULL,'2016-09-18 17:31:32'),
	(156,15,'fundname','fundname',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,85,1,0,NULL,'2016-09-18 17:31:32'),
	(155,15,'fund','fund',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,84,1,0,NULL,'2016-09-18 17:31:32'),
	(154,15,'loantimelimit','loantimelimit',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,83,1,0,NULL,'2016-09-18 17:31:32'),
	(153,15,'zcompanyaddress','zcompanyaddress',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,82,1,0,NULL,'2016-09-18 17:31:32'),
	(152,15,'gunitaddress','gunitaddress',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,81,1,0,NULL,'2016-09-18 17:31:32'),
	(151,15,'xsname','xsname',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,80,1,0,NULL,'2016-09-18 17:31:32'),
	(150,15,'xsid','xsid',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,79,1,0,NULL,'2016-09-18 17:31:32'),
	(149,15,'relations','relations',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,78,1,0,NULL,'2016-09-18 17:31:32'),
	(148,15,'creditLoanMoney','creditLoanMoney',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,77,1,0,NULL,'2016-09-18 17:31:32'),
	(147,15,'zenterpborrow','zenterpborrow',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,76,1,0,NULL,'2016-09-18 17:31:32'),
	(146,15,'creditNumber','creditNumber',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,75,1,0,NULL,'2016-09-18 17:31:32'),
	(145,15,'aname','aname',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,74,1,0,NULL,'2016-09-18 17:31:32'),
	(144,15,'ctime','ctime',120,'datetime','easyui-textbox',1,1,1,1,0,1,NULL,73,1,0,NULL,'2016-09-18 17:31:32'),
	(143,15,'adid','adid',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,72,1,0,NULL,'2016-09-18 17:31:32'),
	(142,15,'contposition','紧急联系人工作职位',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,71,1,0,NULL,'2016-09-18 17:31:32'),
	(141,15,'contworkdep','紧急联系人工作部门',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,70,1,0,NULL,'2016-09-18 17:31:32'),
	(140,15,'contunitaddress','紧急联系人单位地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,69,1,0,NULL,'2016-09-18 17:31:32'),
	(139,15,'contunitphone','紧急联系人单位电话',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,68,1,0,NULL,'2016-09-18 17:31:32'),
	(138,15,'contunitname','紧急联系人单位名称',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,67,1,0,NULL,'2016-09-18 17:31:32'),
	(137,15,'contaddress','紧急联系人地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,66,1,0,NULL,'2016-09-18 17:31:32'),
	(136,15,'contphone','紧急联系人住宅电话',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,65,1,0,NULL,'2016-09-18 17:31:32'),
	(135,15,'contmobile','紧急联系人电话',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,64,1,0,NULL,'2016-09-18 17:31:32'),
	(134,15,'contloan','经济联系人是否知晓贷款',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,63,1,0,NULL,'2016-09-18 17:31:32'),
	(133,15,'contrelation','紧急联系人关系',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,62,1,0,NULL,'2016-09-18 17:31:32'),
	(132,15,'contsex','紧急联系人性别',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,61,1,0,NULL,'2016-09-18 17:31:32'),
	(131,15,'contage','紧急联系人年龄',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,60,1,0,NULL,'2016-09-18 17:31:32'),
	(130,15,'contname','紧急联系人姓名',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,59,1,0,NULL,'2016-09-18 17:31:32'),
	(129,15,'otherloan','其他贷款',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,58,1,0,NULL,'2016-09-18 17:31:32'),
	(128,15,'creditconsume','信用卡已消费',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,57,1,0,NULL,'2016-09-18 17:31:32'),
	(127,15,'creditlimit','信用卡最高额度',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,56,1,0,NULL,'2016-09-18 17:31:32'),
	(126,15,'bankaccount','用户银行账户',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,55,1,0,NULL,'2016-09-18 17:31:32'),
	(125,15,'age','年龄',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,54,1,0,NULL,'2016-09-18 17:31:32'),
	(124,15,'zsharesratio','占股比例-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,53,1,0,NULL,'2016-09-18 17:31:32'),
	(123,15,'zmanageplace','经营场所-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,52,1,0,NULL,'2016-09-18 17:31:32'),
	(122,15,'zenterploan','企业借款-自雇',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,51,1,0,NULL,'2016-09-18 17:31:32'),
	(121,15,'zenterpfund','企业资产-自雇',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,50,1,0,NULL,'2016-09-18 17:31:32'),
	(120,15,'zyearbus','年营业额-自雇',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,49,1,0,NULL,'2016-09-18 17:31:32'),
	(119,15,'zmonincome','月收入—自雇',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,48,1,0,NULL,'2016-09-18 17:31:32'),
	(118,15,'zindustrytype','行业类型-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,47,1,0,NULL,'2016-09-18 17:31:32'),
	(117,15,'zcompanyname','企业名-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,46,1,0,NULL,'2016-09-18 17:31:32'),
	(116,15,'gsalaryway','工作发放形式-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,45,1,0,NULL,'2016-09-18 17:31:32'),
	(115,15,'gworklife','工作年限-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,44,1,0,NULL,'2016-09-18 17:31:32'),
	(114,15,'gmonincome','月收入-工薪',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,43,1,0,NULL,'2016-09-18 17:31:32'),
	(113,15,'gjoblevel','工作级别-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,42,1,0,NULL,'2016-09-18 17:31:32'),
	(112,15,'gunitnature','单位性质-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,41,1,0,NULL,'2016-09-18 17:31:32'),
	(111,15,'gunitname','单位名称-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,40,1,0,NULL,'2016-09-18 17:31:32'),
	(110,15,'job','职业',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,39,1,0,NULL,'2016-09-18 17:31:32'),
	(109,15,'familyaddress','家庭地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,38,1,0,NULL,'2016-09-18 17:31:32'),
	(108,15,'marriagestatus','婚姻状态',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,37,1,0,NULL,'2016-09-18 17:31:32'),
	(107,15,'homestatus','置业状况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,36,1,0,NULL,'2016-09-18 17:31:32'),
	(106,15,'applyamount','申请金额',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,35,1,0,NULL,'2016-09-18 17:31:32'),
	(105,15,'loanproduct','贷款产品',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,34,1,0,NULL,'2016-09-18 17:31:32'),
	(104,15,'fristpayratio','首付比例',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,33,1,0,NULL,'2016-09-18 17:31:32'),
	(103,15,'buyhouseamount','购房金额',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,32,1,0,NULL,'2016-09-18 17:31:32'),
	(102,15,'familycount','家庭人口数量',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,31,1,0,NULL,'2016-09-18 17:31:32'),
	(101,15,'salaryway','工资发放形式',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,30,1,0,NULL,'2016-09-18 17:31:32'),
	(100,15,'domloction','户籍所在地',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,29,1,0,NULL,'2016-09-18 17:31:32'),
	(99,15,'health','健康状况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,28,1,0,NULL,'2016-09-18 17:31:32'),
	(98,15,'higdegree','最高学位',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,27,1,0,NULL,'2016-09-18 17:31:32'),
	(97,15,'pidunit','发证机关',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,26,1,0,NULL,'2016-09-18 17:31:32'),
	(96,15,'pidvaildity','证件有效期',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,25,1,0,NULL,'2016-09-18 17:31:32'),
	(95,15,'pidtype','证件类型',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,24,1,0,NULL,'2016-09-18 17:31:32'),
	(94,15,'nation','民族',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,23,1,0,NULL,'2016-09-18 17:31:32'),
	(93,15,'natiomality','国籍',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,22,1,0,NULL,'2016-09-18 17:31:32'),
	(211,16,'create_time','创建时间',120,'timestamp','easyui-textbox',1,1,1,1,0,0,'',14,1,0,NULL,'2016-09-18 17:31:55'),
	(208,16,'change_ratio','涨跌幅',120,'double','easyui-textbox',1,1,1,1,0,1,'',11,1,0,NULL,'2016-09-18 17:31:55'),
	(207,16,'change_amount','涨跌额',120,'double','easyui-textbox',1,1,1,1,0,1,'',10,1,0,NULL,'2016-09-18 17:31:55'),
	(206,16,'pre','前收盘',120,'double','easyui-textbox',1,1,1,1,0,1,'',9,1,0,NULL,'2016-09-18 17:31:55'),
	(205,16,'opening_price','开盘价',120,'double','easyui-textbox',1,1,1,1,0,1,'',8,2,0,NULL,'2016-09-18 17:31:55'),
	(204,16,'minimum_price','最低价',120,'double','easyui-textbox',1,1,1,1,1,1,'',7,2,0,NULL,'2016-09-18 17:31:55'),
	(202,16,'closing_price','收盘价',120,'double','easyui-textbox',1,1,1,1,1,1,'',5,2,0,NULL,'2016-09-18 17:31:55'),
	(203,16,'top_price','最高价',120,'double','easyui-textbox',1,1,1,1,1,1,'',6,2,0,NULL,'2016-09-18 17:31:55'),
	(200,16,'code','股票代码',120,'varchar','easyui-textbox',1,1,1,1,0,1,'',3,1,0,NULL,'2016-09-18 17:31:55'),
	(92,15,'birthday','出生年月',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,21,1,0,NULL,'2016-09-18 17:31:32'),
	(91,15,'sex','客户性别',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,20,1,0,NULL,'2016-09-18 17:31:32'),
	(90,15,'etime','贷款结束时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,19,1,0,NULL,'2016-09-18 17:31:32'),
	(89,15,'stime','贷款开始时间',120,'date','easyui-textbox',1,1,1,1,0,1,NULL,18,1,0,NULL,'2016-09-18 17:31:32'),
	(88,15,'price','贷款金额',120,'int','easyui-textbox',1,1,1,1,0,0,NULL,17,1,0,NULL,'2016-09-18 17:31:32'),
	(87,15,'ordercode','订单id',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,16,1,0,NULL,'2016-09-18 17:31:32'),
	(86,15,'tempcontent','修改数据',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,15,1,0,NULL,'2016-09-18 17:31:32'),
	(85,15,'score','按模型计算得分',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,14,1,0,NULL,'2016-09-18 17:31:32'),
	(84,15,'mid','使用模型',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,13,1,0,NULL,'2016-09-18 17:31:32'),
	(83,15,'status','状态',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,12,1,0,NULL,'2016-09-18 17:31:32'),
	(82,15,'type','客户类型',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,11,1,0,NULL,'2016-09-18 17:31:32'),
	(81,15,'pname','项目名',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,10,1,0,NULL,'2016-09-18 17:31:32'),
	(80,15,'pid','项目id',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,9,1,0,NULL,'2016-09-18 17:31:32'),
	(79,15,'cityname','城市名',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,8,1,0,NULL,'2016-09-18 17:31:32'),
	(78,15,'cityid','城市id',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,7,1,0,NULL,'2016-09-18 17:31:32'),
	(77,15,'mobile','客户手机号码',120,'varchar','easyui-textbox',1,1,1,1,1,1,'',6,1,0,NULL,'2016-09-18 17:31:32'),
	(76,15,'idcard','客户身份证',120,'varchar','easyui-textbox',1,1,1,1,1,1,'',5,1,0,NULL,'2016-09-18 17:31:32'),
	(75,15,'name','客户姓名',120,'varchar','easyui-textbox',1,1,1,1,1,1,'',4,1,0,NULL,'2016-09-18 17:31:32'),
	(74,15,'parentid','主借人id',120,'int','easyui-textbox',1,1,1,1,1,1,'',3,1,0,NULL,'2016-09-18 17:31:32'),
	(73,15,'source','客户来源',120,'int','easyui-textbox',1,1,1,1,1,1,'',2,1,0,NULL,'2016-09-18 17:31:32'),
	(72,15,'cid','主键客户信息表',120,'int','easyui-textbox',1,1,1,1,0,0,'',1,1,0,NULL,'2016-09-18 17:31:32'),
	(201,16,'name','名称',120,'varchar','easyui-textbox',1,1,1,1,0,1,'',4,1,0,NULL,'2016-09-18 17:31:55'),
	(199,16,'dt','日期',120,'date','easyui-datebox',1,1,1,1,1,1,'',2,2,0,NULL,'2016-09-18 17:31:55'),
	(198,16,'id','id',120,'int','easyui-textbox',1,1,1,1,0,0,'',1,1,0,NULL,'2016-09-18 17:31:55'),
	(218,17,'last_update_time','最后更新时间',120,'datetime','easyui-datebox',0,1,0,1,0,1,'',7,1,0,NULL,'2016-09-18 17:40:23'),
	(217,17,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,1,1,1,0,'',6,2,0,NULL,'2016-09-18 17:40:23'),
	(216,17,'status','状态',100,'int','easyui-combobox',1,1,1,1,1,1,'[dbSource=zcurd_busi]select \'0\', \'未采集\' union all select \'1\', \'采集中\' union all select \'2\', \'采集完\'',5,1,0,NULL,'2016-09-18 17:40:23'),
	(215,17,'url','阅读地址',250,'varchar','easyui-textbox',1,1,1,1,0,1,'',4,1,0,NULL,'2016-09-18 17:40:23'),
	(214,17,'book_name','书名',120,'varchar','easyui-textbox',1,1,1,1,1,1,'',3,1,0,NULL,'2016-09-18 17:40:23'),
	(213,17,'source','来源',120,'varchar','easyui-textbox',1,1,1,1,0,1,'',2,1,0,NULL,'2016-09-18 17:40:23'),
	(212,17,'id','id',120,'int','easyui-textbox',1,1,1,1,0,0,'',1,1,0,NULL,'2016-09-18 17:40:23'),
	(10000,1,'head_id','所属表单',100,'int','easyui-combobox',1,1,1,1,1,0,'select id, form_name from zcurd_head',1,1,0,NULL,'2016-09-27 00:06:53'),
	(3,1,'btn_name','按钮名称',80,'varchar','easyui-textbox',1,1,1,1,0,0,'',2,1,0,NULL,'2016-01-11 21:58:48'),
	(35,6,'create_time','创建时间',120,'timestamp','easyui-datebox',0,0,0,0,0,0,'',6,1,0,NULL,'2016-02-25 23:55:58'),
	(25,5,'id','id',120,'int','easyui-numberspinner',1,1,1,1,0,0,'',0,1,0,NULL,'2016-02-14 16:35:56'),
	(29,5,'method_name','后台method名称',120,'varchar','easyui-textbox',1,1,1,1,0,1,'',4,1,0,NULL,'2016-02-14 16:35:56'),
	(195,15,'overfueremark','逾期记录',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,124,1,0,NULL,'2016-09-18 17:31:32'),
	(196,15,'cheLoanMoney','cheLoanMoney',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,125,1,0,NULL,'2016-09-18 17:31:32'),
	(197,15,'houseLoanMoney','houseLoanMoney',120,'int','easyui-textbox',1,1,1,1,0,1,NULL,126,1,0,NULL,'2016-09-18 17:31:32'),
	(10001,2,'sql_content','扩展sql',120,'varchar','textarea',1,1,1,1,0,1,'',3,1,0,NULL,'2016-11-19 16:08:05'),
	(9,2,'head_id','所属表单',120,'int','easyui-combobox',1,1,1,1,1,0,'select id, form_name from zcurd_head',1,1,0,NULL,'2016-01-12 15:35:01'),
	(10004,18,'url','图片地址',120,'varchar','easyui-filebox_img',1,1,1,1,0,0,'',3,1,0,NULL,'2016-12-20 00:17:53'),
	(10005,18,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,0,0,0,0,'',4,1,0,NULL,'2016-12-20 00:17:53'),
	(10003,18,'name','图片名称',120,'varchar','easyui-textbox',1,1,1,1,1,0,'',2,1,0,NULL,'2016-12-20 00:17:53'),
	(10002,18,'id','id',120,'int unsigned','easyui-textbox',1,1,1,1,0,0,'',1,1,0,NULL,'2016-12-20 00:17:53'),
	(10024,20,'turnover_volume','成交量',120,'bigint','easyui-numberspinner',1,1,1,1,0,1,'',12,1,0,'','2016-12-21 23:07:45'),
	(10023,20,'change_ratio','涨跌幅',120,'double','easyui-textbox',1,1,1,1,0,1,'',11,1,0,'','2016-12-21 23:07:45'),
	(10022,20,'change_amount','涨跌额',120,'double','easyui-textbox',1,1,1,1,0,1,'',10,1,0,'','2016-12-21 23:07:45'),
	(10021,20,'pre','前收盘',120,'double','easyui-textbox',1,1,1,1,0,1,'',9,1,0,'','2016-12-21 23:07:45'),
	(10020,20,'opening_price','开盘价',120,'double','easyui-textbox',1,1,1,1,0,1,'',8,1,0,'','2016-12-21 23:07:45'),
	(10019,20,'minimum_price','最低价',120,'double','easyui-textbox',1,1,1,1,0,1,'',7,1,0,'','2016-12-21 23:07:45'),
	(10018,20,'top_price','最高价',120,'double','easyui-textbox',1,1,1,1,0,1,'',6,1,0,'','2016-12-21 23:07:45'),
	(10017,20,'closing_price','收盘价',120,'double','easyui-textbox',1,1,1,1,0,1,'',5,1,0,'','2016-12-21 23:07:45'),
	(10016,20,'name','名称',120,'varchar','easyui-textbox',1,1,1,1,0,1,'',4,1,0,'','2016-12-21 23:07:45'),
	(10015,20,'code','股票代码',120,'varchar','easyui-textbox',1,1,1,1,0,1,'',3,1,0,'','2016-12-21 23:07:45'),
	(10032,21,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,0,0,0,1,'',6,1,0,NULL,'2016-12-21 23:09:46'),
	(10033,21,'last_update_time','最后更新时间',120,'datetime','easyui-datebox',1,1,0,1,0,1,'',7,1,0,NULL,'2016-12-21 23:09:46'),
	(10031,21,'status','状态',120,'int','easyui-combobox',1,1,1,1,1,0,'[dbSource=zcurd_busi]select \'0\', \'未采集\' union all select \'1\', \'采集中\' union all select \'2\', \'采集完\'',5,1,0,NULL,'2016-12-21 23:09:46'),
	(10030,21,'url','url',120,'varchar','easyui-textbox',1,1,1,1,0,0,'',4,1,0,NULL,'2016-12-21 23:09:46'),
	(10029,21,'book_name','书名',120,'varchar','easyui-textbox',1,1,1,1,1,0,'',3,1,0,NULL,'2016-12-21 23:09:46'),
	(10028,21,'source','来源',120,'varchar','easyui-textbox',1,1,1,1,0,0,'',2,1,0,NULL,'2016-12-21 23:09:46'),
	(10027,21,'id','id',120,'int','easyui-numberspinner',1,1,1,1,0,0,'',1,1,0,NULL,'2016-12-21 23:09:46'),
	(10037,22,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,0,0,0,0,'',4,1,0,'','2016-12-21 23:12:51'),
	(10036,22,'url','图片地址',120,'varchar','easyui-filebox_img',1,1,1,1,0,0,'',3,1,0,'','2016-12-21 23:12:51'),
	(10035,22,'name','图片名称',120,'varchar','easyui-textbox',1,1,0,1,0,0,'',2,1,0,'${user.id}','2016-12-21 23:12:51'),
	(10034,22,'id','id',120,'int unsigned','easyui-textbox',1,1,1,1,0,0,'',1,1,0,'','2016-12-21 23:12:51'),
	(10038,1,'btn_icon','按钮图标',80,'varchar','easyui-textbox',1,1,1,1,0,0,'',3,1,0,NULL,'2017-01-27 15:38:07'),
	(10044,23,'create_user_id','创建人',120,'int','easyui-numberspinner',0,1,0,0,0,1,'',10,1,0,'${user.id}','2017-03-04 16:10:22'),
	(10047,23,'status','状态',80,'int','easyui-combobox',1,1,1,1,1,1,'select dict_key, dict_value from sys_dict where dict_type=\'task_statu\'',9,1,0,'2','2017-03-04 16:10:22'),
	(10046,23,'last_run_time_cost','上次执行耗时',90,'int','easyui-numberspinner',1,1,0,0,0,1,'',8,1,0,'','2017-03-04 16:10:22'),
	(10045,23,'last_run_time','上次执行时间',120,'datetime','easyui-datebox',1,1,0,0,0,1,'',7,1,0,'','2017-03-04 16:10:22'),
	(10049,23,'last_run_result','上次执行结果',90,NULL,'easyui-textbox',1,1,0,0,0,1,'',5,1,0,'','2017-03-12 23:52:52'),
	(10043,23,'cron','cron表达式',100,'varchar','easyui-textbox',1,1,1,1,0,0,'',4,1,0,'','2017-03-04 16:10:22'),
	(10040,23,'name','名称',120,'varchar','easyui-textbox',1,1,1,1,1,0,'',1,1,0,'','2017-03-04 16:10:22'),
	(10041,23,'target_type','任务类型',70,'int','easyui-combobox',1,1,1,1,1,0,'select dict_key, dict_value from sys_dict where dict_type=\'task_type\'',2,1,0,'','2017-03-04 16:10:22'),
	(10042,23,'target_value','任务值',140,'varchar','easyui-textbox',1,1,1,1,0,0,'',3,1,0,'','2017-03-04 16:10:22'),
	(10039,23,'id','id',120,'int','easyui-numberspinner',1,1,1,1,0,0,'',0,1,0,'','2017-03-04 16:10:22'),
	(10048,23,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,0,0,0,1,'',10,1,0,'','2017-03-04 16:10:22'),
	(10025,20,'turnover_money','成交金额',120,'bigint','easyui-numberspinner',1,1,1,1,0,1,'',13,1,0,'','2016-12-21 23:07:45'),
	(10050,24,'id','id',120,'int','easyui-numberspinner',1,1,0,0,0,0,'',1,1,0,'','2017-03-15 00:07:20'),
	(10051,24,'task_id','所属任务',120,'int','easyui-combobox',1,1,0,0,1,1,'select id, name from task_base',2,1,0,'','2017-03-15 00:07:20'),
	(10052,24,'result','执行结果',80,'varchar','easyui-textbox',1,1,0,0,0,1,'',3,1,0,'','2017-03-15 00:07:20'),
	(10053,24,'start_time','开始时间',130,'datetime','easyui-datebox',1,1,0,0,0,1,'',4,1,0,'','2017-03-15 00:07:20'),
	(10054,24,'end_time','结束时间',130,'datetime','easyui-datebox',0,1,0,0,0,1,'',5,1,0,'','2017-03-15 00:07:20'),
	(10055,24,'cost_time','耗时(毫秒)',80,'int','easyui-numberspinner',1,1,0,0,0,1,'',6,1,0,'','2017-03-15 00:07:20'),
	(10056,24,'remark','备注',120,'varchar','easyui-textbox',1,1,0,0,0,1,'',7,1,0,'','2017-03-15 00:07:20'),
	(10014,20,'dt','日期',120,'date','easyui-datebox',1,1,1,1,1,1,'',2,1,0,'','2016-12-21 23:07:45'),
	(10013,20,'id','id',120,'int','easyui-numberspinner',1,1,1,1,0,0,'',1,1,0,'','2016-12-21 23:07:45'),
	(10026,20,'create_time','创建时间',120,'timestamp','easyui-datebox',1,1,0,0,0,0,'',14,2,0,'','2016-12-21 23:07:45'),
	(10159,25,'othertime','othertime',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,103,1,0,NULL,'2017-05-08 14:06:25'),
	(10158,25,'otheramount','加他付款方式',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,102,1,0,NULL,'2017-05-08 14:06:25'),
	(10157,25,'transfertime','转账时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,101,1,0,NULL,'2017-05-08 14:06:25'),
	(10156,25,'transferamount','转账金额',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,100,1,0,NULL,'2017-05-08 14:06:25'),
	(10155,25,'cashtime','现金付款时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,99,1,0,NULL,'2017-05-08 14:06:25'),
	(10154,25,'cashamount','现金金额',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,98,1,0,NULL,'2017-05-08 14:06:25'),
	(10153,25,'postime','pos刷卡时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,97,1,0,NULL,'2017-05-08 14:06:25'),
	(10152,25,'posamount','放款金额',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,96,1,0,NULL,'2017-05-08 14:06:25'),
	(10151,25,'recviway','还款方式1 pos机器 2现金  3转账  4其他',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,95,1,0,NULL,'2017-05-08 14:06:25'),
	(10150,25,'loantime','放款时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,94,1,0,NULL,'2017-05-08 14:06:25'),
	(10149,25,'loanstatus','放款状态',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,93,1,0,NULL,'2017-05-08 14:06:25'),
	(10148,25,'signtime','签约时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,92,1,0,NULL,'2017-05-08 14:06:25'),
	(10147,25,'signstatus','签约状态',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,91,1,0,NULL,'2017-05-08 14:06:25'),
	(10146,25,'repay','repay',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,90,1,0,NULL,'2017-05-08 14:06:25'),
	(10145,25,'period','period',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,89,1,0,NULL,'2017-05-08 14:06:25'),
	(10144,25,'personPath','personPath',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,88,1,0,NULL,'2017-05-08 14:06:25'),
	(10143,25,'fkid','fkid',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,87,1,0,NULL,'2017-05-08 14:06:25'),
	(10142,25,'examindate','报审时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,86,1,0,NULL,'2017-05-08 14:06:25'),
	(10141,25,'fundname','fundname',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,85,1,0,NULL,'2017-05-08 14:06:25'),
	(10140,25,'fund','fund',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,84,1,0,NULL,'2017-05-08 14:06:25'),
	(10139,25,'loantimelimit','loantimelimit',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,83,1,0,NULL,'2017-05-08 14:06:25'),
	(10138,25,'zcompanyaddress','zcompanyaddress',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,82,1,0,NULL,'2017-05-08 14:06:25'),
	(10137,25,'gunitaddress','gunitaddress',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,81,1,0,NULL,'2017-05-08 14:06:25'),
	(10136,25,'xsname','xsname',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,80,1,0,NULL,'2017-05-08 14:06:25'),
	(10135,25,'xsid','xsid',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,79,1,0,NULL,'2017-05-08 14:06:25'),
	(10134,25,'relations','relations',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,78,1,0,NULL,'2017-05-08 14:06:25'),
	(10133,25,'creditLoanMoney','creditLoanMoney',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,77,1,0,NULL,'2017-05-08 14:06:25'),
	(10132,25,'zenterpborrow','zenterpborrow',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,76,1,0,NULL,'2017-05-08 14:06:25'),
	(10131,25,'creditNumber','creditNumber',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,75,1,0,NULL,'2017-05-08 14:06:25'),
	(10130,25,'aname','aname',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,74,1,0,NULL,'2017-05-08 14:06:25'),
	(10129,25,'ctime','ctime',120,'datetime','easyui-datebox',1,1,1,1,0,1,NULL,73,1,0,NULL,'2017-05-08 14:06:25'),
	(10128,25,'adid','adid',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,72,1,0,NULL,'2017-05-08 14:06:25'),
	(10127,25,'contposition','紧急联系人工作职位',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,71,1,0,NULL,'2017-05-08 14:06:25'),
	(10126,25,'contworkdep','紧急联系人工作部门',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,70,1,0,NULL,'2017-05-08 14:06:25'),
	(10125,25,'contunitaddress','紧急联系人单位地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,69,1,0,NULL,'2017-05-08 14:06:25'),
	(10124,25,'contunitphone','紧急联系人单位电话',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,68,1,0,NULL,'2017-05-08 14:06:25'),
	(10123,25,'contunitname','紧急联系人单位名称',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,67,1,0,NULL,'2017-05-08 14:06:25'),
	(10122,25,'contaddress','紧急联系人地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,66,1,0,NULL,'2017-05-08 14:06:25'),
	(10121,25,'contphone','紧急联系人住宅电话',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,65,1,0,NULL,'2017-05-08 14:06:25'),
	(10120,25,'contmobile','紧急联系人电话',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,64,1,0,NULL,'2017-05-08 14:06:25'),
	(10119,25,'contloan','经济联系人是否知晓贷款',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,63,1,0,NULL,'2017-05-08 14:06:25'),
	(10118,25,'contrelation','紧急联系人关系',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,62,1,0,NULL,'2017-05-08 14:06:25'),
	(10117,25,'contsex','紧急联系人性别',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,61,1,0,NULL,'2017-05-08 14:06:25'),
	(10116,25,'contage','紧急联系人年龄',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,60,1,0,NULL,'2017-05-08 14:06:25'),
	(10115,25,'contname','紧急联系人姓名',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,59,1,0,NULL,'2017-05-08 14:06:25'),
	(10114,25,'otherloan','其他贷款',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,58,1,0,NULL,'2017-05-08 14:06:25'),
	(10113,25,'creditconsume','信用卡已消费',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,57,1,0,NULL,'2017-05-08 14:06:25'),
	(10112,25,'creditlimit','信用卡最高额度',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,56,1,0,NULL,'2017-05-08 14:06:25'),
	(10111,25,'bankaccount','用户银行账户',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,55,1,0,NULL,'2017-05-08 14:06:25'),
	(10110,25,'age','年龄',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,54,1,0,NULL,'2017-05-08 14:06:25'),
	(10109,25,'zsharesratio','占股比例-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,53,1,0,NULL,'2017-05-08 14:06:25'),
	(10108,25,'zmanageplace','经营场所-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,52,1,0,NULL,'2017-05-08 14:06:25'),
	(10107,25,'zenterploan','企业借款-自雇',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,51,1,0,NULL,'2017-05-08 14:06:25'),
	(10106,25,'zenterpfund','企业资产-自雇',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,50,1,0,NULL,'2017-05-08 14:06:25'),
	(10105,25,'zyearbus','年营业额-自雇',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,49,1,0,NULL,'2017-05-08 14:06:25'),
	(10104,25,'zmonincome','月收入—自雇',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,48,1,0,NULL,'2017-05-08 14:06:25'),
	(10103,25,'zindustrytype','行业类型-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,47,1,0,NULL,'2017-05-08 14:06:25'),
	(10102,25,'zcompanyname','企业名-自雇',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,46,1,0,NULL,'2017-05-08 14:06:25'),
	(10101,25,'gsalaryway','工作发放形式-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,45,1,0,NULL,'2017-05-08 14:06:25'),
	(10100,25,'gworklife','工作年限-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,44,1,0,NULL,'2017-05-08 14:06:25'),
	(10099,25,'gmonincome','月收入-工薪',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,43,1,0,NULL,'2017-05-08 14:06:25'),
	(10098,25,'gjoblevel','工作级别-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,42,1,0,NULL,'2017-05-08 14:06:25'),
	(10097,25,'gunitnature','单位性质-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,41,1,0,NULL,'2017-05-08 14:06:25'),
	(10096,25,'gunitname','单位名称-工薪',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,40,1,0,NULL,'2017-05-08 14:06:25'),
	(10095,25,'job','职业',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,39,1,0,NULL,'2017-05-08 14:06:25'),
	(10094,25,'familyaddress','家庭地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,38,1,0,NULL,'2017-05-08 14:06:25'),
	(10093,25,'marriagestatus','婚姻状态',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,37,1,0,NULL,'2017-05-08 14:06:25'),
	(10092,25,'homestatus','置业状况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,36,1,0,NULL,'2017-05-08 14:06:25'),
	(10091,25,'applyamount','申请金额',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,35,1,0,NULL,'2017-05-08 14:06:25'),
	(10090,25,'loanproduct','贷款产品',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,34,1,0,NULL,'2017-05-08 14:06:25'),
	(10089,25,'fristpayratio','首付比例',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,33,1,0,NULL,'2017-05-08 14:06:25'),
	(10088,25,'buyhouseamount','购房金额',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,32,1,0,NULL,'2017-05-08 14:06:25'),
	(10087,25,'familycount','家庭人口数量',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,31,1,0,NULL,'2017-05-08 14:06:25'),
	(10086,25,'salaryway','工资发放形式',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,30,1,0,NULL,'2017-05-08 14:06:25'),
	(10085,25,'domloction','户籍所在地',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,29,1,0,NULL,'2017-05-08 14:06:25'),
	(10084,25,'health','健康状况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,28,1,0,NULL,'2017-05-08 14:06:25'),
	(10083,25,'higdegree','最高学位',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,27,1,0,NULL,'2017-05-08 14:06:25'),
	(10082,25,'pidunit','发证机关',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,26,1,0,NULL,'2017-05-08 14:06:25'),
	(10081,25,'pidvaildity','证件有效期',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,25,1,0,NULL,'2017-05-08 14:06:25'),
	(10080,25,'pidtype','证件类型',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,24,1,0,NULL,'2017-05-08 14:06:25'),
	(10079,25,'nation','民族',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,23,1,0,NULL,'2017-05-08 14:06:25'),
	(10078,25,'natiomality','国籍',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,22,1,0,NULL,'2017-05-08 14:06:25'),
	(10077,25,'birthday','出生年月',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,21,1,0,NULL,'2017-05-08 14:06:25'),
	(10076,25,'sex','客户性别',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,20,1,0,NULL,'2017-05-08 14:06:25'),
	(10075,25,'etime','贷款结束时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,19,1,0,NULL,'2017-05-08 14:06:25'),
	(10074,25,'stime','贷款开始时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,18,1,0,NULL,'2017-05-08 14:06:25'),
	(10073,25,'price','贷款金额',120,'int','easyui-numberspinner',1,1,1,1,0,0,NULL,17,1,0,NULL,'2017-05-08 14:06:25'),
	(10072,25,'ordercode','订单id',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,16,1,0,NULL,'2017-05-08 14:06:25'),
	(10071,25,'tempcontent','修改数据',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,15,1,0,NULL,'2017-05-08 14:06:25'),
	(10070,25,'score','按模型计算得分',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,14,1,0,NULL,'2017-05-08 14:06:25'),
	(10069,25,'mid','使用模型',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,13,1,0,NULL,'2017-05-08 14:06:25'),
	(10068,25,'status','状态',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,12,1,0,NULL,'2017-05-08 14:06:25'),
	(10067,25,'type','客户类型',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,11,1,0,NULL,'2017-05-08 14:06:25'),
	(10066,25,'pname','项目名',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,10,1,0,NULL,'2017-05-08 14:06:25'),
	(10065,25,'pid','项目id',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,9,1,0,NULL,'2017-05-08 14:06:25'),
	(10064,25,'cityname','城市名',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,8,1,0,NULL,'2017-05-08 14:06:25'),
	(10063,25,'cityid','城市id',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,7,1,0,NULL,'2017-05-08 14:06:25'),
	(10062,25,'mobile','客户手机号码',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,6,1,0,NULL,'2017-05-08 14:06:25'),
	(10061,25,'idcard','客户身份证',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,5,1,0,NULL,'2017-05-08 14:06:25'),
	(10060,25,'name','客户姓名',120,'varchar','easyui-textbox',1,1,1,1,1,1,'',4,1,0,'','2017-05-08 14:06:25'),
	(10059,25,'parentid','主借人id',120,'int','easyui-numberspinner',1,1,1,1,1,1,'',3,1,0,'','2017-05-08 14:06:25'),
	(10058,25,'source','客户来源',120,'int','easyui-numberspinner',1,1,1,1,1,1,'',2,1,0,'','2017-05-08 14:06:25'),
	(10057,25,'cid','主键客户信息表',120,'int','easyui-numberspinner',1,1,1,1,0,0,NULL,1,1,0,NULL,'2017-05-08 14:06:25'),
	(10160,25,'urgentlevel','紧急程度',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,104,1,0,NULL,'2017-05-08 14:06:25'),
	(10161,25,'applyloandate','申请放款时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,105,1,0,NULL,'2017-05-08 14:06:25'),
	(10162,25,'accountbranch','开户支行',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,106,1,0,NULL,'2017-05-08 14:06:25'),
	(10163,25,'loanbankaccount','还款银行卡号',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,107,1,0,NULL,'2017-05-08 14:06:25'),
	(10164,25,'houseConsultant','职业顾问',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,108,1,0,NULL,'2017-05-08 14:06:25'),
	(10165,25,'consultantphone','顾问号码',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,109,1,0,NULL,'2017-05-08 14:06:25'),
	(10166,25,'refundamount','退款金额',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,110,1,0,NULL,'2017-05-08 14:06:25'),
	(10167,25,'refundtime','退款时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,111,1,0,NULL,'2017-05-08 14:06:25'),
	(10168,25,'loanMoney','loanMoney',120,'double','easyui-textbox',1,1,1,1,0,1,NULL,112,1,0,NULL,'2017-05-08 14:06:25'),
	(10169,25,'creditLevel','信用卡等级',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,113,1,0,NULL,'2017-05-08 14:06:25'),
	(10170,25,'buyhouseaddress','客户新买房新地址',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,114,1,0,NULL,'2017-05-08 14:06:25'),
	(10171,25,'gjrelation','共借人关系',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,115,1,0,NULL,'2017-05-08 14:06:25'),
	(10172,25,'approvalopinion','审批意见',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,116,1,0,NULL,'2017-05-08 14:06:25'),
	(10173,25,'archstatus','资料归档情况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,117,1,0,NULL,'2017-05-08 14:06:25'),
	(10174,25,'archtime','资料归档时间',120,'date','easyui-datebox',1,1,1,1,0,1,NULL,118,1,0,NULL,'2017-05-08 14:06:25'),
	(10175,25,'archremark','资料归档备注',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,119,1,0,NULL,'2017-05-08 14:06:25'),
	(10176,25,'mortgagestatus','按揭情况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,120,1,0,NULL,'2017-05-08 14:06:25'),
	(10177,25,'mortgageremark','按揭备注',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,121,1,0,NULL,'2017-05-08 14:06:25'),
	(10178,25,'repaystatus','还款情况',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,122,1,0,NULL,'2017-05-08 14:06:25'),
	(10179,25,'overduecount','逾期次数',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,123,1,0,NULL,'2017-05-08 14:06:25'),
	(10180,25,'overfueremark','逾期记录',120,'varchar','easyui-textbox',1,1,1,1,0,1,NULL,124,1,0,NULL,'2017-05-08 14:06:25'),
	(10181,25,'cheLoanMoney','cheLoanMoney',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,125,1,0,NULL,'2017-05-08 14:06:25'),
	(10182,25,'houseLoanMoney','houseLoanMoney',120,'int','easyui-numberspinner',1,1,1,1,0,1,NULL,126,1,0,NULL,'2017-05-08 14:06:25');

/*!40000 ALTER TABLE `zcurd_field` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table zcurd_head
# ------------------------------------------------------------

CREATE TABLE `zcurd_head` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(100) DEFAULT NULL COMMENT '数据库表名',
  `form_name` varchar(100) DEFAULT NULL COMMENT '表单名称',
  `id_field` varchar(50) DEFAULT 'id' COMMENT '主键字段',
  `is_auto` int(11) DEFAULT '1' COMMENT '是否自增',
  `form_type` int(11) DEFAULT '1' COMMENT '表单类型（1:单表,2:主从）',
  `dialog_size` varchar(50) NOT NULL DEFAULT '600x400' COMMENT '弹窗大小',
  `db_source` varchar(50) DEFAULT NULL COMMENT '数据源',
  `handle_class` varchar(100) DEFAULT NULL COMMENT '处理类',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在线表单';

LOCK TABLES `zcurd_head` WRITE;
/*!40000 ALTER TABLE `zcurd_head` DISABLE KEYS */;

INSERT INTO `zcurd_head` (`id`, `table_name`, `form_name`, `id_field`, `is_auto`, `form_type`, `dialog_size`, `db_source`, `handle_class`, `create_time`)
VALUES
	(1,'zcurd_head_btn','表单扩展按钮','id',1,1,'600x400','zcurd_base','com.zcurd.common.handler.FlushFormCurdHandle','2016-01-11 21:58:48'),
	(2,'zcurd_head_js','表单扩展js','id',1,1,'600x400','zcurd_base','com.zcurd.common.handler.FlushFormCurdHandle','2016-01-12 15:35:01'),
	(5,'sys_menu_btn','菜单按钮（权限编辑）','id',1,1,'400x300','zcurd_base',NULL,'2016-02-14 16:35:56'),
	(6,'sys_menu_datarule','菜单数据（权限编辑）','id',1,1,'400x300','zcurd_base',NULL,'2016-02-25 23:55:58'),
	(7,'sys_dict','数据字典','id',1,1,'400x300','zcurd_base',NULL,'2016-03-01 04:39:14'),
	(8,'sys_role','角色管理','id',1,1,'400x300','zcurd_base',NULL,'2016-02-12 01:58:39'),
	(18,'images','图片管理','id',1,1,'600x400','zcurd_busi',NULL,'2016-12-20 00:17:53'),
	(12,'sys_user','用户管理','id',1,1,'400x300','zcurd_base','com.zcurd.common.handler.PasswordCurdHandle','2016-01-07 21:31:45'),
	(15,'customer','客户管理','cid',1,1,'800x600','zcurd_busi',NULL,'2016-09-18 17:31:32'),
	(16,'stock_history_log','股票历史数据','id',1,1,'700x500','zcurd_busi',NULL,'2016-09-18 17:31:55'),
	(17,'claw_book_url','小说管理','id',1,1,'600x400','zcurd_busi',NULL,'2016-09-18 17:40:23'),
	(20,'stock_history_log','股票历史数据','id',1,1,'700x500','zcurd_busi',NULL,'2016-12-21 23:07:45'),
	(21,'claw_book_url','小说链接','id',1,1,'600x400','zcurd_busi',NULL,'2016-12-21 23:09:46'),
	(22,'images','图片管理','id',1,1,'600x400','zcurd_busi',NULL,'2016-12-21 23:12:51'),
	(23,'task_base','定时任务','id',1,1,'600x400','zcurd_base',NULL,'2017-03-04 16:10:22'),
	(24,'task_log','定时任务-日志','id',1,1,'600x400','zcurd_base',NULL,'2017-03-15 00:07:20'),
	(25,'customer','客户管理','cid',1,1,'600x400','zcurd_busi',NULL,'2017-05-08 14:06:25');

/*!40000 ALTER TABLE `zcurd_head` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table zcurd_head_btn
# ------------------------------------------------------------

CREATE TABLE `zcurd_head_btn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `head_id` int(11) DEFAULT NULL COMMENT '所属表单',
  `btn_name` varchar(50) DEFAULT NULL COMMENT '按钮名称',
  `btn_icon` varchar(50) DEFAULT NULL COMMENT '按钮图标',
  `location` int(11) DEFAULT '1' COMMENT '按钮位置（1：顶部，2：行内）',
  `action` int(11) DEFAULT '1' COMMENT '行为（0：无，1：打开子页面）',
  `func_content` text COMMENT '方法内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='表单按钮';

LOCK TABLES `zcurd_head_btn` WRITE;
/*!40000 ALTER TABLE `zcurd_head_btn` DISABLE KEYS */;

INSERT INTO `zcurd_head_btn` (`id`, `head_id`, `btn_name`, `btn_icon`, `location`, `action`, `func_content`, `create_time`)
VALUES
	(1,8,'权限设置',NULL,2,1,'function(index) {\r\n var row = datagrid.datagrid(\"getRows\")[index];\r\n  //var url = basePath + \"/zcurd/135/listPage?riskgradeid=\" + row.id;\r\n var url = basePath + \"/role/editAuthPage?roleId=\" + row.id;\r\n _openSubPage(url);\r\n}','2016-02-12 21:23:10'),
	(2,134,'管理',NULL,2,1,'function(index) {\n var row = datagrid.datagrid(\"getRows\")[index];\n  var url = basePath + \"/zcurd/135/listPage?riskgradeid=\" + row.id;\n _openSubPage(url);\n}','2016-01-12 13:28:50'),
	(3,133,'测试',NULL,2,0,'function(){}','2016-01-13 09:30:35'),
	(4,12,'重置密码','glyphicon-repeat',1,0,'function() {\n    var rowsSel = datagrid.datagrid(\"getSelections\");\n	if(rowsSel.length != 1) {\n		showWarnMsg(\"请选择需要重置密码的一条数据！\");\n		return;\n	}\n	confirmMsg(\"确认重置密码？\", function() {\n		var id = rowsSel[0].id;\n		$.post(basePath + \"/user/resetPassword\", {id: id}, function() {\n			showMsg(\"密码重置成功！\");\n		});\n	});\n}','2017-01-27 13:44:37'),
	(5,23,'停止','xx',2,0,'function() {\n\n}','2017-03-12 23:12:56'),
	(6,22,'aaabbbcc','aaa',1,0,'function() {}','2017-04-12 00:46:35');

/*!40000 ALTER TABLE `zcurd_head_btn` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table zcurd_head_js
# ------------------------------------------------------------

CREATE TABLE `zcurd_head_js` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `head_id` int(11) DEFAULT NULL COMMENT '所属表单',
  `page` varchar(50) DEFAULT NULL COMMENT '页面',
  `sql_content` varchar(2000) DEFAULT NULL COMMENT '扩展sql',
  `js_content` text COMMENT '扩展js',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='表单扩展js';

LOCK TABLES `zcurd_head_js` WRITE;
/*!40000 ALTER TABLE `zcurd_head_js` DISABLE KEYS */;

INSERT INTO `zcurd_head_js` (`id`, `head_id`, `page`, `sql_content`, `js_content`, `create_time`)
VALUES
	(1,134,'list',NULL,'var operateWidth = 80;\r\nvar subPageWidth = \"50%\";\r\nvar subPageTitle = \"等级详情\";\r\ndgOptions.singleSelect=true;','2016-01-12 16:25:45'),
	(2,5,'list',NULL,'$(\"#searchBtnWrap\").hide();\n$(\".wrap_search\").hide();\ndgOptions.pageSize=1000;\ndgOptions.pagination=false;\n\nwindow.parent.getDgSelections = function() {\n    return datagrid.datagrid(\"getSelections\");\n}\n\ndgOptions.onLoadSuccess = selectAuthRow;\nfunction selectAuthRow() {\n  	var btnIds = window.parent.getCurrMenuBtns();\n 	var rows = datagrid.datagrid(\"getData\").rows;\n 	if(btnIds && rows.length > 0) {\n   		$.each(rows, function(i, item) {\n      		$.each(btnIds, function(j, btnId) {\n	       		if(item.id == btnId) {\n	          		datagrid.datagrid(\"selectRow\", i);\n	        	}\n     		});\n   		});\n 	}\n\n 	//如果无数据，则显示一键生成\n	if(rows.length == 0) {\n		genAuthBtnBatch();\n	}\n}\n\n//显示一键生成\nfunction genAuthBtnBatch() {\n	$(\"<button id=\'genAuthBtnBatchBtn\' style=\'position: fixed; top: 120px; left: 50%; margin-left: -30px; padding: 2px;\'>一键生成<button>\")\n	.linkbutton().click(function() {\n		$.post(\"../../role/genAuthBtnBatch\", {menuId: $(\"#menu_id\").val()}, function() {\n			$(\"#genAuthBtnBatchBtn\").remove();\n			showMsg(\"生成成功！\");\n			datagrid.datagrid(\"reload\");\n		});\n	}).appendTo(\"body\");\n}','2016-02-15 11:13:14'),
	(3,8,'list',NULL,'var operateWidth = 80;\nvar subPageWidth = \"55%\";\nvar subPageTitle = \"权限设置\";\ndgOptions.singleSelect=true;','2016-02-16 16:32:31'),
	(4,131,'update',NULL,'$(function() {\n  changeComboboxToMult(\"roles\");\n});','2016-02-23 23:09:57'),
	(5,12,'add',NULL,'$(function() {\n changeComboboxToMult(\"roles\");\n});','2016-02-23 23:10:03'),
	(6,152,'list',NULL,'$(\"#searchBtnWrap\").hide();\n$(\".wrap_search\").hide();\ndgOptions.pageSize=1000;\ndgOptions.pagination=false;\n\nwindow.parent.getDgSelections2 = function() {\n    return datagrid.datagrid(\"getSelections\");\n}\n\ndgOptions.onLoadSuccess = selectAuthRow;\nfunction selectAuthRow() {\n var dataruleIds = window.parent.getCurrMenuDatarules();\n var rows = datagrid.datagrid(\"getData\").rows;\n if(dataruleIds && rows.length > 0) {\n    $.each(rows, function(i, item) {\n      $.each(dataruleIds, function(j, dataruleId) {\n       if(item.id == dataruleId) {\n         datagrid.datagrid(\"selectRow\", i);\n        }\n     });\n   });\n }\n}','2016-02-15 11:13:14'),
	(7,6,'list',NULL,'$(\"#searchBtnWrap\").hide();\n$(\".wrap_search\").hide();\ndgOptions.pageSize=1000;\ndgOptions.pagination=false;\n\nwindow.parent.getDgSelections2 = function() {\n    return datagrid.datagrid(\"getSelections\");\n}\n\ndgOptions.onLoadSuccess = selectAuthRow;\nfunction selectAuthRow() {\n	var dataruleIds = window.parent.getCurrMenuDatarules();\n	var rows = datagrid.datagrid(\"getData\").rows;\n	if(dataruleIds && rows.length > 0) {\n		$.each(rows, function(i, item) {\n			$.each(dataruleIds, function(j, dataruleId) {\n				if(item.id == dataruleId) {\n					datagrid.datagrid(\"selectRow\", i);\n				}\n			});\n		});\n	}\n}','2016-09-27 00:17:56'),
	(8,12,'update,detail',NULL,'$(function() {\n changeComboboxToMult(\"roles\");\n});','2016-09-27 00:40:00'),
	(13,6,'update,add',NULL,'$(\"#value_label\").html(\"<a class=\'symbol_var\' title=\'支持变量\'>$</a>\" + $(\"#value_label\").html())','2017-02-26 00:39:55'),
	(9,2,'update,add',NULL,'$(function() {\n changeComboboxToMult(\"page\");\n});','2016-11-19 19:52:45'),
	(14,24,'list',NULL,'$(\"#searchBtnWrap\").hide();\n$(\".wrap_search\").hide();\n\n$(\"#tb\").remove();','2017-03-15 23:59:42');

/*!40000 ALTER TABLE `zcurd_head_js` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping routines (PROCEDURE) for database 'zcurd_base'
--
DELIMITER ;;

# Dump of PROCEDURE pro_test
# ------------------------------------------------------------

/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `pro_test`()
BEGIN
	select 1;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


# 图形验证码
CREATE TABLE `sys_login_log` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NULL DEFAULT NULL COMMENT '用户名',
	`session_id` VARCHAR(50) NULL DEFAULT NULL COMMENT 'session id',
	`ip` VARCHAR(100) NULL DEFAULT NULL COMMENT '登陆ip',
	`is_success` INT(11) NULL DEFAULT NULL COMMENT '是否成功（0：失败，1：成功）',
	`remark` VARCHAR(100) NULL DEFAULT NULL COMMENT '备注',
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='登陆日志'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=27;
