create table financial_app.conta_corrente(
    id int(11) not null auto_increment,
    id_agencia int(11) not null,
    numero int(10000) not null
    digito int(2) not null,
    id_correntista int(11) not null,
    index(numero int(10000)),
    primary key (id),
    foreign key (id_agencia) references financial_app.agencia(id),
    foreign key (id_correntista) references financial_app.correntista(id)
);
