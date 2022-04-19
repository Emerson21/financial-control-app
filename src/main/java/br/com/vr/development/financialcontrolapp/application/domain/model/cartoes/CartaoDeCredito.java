package br.com.vr.development.financialcontrolapp.application.domain.model.cartoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.CARTAO_CREDITO;

@Slf4j
public class CartaoDeCredito implements Cartao {

    private Limite limite;

    private Fatura fatura;

    public CartaoDeCredito(Limite limite, Competencia competencia, Vencimento vencimento) {
        this.limite = limite;
        this.fatura = new Fatura(competencia, vencimento);
    }

    public Valor limite() {
        return limite.valor();
    }

    @Override
    public void debitar(Valor valor, Descricao descricao, ContaDestino contaDestino) throws LimiteExcedidoException {
        limite.saque(valor);
        contaDestino.deposita(valor, CARTAO_CREDITO);
        fatura.novoLancamento(valor, descricao);
    }

    public List<Lancamento> lancamentos() {
        return fatura.lancamentos();
    }
}
