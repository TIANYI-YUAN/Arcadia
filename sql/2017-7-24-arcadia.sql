-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.22-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 arcadia 的数据库结构
CREATE DATABASE IF NOT EXISTS `arcadia` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `arcadia`;

-- 导出  表 arcadia.admin 结构
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminPw` char(50) DEFAULT NULL,
  `address` char(50) DEFAULT NULL,
  `mobile` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  arcadia.admin 的数据：~2 rows (大约)
DELETE FROM `admin`;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `adminPw`, `address`, `mobile`) VALUES
	(1, 'Yty199432', '12312', '231312311'),
	(2, 'smj931214', '963 ferngully', '1429132131');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 导出  表 arcadia.source_link 结构
CREATE TABLE IF NOT EXISTS `source_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sourcecode` varchar(50) DEFAULT NULL,
  `checkchain` varchar(50) DEFAULT NULL,
  `filename` text,
  `ifexpire` tinyint(4) DEFAULT '0',
  `expiretime` datetime DEFAULT NULL,
  `cookie` varchar(50) DEFAULT NULL,
  `readerip` varchar(50) DEFAULT NULL,
  `creatorId` int(11) DEFAULT NULL,
  `starred` int(11) DEFAULT '0',
  `shared` int(11) DEFAULT '0',
  `deleted` int(11) DEFAULT '0',
  `photo` int(11) DEFAULT '0',
  `width` tinytext,
  `height` tinytext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sourcecode` (`sourcecode`),
  KEY `FK_source_link_admin` (`creatorId`),
  CONSTRAINT `FK_source_link_admin` FOREIGN KEY (`creatorid`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  arcadia.source_link 的数据：~0 rows (大约)
DELETE FROM `source_link`;
/*!40000 ALTER TABLE `source_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `source_link` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
