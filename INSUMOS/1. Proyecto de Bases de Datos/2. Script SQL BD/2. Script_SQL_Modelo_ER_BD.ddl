-- Generado por Oracle SQL Developer Data Modeler 21.2.0.183.1957
--   en:        2022-09-01 06:14:39 COT
--   sitio:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE clientes (
    cedula    CHAR(10 CHAR) NOT NULL,
    nombres   VARCHAR2(50 CHAR),
    apellidos VARCHAR2(50 CHAR),
    telefono  CHAR(10 CHAR)
);

ALTER TABLE clientes ADD CONSTRAINT clientes_pk PRIMARY KEY ( cedula );

CREATE TABLE detalle_facturas (
    codigo_factura    CHAR(15 CHAR) NOT NULL,
    codigo_producto   VARCHAR2(10 CHAR) NOT NULL,
    cantidad_prod     INTEGER,
    precio_total_prod NUMBER(10, 2)
);

ALTER TABLE detalle_facturas ADD CONSTRAINT detalle_facturas_pk PRIMARY KEY ( codigo_factura,
                                                                              codigo_producto );

CREATE TABLE facturas (
    codigo          CHAR(15 CHAR) NOT NULL,
    ruc             CHAR(13),
    direccion       VARCHAR2(100 CHAR),
    clientes_cedula CHAR(10 CHAR) NOT NULL,
    sub_total       NUMBER(10, 2),
    valor_iva       NUMBER(10, 2),
    valor_total     NUMBER(10, 2)
);

ALTER TABLE facturas ADD CONSTRAINT facturas_pk PRIMARY KEY ( codigo );

CREATE TABLE stock_productos (
    codigo          VARCHAR2(10 CHAR) NOT NULL,
    descripcion     CLOB,
    precio_unitario NUMBER(10, 2)
);

ALTER TABLE stock_productos ADD CONSTRAINT stock_productos_pk PRIMARY KEY ( codigo );

ALTER TABLE detalle_facturas
    ADD CONSTRAINT detalle_facturas_fk FOREIGN KEY ( codigo_factura )
        REFERENCES facturas ( codigo )
            ON DELETE CASCADE;

ALTER TABLE detalle_facturas
    ADD CONSTRAINT detalle_facturas_productos_fk FOREIGN KEY ( codigo_producto )
        REFERENCES stock_productos ( codigo )
            ON DELETE CASCADE;

ALTER TABLE facturas
    ADD CONSTRAINT facturas_clientes_fk FOREIGN KEY ( clientes_cedula )
        REFERENCES clientes ( cedula )
            ON DELETE CASCADE;



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                              7
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
