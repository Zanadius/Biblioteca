DROP TABLE TB_LIBROS;
DROP TABLE TB_BIBLIOTECA;
DROP TABLE TB_DIRECCION;

CREATE TABLE TB_DIRECCION (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo_direccion ENUM('calle', 'plaza', 'traves a', 'avenida', 'carretera', 'otro') NOT NULL COMMENT "Calle, plaza, traves a, avenida, carretera, otro.",
	direccion VARCHAR(40),
    ciudad VARCHAR(20),
    provincia VARCHAR(20),
    cod_postal NUMERIC(5)
);

CREATE TABLE TB_BIBLIOTECA (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    fk_direccion INT NOT NULL,
    FOREIGN KEY (fk_direccion) REFERENCES TB_DIRECCION (id)
);


CREATE TABLE TB_LIBROS (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(30) NOT NULL,
    autor VARCHAR(20),
    ISBN VARCHAR(20),
    fk_biblioteca INT NOT NULL,
    CONSTRAINT FK_LIBRO_BIBLIOTECA_ID FOREIGN KEY (fk_biblioteca) REFERENCES TB_BIBLIOTECA (id)
);


INSERT INTO TB_DIRECCION (tipo_direccion, direccion, ciudad, provincia, cod_postal)
VALUES ("calle","Nombre Calle 1", "Ciudad1","Provincia1",28000);
INSERT INTO TB_DIRECCION (tipo_direccion, direccion, ciudad, provincia, cod_postal)
VALUES ("calle","Nombre Calle 2", "Ciudad2","Provincia2",37000);
INSERT INTO TB_DIRECCION (tipo_direccion, direccion, ciudad, provincia, cod_postal)
VALUES ("calle","Nombre Calle 3", "Ciudad3","Provincia3",27030);

SELECT * FROM TB_DIRECCION;

INSERT INTO TB_BIBLIOTECA (nombre,fk_direccion)
VALUES ("Biblioteca 1", 1);
INSERT INTO TB_BIBLIOTECA (nombre,fk_direccion)
VALUES ("Biblioteca 2", 2);
INSERT INTO TB_BIBLIOTECA (nombre,fk_direccion)
VALUES ("Biblioteca 3", 3);

SELECT * FROM TB_BIBLIOTECA;

INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo1","Autor1",1234567890,2);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo2","Autor2",0987654321,2);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo3","Autor3",0977644321,2);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo4","Autor4",1277634021,1);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo5","Autor5",4297604322,1);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo6","Autor6",7291305847,1);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo7","Autor7",8231408940,3);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo8","Autor8",7190307862,3);
INSERT INTO TB_LIBROS (titulo,autor,ISBN,fk_biblioteca) VALUES ("Titulo9","Autor9",3240305747,3);


ALTER TABLE TB_LIBROS ADD precio INT NOT NULL DEFAULT 1 AFTER fk_biblioteca;


UPDATE TB_LIBROS SET PRECIO=11 WHERE ID IN (1,3,7);
UPDATE TB_LIBROS SET PRECIO=13 WHERE ID IN (2,4);
UPDATE TB_LIBROS SET PRECIO=15 WHERE ID IN (8,9);
UPDATE TB_LIBROS SET PRECIO=14 WHERE ID IN (5,6);