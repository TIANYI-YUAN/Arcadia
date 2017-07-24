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

-- 导出  表 arcadia.photos 结构
CREATE TABLE IF NOT EXISTS `photos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `photo_code` tinytext NOT NULL,
  `photoName` text,
  `creatorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  arcadia.photos 的数据：~0 rows (大约)
DELETE FROM `photos`;
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
INSERT INTO `photos` (`id`, `photo_code`, `photoName`, `creatorId`) VALUES
	(1, 'ahd34awuid1', 'me.png', 1);
/*!40000 ALTER TABLE `photos` ENABLE KEYS */;

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `sourcecode` (`sourcecode`),
  KEY `FK_source_link_admin` (`creatorId`),
  CONSTRAINT `FK_source_link_admin` FOREIGN KEY (`creatorid`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- 正在导出表  arcadia.source_link 的数据：~15 rows (大约)
DELETE FROM `source_link`;
/*!40000 ALTER TABLE `source_link` DISABLE KEYS */;
INSERT INTO `source_link` (`id`, `sourcecode`, `checkchain`, `filename`, `ifexpire`, `expiretime`, `cookie`, `readerip`, `creatorId`, `starred`, `shared`, `deleted`) VALUES
	(1, '11592', '7dw7a1WJ27dhSH21Nss', 'js函數.txt', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(2, '19315', '7dw7a1WJ27dhSH21Nss', 'ChromeSetup.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(3, '14456', '7dw7a1WJ27dhSH21Nss', 'Chinese_Road_to_solo_driving_introduction_How_to_use_this_book.pdf', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(4, '13338', '7dw7a1WJ27dhSH21Nss', 'CreativeCloudSet-Up.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 1, 1, 0),
	(5, '11636', '7dw7a1WJ27dhSH21Nss', 'Balance confirmation.pdf', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(6, '12082', '7dw7a1WJ27dhSH21Nss', 'MEBUni_Mod12_StudentGuide_V1.0.pdf', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 1),
	(7, '13396', '7dw7a1WJ27dhSH21Nss', 'jdk-8u121-windows-x64.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 1, 0),
	(8, '19381', '7dw7a1WJ27dhSH21Nss', 'iTunes6464Setup.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(9, '17489', '7dw7a1WJ27dhSH21Nss', 'Pre.zip', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(10, '15383', '7dw7a1WJ27dhSH21Nss', 'orderWayThailand.zip', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(11, '10108', '7dw7a1WJ27dhSH21Nss', 'python-3.6.1.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 1, 0),
	(12, '10979', '7dw7a1WJ27dhSH21Nss', 'Graph Writing - Evaine.docx', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(13, '13683', '7dw7a1WJ27dhSH21Nss', 'Offer Confirmation Letter.pdf', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 1, 0, 0),
	(14, '12978', '7dw7a1WJ27dhSH21Nss', 'BaiduYunGuanjia_5.4.4.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(15, '14300', '7dw7a1WJ27dhSH21Nss', 'Setup.x86.en-US_ProPlusRetail_XNCGJ-CWTCB-494FX-4X4BM-VQW92_TX_PR_.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(16, '14445', '7dw7a1WJ27dhSH21Nss', 'python-2.7.13.msi', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 1, 0, 0),
	(17, '19045', '7dw7a1WJ27dhSH21Nss', 'webwxgetmsgimg (1).jpg', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 1),
	(18, '13430', '7dw7a1WJ27dhSH21Nss', 'Hearthstone-Setup.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 1, 0, 0),
	(19, '11527', '7dw7a1WJ27dhSH21Nss', 'p2pover4.34.exe', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0),
	(20, '14805', '7dw7a1WJ27dhSH21Nss', 'Religious center.pptx', 0, '2017-03-26 23:43:21', NULL, NULL, 1, 0, 0, 0);
/*!40000 ALTER TABLE `source_link` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
