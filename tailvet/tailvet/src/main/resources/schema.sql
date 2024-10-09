CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(50),
    cedula VARCHAR(50)
);

CREATE TABLE veterinario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    especialidad VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    telefono VARCHAR(255)
);




CREATE TABLE mascota (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    raza VARCHAR(255),
    edad INT,
    peso DOUBLE,
    imagen VARCHAR(1024),
    enfermedad VARCHAR(255),
    usuario_id BIGINT,
    veterinario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(id)
);
CREATE TABLE Droga (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio_compra DECIMAL(10, 2) NOT NULL,
    precio_venta DECIMAL(10, 2) NOT NULL,
    unidades_disponibles INT NOT NULL,
    unidades_vendidas INT NOT NULL
);
CREATE TABLE Tratamiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    cantidad_usada INT NOT NULL,
    observaciones TEXT,
    mascota_id BIGINT,
    veterinario_id BIGINT,
    droga_id BIGINT,
    FOREIGN KEY (mascota_id) REFERENCES mascota(id),
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(id),
    FOREIGN KEY (droga_id) REFERENCES Droga(id)
);

