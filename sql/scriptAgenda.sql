DROP DATABASE IF EXISTS Agenda;
CREATE DATABASE `agenda`;
USE agenda;
CREATE TABLE PAIS (
    idPais varchar(11) PRIMARY KEY,
    pais varchar(20) NOT NULL
);

CREATE TABLE PROVINCIA (
    idProvincia varchar(11) primary key,
    idPais varchar(11) NOT NULL,
    provincia varchar(20) NOT NULL,
    FOREIGN KEY (idPais) REFERENCES PAIS(idPais)
);

CREATE TABLE LOCALIDAD (
    idLocalidad varchar(11) primary key,
    idProvincia varchar(11) NOT NULL,
    localidad varchar(20) NOT NULL,
    FOREIGN KEY (idProvincia) REFERENCES PROVINCIA(idProvincia)
);

CREATE TABLE TIPO_CONTACTO
(
  idTipoContacto int(11) NOT NULL AUTO_INCREMENT,
  Tipo varchar(32) NOT NULL,
  PRIMARY KEY (`idTipoContacto`)
);

CREATE TABLE PERSONAS
(
  idPersona int(11) NOT NULL AUTO_INCREMENT,
  Nombre varchar(45) NOT NULL,
  Telefono varchar(20) NOT NULL,
  Calle varchar(20),
  altura varchar(20),
  piso varchar(20),
  dpto varchar(20),
  email varchar(20),
  cumpleaños date,
  idTipoContacto int(11),
  idLocalidad varchar(11),
  PRIMARY KEY (idPersona),
  FOREIGN KEY (IdTipoContacto) REFERENCES TIPO_CONTACTO(idTipoContacto),
  FOREIGN KEY (idLocalidad) REFERENCES LOCALIDAD(idLocalidad)
);

INSERT INTO PAIS (idPais, pais) VALUES ('ARG', 'Argentina');

INSERT INTO PROVINCIA (idPais, idProvincia, provincia) VALUES ('ARG', 'BA', 'Buenos Aires');
INSERT INTO PROVINCIA (idPais, idProvincia, provincia) VALUES ('ARG', 'COR', 'Cordoba');
INSERT INTO PROVINCIA (idPais, idProvincia, provincia) VALUES ('ARG', 'MEN', 'Mendoza');

INSERT INTO LOCALIDAD (idProvincia, idLocalidad, localidad) VALUES ('BA', 'CABA', 'CABA');
INSERT INTO LOCALIDAD (idProvincia, idLocalidad, localidad) VALUES ('BA', 'LP', 'Los Polvorines');
INSERT INTO LOCALIDAD (idProvincia, idLocalidad, localidad) VALUES ('MEN', 'SR', 'San Rafael');
INSERT INTO LOCALIDAD (idProvincia, idLocalidad, localidad) VALUES ('MEN', 'MEN', 'Mendoza');
INSERT INTO LOCALIDAD (idProvincia, idLocalidad, localidad) VALUES ('COR', 'MC', 'Mina Clavero');
INSERT INTO LOCALIDAD (idProvincia, idLocalidad, localidad) VALUES ('COR', 'CARL_PAZ', 'Villa Carlos Paz');

INSERT INTO LOCALIDAD (idProvincia, idLocalidad, localidad) VALUES ('COR', 'CARL_PAZ', 'Villa Carlos Paz');

INSERT INTO TIPO_CONTACTO (Tipo) VALUES ('Familia');
INSERT INTO TIPO_CONTACTO (Tipo) VALUES ('Trabajo');
INSERT INTO TIPO_CONTACTO (Tipo) VALUES ('Amigo');
INSERT INTO TIPO_CONTACTO (Tipo) VALUES ('Otro');