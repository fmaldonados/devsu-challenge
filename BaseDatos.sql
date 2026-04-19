DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS persona;


CREATE TABLE persona (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20),
    edad INTEGER,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    cliente_id BIGINT PRIMARY KEY,
    contrasena VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT fk_cliente_persona FOREIGN KEY (cliente_id) REFERENCES persona(id) ON DELETE CASCADE
);


CREATE TABLE cuenta (
    id BIGSERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
    tipo VARCHAR(20) NOT NULL, 
    saldo_inicial DECIMAL(15, 2) NOT NULL,
    estado VARCHAR(20) NOT NULL, 
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

CREATE TABLE movimiento (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(20) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    saldo_resultante DECIMAL(15, 2) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);