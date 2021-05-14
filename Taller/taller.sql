-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 14-05-2021 a las 19:09:27
-- Versión del servidor: 10.5.6-MariaDB
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `taller`
--
CREATE DATABASE IF NOT EXISTS `taller` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `taller`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `dni` varchar(9) COLLATE utf8_bin NOT NULL,
  `nombre` varchar(50) COLLATE utf8_bin NOT NULL,
  `direccion` varchar(150) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`dni`, `nombre`, `direccion`) VALUES
('31009229P', 'Pedro', 'C/Gil'),
('31009229p', 'Juan Fco', 'C/Bonifacio'),
('31319229p', 'Javi', 'C/Golf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparacion`
--

DROP TABLE IF EXISTS `reparacion`;
CREATE TABLE IF NOT EXISTS `reparacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `precio` double NOT NULL,
  `matricula` varchar(10) COLLATE utf8_bin NOT NULL,
  `descripcion` varchar(250) COLLATE utf8_bin NOT NULL,
  `fecha` varchar(10) COLLATE utf8_bin NOT NULL,
  `dni_client` varchar(9) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dni_client` (`dni_client`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `reparacion`
--

INSERT INTO `reparacion` (`id`, `precio`, `matricula`, `descripcion`, `fecha`, `dni_client`) VALUES
(20, 50, '1111', 'maletero', '2021-5-7', '31319229p'),
(25, 200.3, '3949 bby', 'Maletero visagra suelo', '2021-5-6', '31319229p'),
(27, 5000, '4646 AWS', 'Reparacion de todo el lateral derecho, incluyendo la reparacion de los parachoques, delantero y trasero.', '2021-5-11', '31319229p'),
(28, 1000, '9999 yyy', 'la compra de un coche nuevo', '2021-4-7', '31009229p'),
(29, 1000, '3949 BBY', 'Puerta derecha', '2021-6-16', '31009229p');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reparacion`
--
ALTER TABLE `reparacion`
  ADD CONSTRAINT `reparacion_ibfk_1` FOREIGN KEY (`dni_client`) REFERENCES `client` (`dni`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
