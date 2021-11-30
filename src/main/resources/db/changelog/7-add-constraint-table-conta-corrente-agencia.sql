alter table financial_app.conta_corrente ADD CONSTRAINT id_fk_agencia
foreign key (id_agencia) references financial_app.agencia(id);
