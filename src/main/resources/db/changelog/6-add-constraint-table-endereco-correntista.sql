alter table financial_app.endereco_correntista ADD CONSTRAINT id_fk_correntista
foreign key (id_correntista) references financial_app.correntista(id);
