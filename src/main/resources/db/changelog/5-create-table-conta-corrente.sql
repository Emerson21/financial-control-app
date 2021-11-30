create table financial_app.conta_corrente(
    id int(11) not null auto_increment,
    id_agencia int(11) not null,
    numero int(255) not null,
    digito int(2) not null,
    id_correntista int(11) not null,
    primary key (id)
);
