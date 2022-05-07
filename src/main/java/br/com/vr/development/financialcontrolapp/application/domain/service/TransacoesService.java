package br.com.vr.development.financialcontrolapp.application.domain.service;

import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.TransacaoDTO;

public interface TransacoesService {
    void transacionar(TransacaoDTO transacaoDTO);
}
