create table financial_app.endereco_correntista(
    id int(11) not null auto_increment,
    cep varchar(10) not null,
    logradouro longtext not null,
    numero varchar(50) not null,
    estado varchar(50) not null,
    complemento longtext null,
    bairro longtext not null,
    municipio longtext not null,
    primary key (id)
);
