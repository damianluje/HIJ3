/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     18/02/2016 17:33:25                          */
/*==============================================================*/


drop table if exists PEEMP_EMPLEADO;

drop table if exists ROL;

drop table if exists SEDFA_DETALLE_FACTURA;

drop table if exists SEFAC_FACTURA;

drop table if exists SEPAC_PACIENTE;

drop table if exists SEPRO_PROVEEDOR;

drop table if exists SETSV_TIPO_SERVICIO;

drop table if exists XEOPC_OPCIONES;

drop table if exists XEOPP_OPCIONES_PERFIL;

drop table if exists XEPER_PERFIL;

drop table if exists XESIS_SISTEMA;

drop table if exists XEUSU_USUARIO;

drop table if exists XEVEN_VENTANA;

/*==============================================================*/
/* Table: PEEMP_EMPLEADO                                        */
/*==============================================================*/
create table PEEMP_EMPLEADO
(
   PEEMP_CODIGO         int not null auto_increment,
   PEEMP_NOMBRES        varchar(100),
   PEEMP_APELLIDOS      varchar(100) not null,
   PEEMP_CEDULA         varchar(10),
   PEEMP_PASAPORTE      varchar(10),
   primary key (PEEMP_CODIGO)
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL
(
   XEPER_CODIGO         int not null,
   XEOPC_CODIGO         int not null,
   ROL_INSERT           bool,
   ROL_UPDATE           bool,
   ROL_DELETE           bool,
   ROL_SELECT           bool,
   primary key (XEPER_CODIGO, XEOPC_CODIGO)
);

/*==============================================================*/
/* Table: SEDFA_DETALLE_FACTURA                                 */
/*==============================================================*/
create table SEDFA_DETALLE_FACTURA
(
   SEDFA_CODIGO         int not null auto_increment,
   SETSV_CODIGO         int comment 'Código del tipo de servicio',
   SEFAC_CODIGO         int,
   SEDFA_CANTIDAD       int,
   primary key (SEDFA_CODIGO)
);

/*==============================================================*/
/* Table: SEFAC_FACTURA                                         */
/*==============================================================*/
create table SEFAC_FACTURA
(
   SEFAC_CODIGO         int not null auto_increment,
   SEPAC_CODIGO         int comment 'Código del paciente',
   PEEMP_CODIGO         int,
   SEFAC_FECHA          date,
   SEFAC_DIRECCION      varchar(100),
   SEFAC_SUBTOTAL       real,
   SEFAC_TOTAL          real,
   SEFAC_IVA            real,
   SEFAC_FORMA_PAGO     varchar(100),
   primary key (SEFAC_CODIGO)
);

/*==============================================================*/
/* Table: SEPAC_PACIENTE                                        */
/*==============================================================*/
create table SEPAC_PACIENTE
(
   SEPAC_CODIGO         int not null auto_increment comment 'Código del paciente',
   SEPAC_NOMBRE         varchar(100) not null comment 'Nombre del paciente',
   SEPAC_AREA_GEOGRAFICA varchar(100) comment 'Dirección del paciente',
   SEPAC_SEGURO         int not null comment 'Valor que indica si el paciente posee cubierta de Medicare',
   primary key (SEPAC_CODIGO)
);

alter table SEPAC_PACIENTE comment 'Los pacientes son quienes reciben los servicios médicos. Se ';

/*==============================================================*/
/* Table: SEPRO_PROVEEDOR                                       */
/*==============================================================*/
create table SEPRO_PROVEEDOR
(
   SEPRO_CODIGO         int not null auto_increment comment 'Código del proveedor',
   SEPRO_NOMBRE         varchar(100) not null comment 'Nombre del proveedor',
   SEPRO_PORCENTAJE_DEDUCIBLE float comment 'Porcentaje de deducible que debe cancelar el paciente si tiene cubierta Medicare',
   SEPRO_AREA_GEOGRAFICA varchar(100) comment 'Dirección del proveedor',
   primary key (SEPRO_CODIGO)
);

alter table SEPRO_PROVEEDOR comment 'El proveedor es quien ofrece los servicios médicos.
Op';

/*==============================================================*/
/* Table: SETSV_TIPO_SERVICIO                                   */
/*==============================================================*/
create table SETSV_TIPO_SERVICIO
(
   SETSV_CODIGO         int not null auto_increment comment 'Código del tipo de servicio',
   SEPRO_CODIGO         int not null comment 'Código del proveedor',
   SETSV_NOMBRE         varchar(100) not null comment 'Nombre o descripción del tipo de servicio',
   SETSV_COSTO          float not null comment 'Costo del tipo de servicio ofrecido',
   SETSV_DESCRIPCION    varchar(100),
   primary key (SETSV_CODIGO)
);

alter table SETSV_TIPO_SERVICIO comment 'Diferentes servicios ofrecidos a los pacientes con un costo ';

/*==============================================================*/
/* Table: XEOPC_OPCIONES                                        */
/*==============================================================*/
create table XEOPC_OPCIONES
(
   XEOPC_CODIGO         int not null auto_increment,
   XESIS_CODIGO         int not null,
   XEOPC_DESCRIPCION    varchar(100),
   primary key (XEOPC_CODIGO)
);

/*==============================================================*/
/* Table: XEOPP_OPCIONES_PERFIL                                 */
/*==============================================================*/
create table XEOPP_OPCIONES_PERFIL
(
   XEPER_CODIGO         int not null,
   XEOPC_CODIGO         int not null,
   XEOPP_FECHA_ASIGNACION date,
   XEOPP_ACTIVO         char(1),
   primary key (XEPER_CODIGO, XEOPC_CODIGO)
);

/*==============================================================*/
/* Table: XEPER_PERFIL                                          */
/*==============================================================*/
create table XEPER_PERFIL
(
   XEPER_CODIGO         int not null auto_increment,
   XEPER_DESCRIPCION    varchar(100),
   primary key (XEPER_CODIGO)
);

/*==============================================================*/
/* Table: XESIS_SISTEMA                                         */
/*==============================================================*/
create table XESIS_SISTEMA
(
   XESIS_CODIGO         int not null auto_increment,
   XESIS_DESCRIPCION    varchar(100),
   primary key (XESIS_CODIGO)
);

/*==============================================================*/
/* Table: XEUSU_USUARIO                                         */
/*==============================================================*/
create table XEUSU_USUARIO
(
   XEUSU_CODIGO         int not null auto_increment,
   XEUSU_ID             varchar(100) not null,
   XEPER_CODIGO         int,
   PEEMP_CODIGO         int not null,
   XEUSU_PASSWORD       varchar(100) not null,
   primary key (XEUSU_CODIGO)
);

/*==============================================================*/
/* Table: XEVEN_VENTANA                                         */
/*==============================================================*/
create table XEVEN_VENTANA
(
   XEVEN_CODIGO         int not null auto_increment,
   XEOPC_CODIGO         int,
   XEVEN_PAGINA         varchar(100) not null,
   primary key (XEVEN_CODIGO)
);

alter table ROL add constraint FK_RELATIONSHIP_12 foreign key (XEPER_CODIGO, XEOPC_CODIGO)
      references XEOPP_OPCIONES_PERFIL (XEPER_CODIGO, XEOPC_CODIGO) on delete restrict on update restrict;

alter table SEDFA_DETALLE_FACTURA add constraint FK_RELATIONSHIP_3 foreign key (SETSV_CODIGO)
      references SETSV_TIPO_SERVICIO (SETSV_CODIGO) on delete restrict on update restrict;

alter table SEDFA_DETALLE_FACTURA add constraint FK_RELATIONSHIP_4 foreign key (SEFAC_CODIGO)
      references SEFAC_FACTURA (SEFAC_CODIGO) on delete restrict on update restrict;

alter table SEFAC_FACTURA add constraint FK_RELATIONSHIP_1 foreign key (SEPAC_CODIGO)
      references SEPAC_PACIENTE (SEPAC_CODIGO) on delete restrict on update restrict;

alter table SEFAC_FACTURA add constraint FK_RELATIONSHIP_13 foreign key (PEEMP_CODIGO)
      references PEEMP_EMPLEADO (PEEMP_CODIGO) on delete restrict on update restrict;

alter table SETSV_TIPO_SERVICIO add constraint FK_RELATIONSHIP_2 foreign key (SEPRO_CODIGO)
      references SEPRO_PROVEEDOR (SEPRO_CODIGO) on delete restrict on update restrict;

alter table XEOPC_OPCIONES add constraint FK_RELATIONSHIP_10 foreign key (XESIS_CODIGO)
      references XESIS_SISTEMA (XESIS_CODIGO) on delete restrict on update restrict;

alter table XEOPP_OPCIONES_PERFIL add constraint FK_RELATIONSHIP_11 foreign key (XEOPC_CODIGO)
      references XEOPC_OPCIONES (XEOPC_CODIGO) on delete restrict on update restrict;

alter table XEOPP_OPCIONES_PERFIL add constraint FK_RELATIONSHIP_9 foreign key (XEPER_CODIGO)
      references XEPER_PERFIL (XEPER_CODIGO) on delete restrict on update restrict;

alter table XEUSU_USUARIO add constraint FK_RELATIONSHIP_14 foreign key (PEEMP_CODIGO)
      references PEEMP_EMPLEADO (PEEMP_CODIGO) on delete restrict on update restrict;

alter table XEUSU_USUARIO add constraint FK_RELATIONSHIP_6 foreign key (XEPER_CODIGO)
      references XEPER_PERFIL (XEPER_CODIGO) on delete restrict on update restrict;

alter table XEVEN_VENTANA add constraint FK_RELATIONSHIP_15 foreign key (XEOPC_CODIGO)
      references XEOPC_OPCIONES (XEOPC_CODIGO) on delete restrict on update restrict;

