/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.41 : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security`;

/*Table structure for table `log` */

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `project_id` bigint(20) NOT NULL COMMENT '项目ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_ip` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户IP',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '模块名称',
  `descr` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '操作描述',
  `type` int(2) NOT NULL COMMENT '操作类型 1.登陆 2:增 3:删 4:改 5:查',
  `content` text COLLATE utf8_unicode_ci COMMENT '内容',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `menu` */

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `project_id` bigint(20) NOT NULL COMMENT '所属项目ID',
  `type` int(11) NOT NULL COMMENT '类型 1:目录 2:菜单 3:按钮',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID,一级为null',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单URL,目录为#',
  `auth_iden` varchar(100) DEFAULT NULL COMMENT '授权标识',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `project` */

CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `name` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '项目名称',
  `project_url` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '项目URL',
  `reload_auth_url` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '/hessian/reload' COMMENT '刷新权限URI',
  `status` tinyint(1) NOT NULL COMMENT '状态 0:无效 1:有效',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `role` */

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统角色ID',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `descr` varchar(100) DEFAULT NULL COMMENT '备注',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `role_menu` */

CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色与菜单关系ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `phone` varchar(30) NOT NULL COMMENT '手机号',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `status` tinyint(1) NOT NULL COMMENT '状态 0:无效 1:有效',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `user_role` */

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户与角色关系ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
