create table tb_cliente ( 
		codCliente serial PRIMARY KEY, 
		nomeCompleto varchar (50) UNIQUE NOT NULL, 
		email varchar(50) UNIQUE NOT NULL, 
		telefone varchar(50) NOT NULL 
	);