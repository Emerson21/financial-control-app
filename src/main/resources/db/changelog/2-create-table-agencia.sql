create table financial_app.agencia(
    id int(11) not null auto_increment,
    numero int(100) not null,
    digito int(2) not null,
    id_banco int(11) not null,
    INDEX(numero int(100)),
    primary key (id),
    foreign key (id_banco)
);
