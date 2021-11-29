create table financial_app.endereco_correntista(
    id int(11) not null auto_increment,
    cep varchar(10) not null,
    logradouro varchar(500) not null
    numero varchar(50) not null,
    estado varchar(50) not null,
    complemento varchar(150) not null,
    bairro varchar(100) not null,
    municipio varchar(100) not null,
    id_correntista int(11) not null,
    index(cep varchar(10)),
    primary key (id),
    foreign key (id_correntista) references financial_app.correntista(id)
);
