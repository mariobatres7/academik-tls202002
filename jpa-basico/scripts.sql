create table producto
(
	producto_id integer not null primary key auto_increment,
	codigo varchar(200) not null,
	codigo_barras varchar(200) null,
	nombre varchar(1000),
	precio decimal(10, 2)
);


create table cliente(
	cliente_id integer not null primary key auto_increment,
	nombre varchar(300),
	nit varchar(50),
	direccion varchar(2000)
);

create table  cliente_producto(
	cliente_id integer not null,
	producto_id integer not null
);


alter table cliente_producto add constraint
cliente_producto_pk
primary key (cliente_id, producto_id);


alter table cliente_producto add constraint 
cliente_producto_fk1 foreign key (cliente_id) references cliente(cliente_id);

alter table cliente_producto add constraint 
cliente_producto_fk2 foreign key (producto_id) references producto(producto_id);

create table factura 
(
	factura_id integer not null primary key auto_increment,
	numero varchar(100) not null,
	fecha timestamp not null
);

alter table factura add cliente_id integer not null;

alter table factura add constraint 
factura_fk1 foreign key (cliente_id) 
references cliente(cliente_id);


create table factura_detalle (
	factura_detalle_id integer not null primary key auto_increment,
	factura_id integer not null,
	producto_id integer not null,
	cantidad integer not null ,factura
	subtotal decimal(10, 2) not null ,
	total decimal(10, 2) not null 
);


alter table factura_detalle add constraint 
factura_detalle_fk1 foreign key (factura_id) 
references factura(factura_id);


alter table factura_detalle add constraint 
factura_detalle_fk2 foreign key (producto_id) 
references producto(producto_id);

create table membresia(
	membresia_id integer not null primary key auto_increment,
	cliente_id integer not null,
	codigo varchar(200)
);


alter table membresia add constraint 
mebresia_fk1 foreign key (cliente_id) 
references cliente(cliente_id);

create unique index membresia_uk on membresia(cliente_id);




create table medida (
	medida_id integer not null primary key auto_increment,
	descripcion varchar(100) not null
);


create table producto_medida(
	producto_id integer not null,
	medida_id integer not null,
	existencia decimal 	
);


alter table producto_medida add constraint producto_medida_pk primary key (producto_id, medida_id);


alter table producto_medida add constraint producto_medida_fk1 foreign key (producto_id) references producto (producto_id);

alter table producto_medida add constraint producto_medida_fk2 foreign key (medida_id) references medida (medida_id);





