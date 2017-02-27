-- phpMyAdmin SQL Dump
-- version phpStudy 2014
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 20, 2017 at 06:09 AM
-- Server version: 5.5.36-log
-- PHP Version: 5.5.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_rsyncWeb`
--

-- --------------------------------------------------------

--
-- Table structure for table `rsync_configure`
--

CREATE TABLE IF NOT EXISTS `rsync_configure` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `config_userid` int(11) NOT NULL COMMENT '配置参数对应用户id',
  `config_ip` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ip地址',
  `config_port` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '端口号',
  `config_catalog` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目录地址',
  `ftp_username` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ftp帐号',
  `ftp_password` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ftp密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=29 ;

--
-- Dumping data for table `rsync_configure`
--

INSERT INTO `rsync_configure` (`id`, `config_userid`, `config_ip`, `config_port`, `config_catalog`, `ftp_username`, `ftp_password`) VALUES
(22, 56, '119.29.188.78', '22', '/bin/bash', 'uftp', 'huangzhilong...0'),
(23, 57, '', '', '', '', ''),
(24, 58, 'qq', 'q', '', '', ''),
(25, 59, '', '', '', '', ''),
(26, 60, '', '', '', '', ''),
(27, 61, '', '', '', '', ''),
(28, 62, '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `rsync_user`
--

CREATE TABLE IF NOT EXISTS `rsync_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `rsync_username` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `rsync_password` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=63 ;

--
-- Dumping data for table `rsync_user`
--

INSERT INTO `rsync_user` (`id`, `rsync_username`, `rsync_password`) VALUES
(56, 'zhilonng', '123456'),
(57, 'xcvsadfv', 'xcbfb'),
(58, 'zxcwac', 'zxcsac'),
(59, 'zxéè¯¯é¢çè´¢å¯', 'casc'),
(60, 'asdasd', 'xzcasc'),
(61, 'xzzcsac', 'zxc'),
(62, 'zxc', 'acs');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
