create table financial_app.correntista(
    id int(11) not null auto_increment,
    nome_completo longtext not null,
    numero_documento varchar(50) not null,
    tipo_documento varchar(50) not null,
    renda_mensal decimal not null,
    email longtext not null,
    celular varchar(50) not null,
    id_endereco int(11) not null,
    id_conta_corrente int(11) not null,
    primary key (id),
    foreign key (id_endereco) references financial_app.endereco_correntista(id),
    foreign key (id_conta_corrente) references financial_app.conta_corrente(id)
);
