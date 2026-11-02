CREATE DATABASE IF NOT EXISTS bd_paciente;
CREATE DATABASE IF NOT EXISTS bd_medico;
CREATE DATABASE IF NOT EXISTS bd_cita;

USE bd_paciente;
CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(8) UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    sexo CHAR(1),
    telefono VARCHAR(15),
    direccion VARCHAR(200),
    correo VARCHAR(100),
    estado CHAR(1) DEFAULT 'A'
);

USE bd_medico;
CREATE TABLE especialidad (
    id_especialidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    estado CHAR(1) DEFAULT 'A'
);
CREATE TABLE medico (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    cmp VARCHAR(20) UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(100),
    id_especialidad INT NOT NULL,
    estado CHAR(1) DEFAULT 'A',
    FOREIGN KEY (id_especialidad)
        REFERENCES especialidad(id_especialidad)
);
CREATE TABLE horario_medico (
    id_horario INT AUTO_INCREMENT PRIMARY KEY,
    id_medico INT NOT NULL,
    dia_semana VARCHAR(15),
    hora_inicio TIME,
    hora_fin TIME,
    FOREIGN KEY (id_medico)
        REFERENCES medico(id_medico)
);

USE bd_cita;
-- id_paciente e id_medico viven en otras bases: se validan llamando a sus servicios
CREATE TABLE cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    motivo VARCHAR(300),
    estado VARCHAR(20) DEFAULT 'PROGRAMADA'
);
CREATE TABLE atencion (
    id_atencion INT AUTO_INCREMENT PRIMARY KEY,
    id_cita INT UNIQUE,
    diagnostico TEXT,
    tratamiento TEXT,
    observaciones TEXT,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cita)
        REFERENCES cita(id_cita)
);
