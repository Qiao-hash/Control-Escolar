CREATE DATABASE IF NOT EXISTS control_escolar;
USE control_escolar;

CREATE TABLE Alumnos (
    carnet VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(20),
    apellidos VARCHAR(50),
    fecha_nacimiento DATE,
    direccion VARCHAR (100)
);

CREATE TABLE Materias (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(20),
    descripcion TEXT,
    fecha_creacion DATE
);

CREATE TABLE Estudiante_Materias (
    carnet_alumno VARCHAR(20),
    codigo_materia VARCHAR(20),
    PRIMARY KEY (carnet_alumno, codigo_materia),
    FOREIGN KEY (carnet_alumno) REFERENCES Alumnos(carnet),
    FOREIGN KEY (codigo_materia) REFERENCES Materias(codigo)
);

INSERT INTO Alumnos VALUES 
('A001', 'Juan', 'Perez', '2000-01-15', 'Calle 123'),
('A002', 'Maria', 'Gomez', '1999-05-22', 'Avenida 456');

INSERT INTO Materias VALUES
('M001', 'Matematicas', 'Algebra basica', CURDATE()),
('M002', 'Historia', 'Historia universal', CURDATE());