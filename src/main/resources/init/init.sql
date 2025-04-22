-- Base de datos: Sistema Control de Asistencia
-- create database asistencia;
use asistencia;
drop table Asistencia;
drop table Clase;
drop table Docente;
drop table Estudiante;
drop table Seccion;
drop table Grado;
drop table Nivel;
drop table Usuario;
drop table Rol;

CREATE TABLE Rol (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  rol_id INT,
  activo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (rol_id) REFERENCES Rol(id)
);
CREATE TABLE Nivel (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);
insert into nivel values (1,'Preescolar'),(2,'Primaria'),(3,'Secundaria'),(4,'Superior');
CREATE TABLE Grado (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  nivel_id INT,
  FOREIGN KEY (nivel_id) REFERENCES Nivel(id)
);

insert into Grado values (1,'Primero',1),(2,'Segundo',1),(3,'Tercero',1),(4,'Cuarto',1),(5,'Quinto',1),(6,'Sexto',1),(7,'Setimo',2),(8,'Octavo',2),(9,'Noveno',2),(10,'Decimo',2),(11,'Undecimo',2),(12,'Duodecimo',2);
CREATE TABLE Seccion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  grado_id INT,
  FOREIGN KEY (grado_id) REFERENCES Grado(id)
);
insert into Seccion values (1,'A',1),(2,'A',2),(3,'A',3),(4,'A',4),(5,'A',5),(6,'A',6),(7,'A',7),(8,'A',8),(9,'A',9),(10,'A',10),(11,'A',11),(12,'A',12);
CREATE TABLE Estudiante (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  fecha_nacimiento DATE,
  seccion_id INT,
  contacto_padre VARCHAR(100),
  FOREIGN KEY (seccion_id) REFERENCES Seccion(id)
);
insert into Estudiante values (1,'David Salas','1987-05-25',1,'81344567');
insert into Estudiante values (2,'WILLIAM ANDRES AGUILAR CALVO','1987-05-25',1,'81344567');
insert into Estudiante values (3,'ARMANDO JOSE AYALA CORDOBA','1987-05-25',1,'81344567');
insert into Estudiante values (4,'JAIRO DAVID BONILLA CALVO','1987-05-25',1,'81344567');
insert into Estudiante values (5,'JOAN ARMANDO CARBALLO BADILLA','1987-05-25',1,'81344567');
insert into Estudiante values (6,'RAQUEL VANESSA FERNANDEZ MENDEZ','1987-05-25',1,'81344567');
insert into Estudiante values (7,'ORLANDO ALEXANDER GATICA VALLE','1987-05-25',1,'81344567');
insert into Estudiante values (8,'CARLOS EDUARDO PERALTA COTO','1987-05-25',1,'81344567');
insert into Estudiante values (9,'KEVIN ANDREY RODRIGUEZ BARRANTES','1987-05-25',1,'81344567');
insert into Estudiante values (10,'ADOLFO JOSE FERNANDEZ CASTRO','1987-05-25',2,'81344567');
insert into Estudiante values (11,'YEHOSHUA MATAMOROS VALVERDE','1987-05-25',2,'81344567');
insert into Estudiante values (12,'RAQUEL VANESSA FERNANDEZ MENDEZ','1987-05-25',2,'81344567');
insert into Estudiante values (13,'ORLANDO ALEXANDER GATICA VALLE','1987-05-25',2,'81344567');
CREATE TABLE Docente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL
);
insert into Docente values (1,'Jirley Ramirez','jramirez@ucenfotec.ac.cr');
insert into Docente values (2,'Marco Alvarado','malvarado@ucenfotec.ac.cr');
insert into Docente values (3,'Federico Santos','fsantos@ucenfotec.ac.cr');

CREATE TABLE Clase (
  id INT AUTO_INCREMENT PRIMARY KEY,
  docente_id INT,
  seccion_id INT,
  fecha DATE NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fin TIME NOT NULL,
  FOREIGN KEY (docente_id) REFERENCES Docente(id),
  FOREIGN KEY (seccion_id) REFERENCES Seccion(id)
);
insert into Clase values(1,1,1,now(),'07:00:00', '08:20:00');
insert into Clase values(2,2,1,now(),'09:00:00', '10:20:00');

CREATE TABLE Asistencia (
  id SERIAL PRIMARY KEY,
  estudiante_id INT REFERENCES Estudiante(id),
  clase_id INT REFERENCES Clase(id),
  presente BOOLEAN DEFAULT TRUE,
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  observaciones VARCHAR(255)
);

-- Índices útiles
CREATE INDEX idx_asistencia_estudiante ON Asistencia(id);
CREATE INDEX idx_asistencia_clase ON Asistencia(id);
CREATE INDEX idx_estudiantes_seccion ON Estudiante(id);