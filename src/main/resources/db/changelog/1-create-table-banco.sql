create table financial_app.banco(
    id int(11) not null auto_increment,
    codigo varchar(100) not null,
    nome longtext not null,
    INDEX(codigo varchar(100)),
    primary key (id)
);
