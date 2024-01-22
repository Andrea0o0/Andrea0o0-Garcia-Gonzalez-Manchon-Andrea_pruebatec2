-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 22, 2024 at 09:39 PM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shift_operator`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminstrator`
--

DROP TABLE IF EXISTS `adminstrator`;
CREATE TABLE IF NOT EXISTS `adminstrator` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `adminstrator`
--

INSERT INTO `adminstrator` (`ID`, `PASSWORD`, `USERNAME`) VALUES
(1, 'Aadmin1:', 'admin0o0');

-- --------------------------------------------------------

--
-- Table structure for table `citizen`
--

DROP TABLE IF EXISTS `citizen`;
CREATE TABLE IF NOT EXISTS `citizen` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `DATEOFBIRTH` date DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `citizen`
--

INSERT INTO `citizen` (`ID`, `DATEOFBIRTH`, `LASTNAME`, `NAME`, `PASSWORD`, `USERNAME`) VALUES
(1, '2000-08-25', 'GarcíaGonzález Manchón', 'Andrea', 'Aandrea1:', 'andrea0o0'),
(2, '2002-11-20', 'GarcíaGonzález Manchón', 'Tomás', 'Ttomi13:', 'tomi13__'),
(3, '1966-09-20', 'Manchón Castro', 'Montse', 'Mmontse15:', 'montse1515');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `procedure_entity`
--

DROP TABLE IF EXISTS `procedure_entity`;
CREATE TABLE IF NOT EXISTS `procedure_entity` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) DEFAULT NULL,
  `requirements` varchar(1000) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `admin` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_procedure_entity_admin` (`admin`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `procedure_entity`
--

INSERT INTO `procedure_entity` (`ID`, `description`, `requirements`, `TITLE`, `admin`) VALUES
(1, 'Description 1:\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut lacus venenatis, faucibus nisl in, pharetra odio. Aenean at nibh non dolor sagittis ullamcorper. Nunc quam ex, sodales sed lorem in, pharetra pellentesque felis. Integer eu fringilla velit. Vivamus iaculis tincidunt ex, in malesuada nunc. Aliquam erat volutpat. Vivamus nec enim laoreet, fringilla dolor ut, imperdiet tortor.\r\n\r\nNunc malesuada nisl ac libero eleifend tempor. Ut ut consectetur tortor, a ullamcorper tellus. Suspendisse quis fringilla turpis, at congue ligula. Duis id congue velit. Ut accumsan sapien diam, non pellentesque neque ornare vitae. Sed vitae lacus lobortis arcu porta finibus. Vivamus fermentum venenatis ipsum at rhoncus. Maecenas id erat nisl.\r\n            ', 'Requirements 1:\r\n1. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sollicitudin nibh sit amet commodo.\r\n2. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sollicitudin nibh sit amet commodo.\r\n3. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sollicitudin nibh sit amet commodo.\r\n            ', 'Procedure 1', 1),
(2, 'Description 2:\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut lacus venenatis, faucibus nisl in, pharetra odio. Aenean at nibh non dolor sagittis ullamcorper. Nunc quam ex, sodales sed lorem in, pharetra pellentesque felis. Integer eu fringilla velit. Vivamus iaculis tincidunt ex, in malesuada nunc. Aliquam erat volutpat. Vivamus nec enim laoreet, fringilla dolor ut, imperdiet tortor.\r\n\r\nNunc malesuada nisl ac libero eleifend tempor. Ut ut consectetur tortor, a ullamcorper tellus. Suspendisse quis fringilla turpis, at congue ligula. Duis id congue velit. Ut accumsan sapien diam, non pellentesque neque ornare vitae. Sed vitae lacus lobortis arcu porta finibus. Vivamus fermentum venenatis ipsum at rhoncus. Maecenas id erat nisl.', 'Requirements 2:\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut lacus venenatis, faucibus nisl in, pharetra odio. Aenean at nibh non dolor sagittis ullamcorper. Nunc quam ex, sodales sed lorem in, pharetra pellentesque felis. Integer eu fringilla velit. Vivamus iaculis tincidunt ex, in malesuada nunc. Aliquam erat volutpat. Vivamus nec enim laoreet, fringilla dolor ut, imperdiet tortor.\r\n\r\nNunc malesuada nisl ac libero eleifend tempor. Ut ut consectetur tortor, a ullamcorper tellus. Suspendisse quis fringilla turpis, at congue ligula. Duis id congue velit. Ut accumsan sapien diam, non pellentesque neque ornare vitae. Sed vitae lacus lobortis arcu porta finibus. Vivamus fermentum venenatis ipsum at rhoncus. Maecenas id erat nisl.', 'Procedure 2', 1),
(3, 'Description 3:\r\n1. eget mauris pharetra et ultrices neque ornare aenean euismod elementum nisi quis eleifend quam adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque viverra justo nec ultrices dui sapien eget mi proin sed libero enim sed faucibus turpis in eu mi bibendum neque egestas congue quisque egestas diam in arcu cursus euismod quis viverra nibh cras pulvinar mattis nunc sed blandit libero volutpat sed cras ornare arcu dui vivamus arcu felis bibendum ut tristique et egestas quis ipsum suspendisse ultrices gravida dictum fusce ut placerat orci nulla pellentesque dignissim enim sit amet venenatis urna cursus eget nunc scelerisque viverra mauris in aliquam sem fringilla ut morbi tincidunt augue.', 'Requirements 3: \r\n1. eget mauris pharetra et ultrices neque ornare aenean euismod elementum nisi quis eleifend quam adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque viverra.\r\n2. eget mauris pharetra et ultrices neque ornare aenean euismod elementum nisi quis eleifend quam adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus urna neque viverra.', 'Procedure 3', 1),
(4, 'Description 4:\r\nEtiam eu leo luctus risus placerat facilisis efficitur et lorem.\r\nPraesent rhoncus metus a velit gravida elementum.\r\nDuis volutpat erat sed leo auctor sollicitudin.\r\nUt malesuada nisi eu neque sodales, pretium aliquam nunc fringilla.\r\nDonec mollis velit venenatis tellus venenatis scelerisque.\r\nDuis sed dui sed purus sodales laoreet.\r\nNullam in lacus quis dolor blandit interdum non eu dui.\r\nInteger non mi id lectus ullamcorper accumsan.\r\nVivamus nec ligula pharetra, vestibulum massa eget, porttitor ligula.\r\nMorbi ac diam et libero laoreet placerat ut vel neque.\r\nMorbi sodales turpis sed quam luctus posuere.', 'Requirements 4:\r\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut lacus venenatis, faucibus nisl in, pharetra odio. Aenean at nibh non dolor sagittis ullamcorper. Nunc quam ex, sodales sed lorem in, pharetra pellentesque felis. Integer eu fringilla velit. Vivamus iaculis tincidunt ex, in malesuada nunc. Aliquam erat volutpat. Vivamus nec enim laoreet, fringilla dolor ut, imperdiet tortor.\r\n\r\nNunc malesuada nisl ac libero eleifend tempor. Ut ut consectetur tortor, a ullamcorper tellus. Suspendisse quis fringilla turpis, at congue ligula. Duis id congue velit. Ut accumsan sapien diam, non pellentesque neque ornare vitae. Sed vitae lacus lobortis arcu porta finibus. Vivamus fermentum venenatis ipsum at rhoncus. Maecenas id erat nisl.', 'Procedure 4', 1),
(5, 'Description 5:\r\nNunc malesuada nisl ac libero eleifend tempor. Ut ut consectetur tortor, a ullamcorper tellus. Suspendisse quis fringilla turpis, at congue ligula. Duis id congue velit. Ut accumsan sapien diam, non pellentesque neque ornare vitae. Sed vitae lacus lobortis arcu porta finibus. Vivamus fermentum venenatis ipsum at rhoncus. Maecenas id erat nisl.\r\n\r\n1. Etiam eu leo luctus risus placerat facilisis efficitur et lorem.\r\n2. Praesent rhoncus metus a velit gravida elementum.\r\n3. Duis volutpat erat sed leo auctor sollicitudin.\r\n4. Ut malesuada nisi eu neque sodales, pretium aliquam nunc fringilla.', 'Requirements 5:\r\nEtiam eu leo luctus risus placerat facilisis efficitur et lorem.\r\nPraesent rhoncus metus a velit gravida elementum.\r\nDuis volutpat erat sed leo auctor sollicitudin.', 'Procedure 5', 1);

-- --------------------------------------------------------

--
-- Table structure for table `shifts`
--

DROP TABLE IF EXISTS `shifts`;
CREATE TABLE IF NOT EXISTS `shifts` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ADDITIONALINFORMATION` varchar(255) DEFAULT NULL,
  `date_hour` datetime(3) DEFAULT NULL,
  `SHIFTSTATUS` tinyint(1) DEFAULT '0',
  `citizen_id` bigint DEFAULT NULL,
  `procedure_id` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_shifts_citizen_id` (`citizen_id`),
  KEY `FK_shifts_procedure_id` (`procedure_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `shifts`
--

INSERT INTO `shifts` (`ID`, `ADDITIONALINFORMATION`, `date_hour`, `SHIFTSTATUS`, `citizen_id`, `procedure_id`) VALUES
(1, 'More info :)', '2024-01-22 21:19:17.783', 1, 2, 2),
(2, 'Montse Dni more info', '2024-01-22 21:31:14.027', 1, 3, 4),
(3, 'Hi this is my second shift :)', '2024-01-22 21:32:35.039', 1, 3, 1),
(4, 'Hii I was waiting too much the last time', '2024-01-22 21:34:15.091', 1, 1, 3),
(5, 'Tetsing additional info', '2024-01-22 21:34:54.690', 1, 2, 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
