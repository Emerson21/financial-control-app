package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;

import java.io.Serializable;
import java.util.Set;

public interface ContaDestino extends Serializable {
    Set<Lancamento> getLancamentos();
}
