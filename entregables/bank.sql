-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-12-2022 a las 18:13:23
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bank`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cliente_id` bigint(20) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `clave` varchar(255) NOT NULL,
  `estado` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cliente_id`, `direccion`, `edad`, `genero`, `identificacion`, `nombre`, `telefono`, `clave`, `estado`) VALUES
(1, 'string', 0, 'string', 'string', 'string', 'string', 'string', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `cuenta_id` bigint(20) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `numero_cuenta` bigint(20) DEFAULT NULL,
  `saldo_actual` bigint(20) DEFAULT NULL,
  `saldo_inicial` bigint(20) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`cuenta_id`, `estado`, `numero_cuenta`, `saldo_actual`, `saldo_inicial`, `tipo_cuenta`, `cliente_id`) VALUES
(1, b'1', 0, 1111111090, 1111111110, 'string', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE `movimientos` (
  `movimiento_id` bigint(20) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `saldo` bigint(20) DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  `valor` bigint(20) DEFAULT NULL,
  `cuenta_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`movimiento_id`, `fecha`, `saldo`, `tipo_movimiento`, `valor`, `cuenta_id`) VALUES
(1, '2022-12-25 19:00:00', 1111111100, 'Debito', -10, 1),
(2, '2022-12-25 19:00:00', 1111111090, 'Debito', -10, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cliente_id`),
  ADD UNIQUE KEY `UK_147rikqkb68rxqijmxxgpjwot` (`identificacion`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`cuenta_id`),
  ADD UNIQUE KEY `UK_pj7ncg765kt4klndu25bwbwe4` (`numero_cuenta`),
  ADD KEY `FK4p224uogyy5hmxvn8fwa2jlug` (`cliente_id`);

--
-- Indices de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`movimiento_id`),
  ADD KEY `FKof0364849tnpw8nw69v8m1vq4` (`cuenta_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cliente_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `cuenta_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `movimiento_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `FK4p224uogyy5hmxvn8fwa2jlug` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`);

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `FKof0364849tnpw8nw69v8m1vq4` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`cuenta_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
