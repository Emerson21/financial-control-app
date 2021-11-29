alter table financial_app.conta_corrente ADD CONSTRAINT id_fk_conta_corrente_endereco
foreign key (id_endereco) references financial_app.endereco(id);
