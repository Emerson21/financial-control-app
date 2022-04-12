package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.FuncoesCartao;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.FuncaoCartao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cartao {

    private FuncoesCartao funcoesCartao;
    private ContaOrigem contaOrigem;

    public Cartao(FuncoesCartao funcoesCartao, ContaOrigem contaOrigem) {
        this.funcoesCartao = funcoesCartao;
        this.contaOrigem = contaOrigem;
    }

    public void debitar(Valor valor, FuncaoCartao funcao, ContaDestino contaDestino) {
        if (!funcoesCartao.possui(funcao)) {
            log.error("Cartao nao possui a funcaoo {}", funcao);
            throw new IllegalArgumentException("Cartao nao possui a funcao para realizar a transacao");
        }

        funcao.debitar(valor, this.contaOrigem, contaDestino);
    }
}
