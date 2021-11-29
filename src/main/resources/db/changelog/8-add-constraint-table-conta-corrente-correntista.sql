alter table financial_app.conta_corrente ADD CONSTRAINT id_fk_cc_correntista
foreign key (id_correntista) references financial_app.correntista(id);
