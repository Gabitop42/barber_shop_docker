create database barber_shop_db;

Use barber_shop_db;

CREATE TABLE usuarios (
    id_usuario BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    email VARCHAR(255),
    contraseña VARCHAR(255),
    telefono VARCHAR(255),
    activo VARCHAR(255),
    rol ENUM('ADMIN','BARBERO'),
    PRIMARY KEY (id_usuario)
);

CREATE TABLE servicio_producto (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    tipo ENUM('PRODUCTO','SERVICIO') NOT NULL,
    precio_base DECIMAL(38,2) NOT NULL,
    porcentaje_comision DECIMAL(38,2) NOT NULL,
    activo BIT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ventas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    fecha DATETIME(6) NOT NULL,
    total DECIMAL(10,2),
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE detalle_venta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    servicio_producto_id BIGINT NOT NULL,
    venta_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE comisiones (
    id_comision BIGINT NOT NULL AUTO_INCREMENT,
    monto_comision DECIMAL(38,2),
    servicio_producto_id BIGINT,
    usuario_id BIGINT,
    venta_id BIGINT,
    PRIMARY KEY (id_comision)
);

CREATE TABLE registro_asistencia (
    id INT NOT NULL AUTO_INCREMENT,
    fecha DATE,
    hora_ingreso DATETIME(6),
    hora_salida DATETIME(6),
    usuario_id BIGINT,
    PRIMARY KEY (id)
);


ALTER TABLE detalle_venta
ADD CONSTRAINT uk_venta_servicio
UNIQUE (venta_id, servicio_producto_id);

ALTER TABLE ventas
ADD CONSTRAINT FK_venta_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id_usuario);

ALTER TABLE comisiones
ADD CONSTRAINT FK_comisiones_servicio_producto
FOREIGN KEY (servicio_producto_id)
REFERENCES servicio_producto(id);

ALTER TABLE comisiones
ADD CONSTRAINT FK_comisiones_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id_usuario);

ALTER TABLE comisiones
ADD CONSTRAINT FK_comisiones_venta
FOREIGN KEY (venta_id)
REFERENCES ventas(id);

ALTER TABLE detalle_venta
ADD CONSTRAINT FK_detalle_venta_servicio_producto
FOREIGN KEY (servicio_producto_id)
REFERENCES servicio_producto(id);

ALTER TABLE detalle_venta
ADD CONSTRAINT FK_detalle_venta_venta
FOREIGN KEY (venta_id)
REFERENCES ventas(id);

ALTER TABLE registro_asistencia
ADD CONSTRAINT FK_asistencia_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id_usuario);
