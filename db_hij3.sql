-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-02-2016 a las 16:16:26
-- Versión del servidor: 10.1.8-MariaDB
-- Versión de PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hij3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peemp_empleado`
--

CREATE TABLE `peemp_empleado` (
  `PEEMP_CODIGO` int(11) NOT NULL,
  `PEEMP_NOMBRES` varchar(100) DEFAULT NULL,
  `PEEMP_APELLIDOS` varchar(100) NOT NULL,
  `PEEMP_CEDULA` varchar(10) DEFAULT NULL,
  `PEEMP_PASAPORTE` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `peemp_empleado`
--

INSERT INTO `peemp_empleado` (`PEEMP_CODIGO`, `PEEMP_NOMBRES`, `PEEMP_APELLIDOS`, `PEEMP_CEDULA`, `PEEMP_PASAPORTE`) VALUES
(1, 'Jesus Damian', 'Luje Pozo', '1723526016', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `XEPER_CODIGO` int(11) NOT NULL,
  `XEOPC_CODIGO` int(11) NOT NULL,
  `ROL_INSERT` tinyint(1) DEFAULT NULL,
  `ROL_UPDATE` tinyint(1) DEFAULT NULL,
  `ROL_DELETE` tinyint(1) DEFAULT NULL,
  `ROL_SELECT` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`XEPER_CODIGO`, `XEOPC_CODIGO`, `ROL_INSERT`, `ROL_UPDATE`, `ROL_DELETE`, `ROL_SELECT`) VALUES
(1, 3, 0, 1, 0, 1),
(1, 4, 1, 1, 1, 1),
(1, 6, 1, 1, 0, 1),
(1, 7, 1, 1, 1, 1),
(1, 11, 0, 0, 0, 0),
(1, 12, 0, 0, 0, 0),
(1, 13, 0, 0, 0, 0),
(6, 3, 0, 0, 1, 0),
(6, 4, 0, 0, 0, 0),
(6, 6, 0, 0, 0, 0),
(6, 7, 0, 0, 0, 0),
(6, 11, 0, 0, 0, 0),
(6, 12, 0, 0, 0, 0),
(6, 13, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sedfa_detalle_factura`
--

CREATE TABLE `sedfa_detalle_factura` (
  `SEDFA_CODIGO` int(11) NOT NULL,
  `SETSV_CODIGO` int(11) DEFAULT NULL COMMENT 'Código del tipo de servicio',
  `SEFAC_CODIGO` int(11) DEFAULT NULL,
  `SEDFA_CANTIDAD` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sefac_factura`
--

CREATE TABLE `sefac_factura` (
  `SEFAC_CODIGO` int(11) NOT NULL,
  `SEPAC_CODIGO` int(11) DEFAULT NULL COMMENT 'Código del paciente',
  `PEEMP_CODIGO` int(11) DEFAULT NULL,
  `SEFAC_FECHA` date DEFAULT NULL,
  `SEFAC_DIRECCION` varchar(100) DEFAULT NULL,
  `SEFAC_SUBTOTAL` double DEFAULT NULL,
  `SEFAC_TOTAL` double DEFAULT NULL,
  `SEFAC_IVA` double DEFAULT NULL,
  `SEFAC_FORMA_PAGO` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sepac_paciente`
--

CREATE TABLE `sepac_paciente` (
  `SEPAC_CODIGO` int(11) NOT NULL COMMENT 'Código del paciente',
  `SEPAC_NOMBRE` varchar(100) NOT NULL COMMENT 'Nombre del paciente',
  `SEPAC_AREA_GEOGRAFICA` varchar(100) DEFAULT NULL COMMENT 'Dirección del paciente',
  `SEPAC_SEGURO` int(11) NOT NULL COMMENT 'Valor que indica si el paciente posee cubierta de Medicare'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Los pacientes son quienes reciben los servicios médicos. Se ';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sepro_proveedor`
--

CREATE TABLE `sepro_proveedor` (
  `SEPRO_CODIGO` int(11) NOT NULL COMMENT 'Código del proveedor',
  `SEPRO_NOMBRE` varchar(100) NOT NULL COMMENT 'Nombre del proveedor',
  `SEPRO_PORCENTAJE_DEDUCIBLE` float DEFAULT NULL COMMENT 'Porcentaje de deducible que debe cancelar el paciente si tiene cubierta Medicare',
  `SEPRO_AREA_GEOGRAFICA` varchar(100) DEFAULT NULL COMMENT 'Dirección del proveedor'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='El proveedor es quien ofrece los servicios médicos.\r\nOp';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `setsv_tipo_servicio`
--

CREATE TABLE `setsv_tipo_servicio` (
  `SETSV_CODIGO` int(11) NOT NULL COMMENT 'Código del tipo de servicio',
  `SEPRO_CODIGO` int(11) NOT NULL COMMENT 'Código del proveedor',
  `SETSV_NOMBRE` varchar(100) NOT NULL COMMENT 'Nombre o descripción del tipo de servicio',
  `SETSV_COSTO` float NOT NULL COMMENT 'Costo del tipo de servicio ofrecido',
  `SETSV_DESCRIPCION` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Diferentes servicios ofrecidos a los pacientes con un costo ';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xeopc_opciones`
--

CREATE TABLE `xeopc_opciones` (
  `XEOPC_CODIGO` int(11) NOT NULL,
  `XESIS_CODIGO` int(11) NOT NULL,
  `XEOPC_DESCIPCION` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `xeopc_opciones`
--

INSERT INTO `xeopc_opciones` (`XEOPC_CODIGO`, `XESIS_CODIGO`, `XEOPC_DESCIPCION`) VALUES
(3, 2, 'Administrar Sistemas'),
(4, 3, 'Administrar Empleados'),
(6, 2, 'Administrar Usuarios'),
(7, 3, 'Administrar Personal'),
(11, 2, 'AdminSistem'),
(12, 2, 'AdminSis'),
(13, 2, 'AdminSisqwe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xeopp_opciones_perfil`
--

CREATE TABLE `xeopp_opciones_perfil` (
  `XEPER_CODIGO` int(11) NOT NULL,
  `XEOPC_CODIGO` int(11) NOT NULL,
  `XEOPP_FECHA_ASIGNACION` date DEFAULT NULL,
  `XEOPP_ACTIVO` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `xeopp_opciones_perfil`
--

INSERT INTO `xeopp_opciones_perfil` (`XEPER_CODIGO`, `XEOPC_CODIGO`, `XEOPP_FECHA_ASIGNACION`, `XEOPP_ACTIVO`) VALUES
(1, 3, NULL, NULL),
(1, 4, NULL, NULL),
(1, 6, NULL, NULL),
(1, 7, NULL, NULL),
(1, 11, NULL, NULL),
(1, 12, NULL, NULL),
(1, 13, NULL, NULL),
(6, 3, NULL, NULL),
(6, 4, NULL, NULL),
(6, 6, NULL, NULL),
(6, 7, NULL, NULL),
(6, 11, NULL, NULL),
(6, 12, NULL, NULL),
(6, 13, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xeper_perfil`
--

CREATE TABLE `xeper_perfil` (
  `XEPER_CODIGO` int(11) NOT NULL,
  `XEPER_DESCRIPCION` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `xeper_perfil`
--

INSERT INTO `xeper_perfil` (`XEPER_CODIGO`, `XEPER_DESCRIPCION`) VALUES
(1, 'Administrador'),
(6, 'Usuario1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xesis_sistema`
--

CREATE TABLE `xesis_sistema` (
  `XESIS_CODIGO` int(11) NOT NULL,
  `XESIS_DESCRIPCION` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `xesis_sistema`
--

INSERT INTO `xesis_sistema` (`XESIS_CODIGO`, `XESIS_DESCRIPCION`) VALUES
(2, 'Seguridad'),
(3, 'Personal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xeusu_usuario`
--

CREATE TABLE `xeusu_usuario` (
  `XEUSU_CODIGO` int(11) NOT NULL,
  `XEUSU_ID` varchar(100) NOT NULL,
  `XEPER_CODIGO` int(11) DEFAULT NULL,
  `PEEMP_CODIGO` int(11) NOT NULL,
  `XEUSU_PASSWORD` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `xeusu_usuario`
--

INSERT INTO `xeusu_usuario` (`XEUSU_CODIGO`, `XEUSU_ID`, `XEPER_CODIGO`, `PEEMP_CODIGO`, `XEUSU_PASSWORD`) VALUES
(1, 'jdluje', 1, 1, '9059e2dc0e5d421dcb5e10b8ad86b000');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `peemp_empleado`
--
ALTER TABLE `peemp_empleado`
  ADD PRIMARY KEY (`PEEMP_CODIGO`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`XEPER_CODIGO`,`XEOPC_CODIGO`);

--
-- Indices de la tabla `sedfa_detalle_factura`
--
ALTER TABLE `sedfa_detalle_factura`
  ADD PRIMARY KEY (`SEDFA_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_3` (`SETSV_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_4` (`SEFAC_CODIGO`);

--
-- Indices de la tabla `sefac_factura`
--
ALTER TABLE `sefac_factura`
  ADD PRIMARY KEY (`SEFAC_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_1` (`SEPAC_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_13` (`PEEMP_CODIGO`);

--
-- Indices de la tabla `sepac_paciente`
--
ALTER TABLE `sepac_paciente`
  ADD PRIMARY KEY (`SEPAC_CODIGO`);

--
-- Indices de la tabla `sepro_proveedor`
--
ALTER TABLE `sepro_proveedor`
  ADD PRIMARY KEY (`SEPRO_CODIGO`);

--
-- Indices de la tabla `setsv_tipo_servicio`
--
ALTER TABLE `setsv_tipo_servicio`
  ADD PRIMARY KEY (`SETSV_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_2` (`SEPRO_CODIGO`);

--
-- Indices de la tabla `xeopc_opciones`
--
ALTER TABLE `xeopc_opciones`
  ADD PRIMARY KEY (`XEOPC_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_10` (`XESIS_CODIGO`);

--
-- Indices de la tabla `xeopp_opciones_perfil`
--
ALTER TABLE `xeopp_opciones_perfil`
  ADD PRIMARY KEY (`XEPER_CODIGO`,`XEOPC_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_11` (`XEOPC_CODIGO`);

--
-- Indices de la tabla `xeper_perfil`
--
ALTER TABLE `xeper_perfil`
  ADD PRIMARY KEY (`XEPER_CODIGO`);

--
-- Indices de la tabla `xesis_sistema`
--
ALTER TABLE `xesis_sistema`
  ADD PRIMARY KEY (`XESIS_CODIGO`);

--
-- Indices de la tabla `xeusu_usuario`
--
ALTER TABLE `xeusu_usuario`
  ADD PRIMARY KEY (`XEUSU_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_14` (`PEEMP_CODIGO`),
  ADD KEY `FK_RELATIONSHIP_6` (`XEPER_CODIGO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `peemp_empleado`
--
ALTER TABLE `peemp_empleado`
  MODIFY `PEEMP_CODIGO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `sedfa_detalle_factura`
--
ALTER TABLE `sedfa_detalle_factura`
  MODIFY `SEDFA_CODIGO` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sefac_factura`
--
ALTER TABLE `sefac_factura`
  MODIFY `SEFAC_CODIGO` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sepac_paciente`
--
ALTER TABLE `sepac_paciente`
  MODIFY `SEPAC_CODIGO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Código del paciente';
--
-- AUTO_INCREMENT de la tabla `sepro_proveedor`
--
ALTER TABLE `sepro_proveedor`
  MODIFY `SEPRO_CODIGO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Código del proveedor';
--
-- AUTO_INCREMENT de la tabla `setsv_tipo_servicio`
--
ALTER TABLE `setsv_tipo_servicio`
  MODIFY `SETSV_CODIGO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Código del tipo de servicio';
--
-- AUTO_INCREMENT de la tabla `xeopc_opciones`
--
ALTER TABLE `xeopc_opciones`
  MODIFY `XEOPC_CODIGO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `xeper_perfil`
--
ALTER TABLE `xeper_perfil`
  MODIFY `XEPER_CODIGO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `xesis_sistema`
--
ALTER TABLE `xesis_sistema`
  MODIFY `XESIS_CODIGO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `xeusu_usuario`
--
ALTER TABLE `xeusu_usuario`
  MODIFY `XEUSU_CODIGO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `rol`
--
ALTER TABLE `rol`
  ADD CONSTRAINT `FK_RELATIONSHIP_12` FOREIGN KEY (`XEPER_CODIGO`,`XEOPC_CODIGO`) REFERENCES `xeopp_opciones_perfil` (`XEPER_CODIGO`, `XEOPC_CODIGO`);

--
-- Filtros para la tabla `sedfa_detalle_factura`
--
ALTER TABLE `sedfa_detalle_factura`
  ADD CONSTRAINT `FK_RELATIONSHIP_3` FOREIGN KEY (`SETSV_CODIGO`) REFERENCES `setsv_tipo_servicio` (`SETSV_CODIGO`),
  ADD CONSTRAINT `FK_RELATIONSHIP_4` FOREIGN KEY (`SEFAC_CODIGO`) REFERENCES `sefac_factura` (`SEFAC_CODIGO`);

--
-- Filtros para la tabla `sefac_factura`
--
ALTER TABLE `sefac_factura`
  ADD CONSTRAINT `FK_RELATIONSHIP_1` FOREIGN KEY (`SEPAC_CODIGO`) REFERENCES `sepac_paciente` (`SEPAC_CODIGO`),
  ADD CONSTRAINT `FK_RELATIONSHIP_13` FOREIGN KEY (`PEEMP_CODIGO`) REFERENCES `peemp_empleado` (`PEEMP_CODIGO`);

--
-- Filtros para la tabla `setsv_tipo_servicio`
--
ALTER TABLE `setsv_tipo_servicio`
  ADD CONSTRAINT `FK_RELATIONSHIP_2` FOREIGN KEY (`SEPRO_CODIGO`) REFERENCES `sepro_proveedor` (`SEPRO_CODIGO`);

--
-- Filtros para la tabla `xeopc_opciones`
--
ALTER TABLE `xeopc_opciones`
  ADD CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`XESIS_CODIGO`) REFERENCES `xesis_sistema` (`XESIS_CODIGO`);

--
-- Filtros para la tabla `xeopp_opciones_perfil`
--
ALTER TABLE `xeopp_opciones_perfil`
  ADD CONSTRAINT `FK_RELATIONSHIP_11` FOREIGN KEY (`XEOPC_CODIGO`) REFERENCES `xeopc_opciones` (`XEOPC_CODIGO`),
  ADD CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`XEPER_CODIGO`) REFERENCES `xeper_perfil` (`XEPER_CODIGO`);

--
-- Filtros para la tabla `xeusu_usuario`
--
ALTER TABLE `xeusu_usuario`
  ADD CONSTRAINT `FK_RELATIONSHIP_14` FOREIGN KEY (`PEEMP_CODIGO`) REFERENCES `peemp_empleado` (`PEEMP_CODIGO`),
  ADD CONSTRAINT `FK_RELATIONSHIP_6` FOREIGN KEY (`XEPER_CODIGO`) REFERENCES `xeper_perfil` (`XEPER_CODIGO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
