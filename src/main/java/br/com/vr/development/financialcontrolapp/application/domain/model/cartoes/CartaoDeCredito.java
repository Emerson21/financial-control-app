package br.com.vr.development.financialcontrolapp.application.domain.model.cartoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.application.enums.StatusFatura;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static br.com.vr.development.financialcontrolapp.application.enums.StatusFatura.*;
import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.CARTAO_CREDITO;

@Slf4j
public class CartaoDeCredito implements Cartao {

    private Limite limite;
    private List<Fatura> faturas = new ArrayList<>();

    public CartaoDeCredito(Limite limite) {
        this.limite = limite;
        this.faturas.add(new Fatura(Competencia.JANEIRO, Vencimento.dia(5)));
    }

    public Valor limite() {
        return limite.valor();
    }

    @Override
    public void debitar(Valor valor, Descricao descricao, ContaDestino contaDestino) throws LimiteExcedidoException {
        limite.saque(valor);
        contaDestino.deposita(valor, CARTAO_CREDITO);
        faturas.get(0).novoLancamento(valor, descricao);
    }

    public List<Lancamento> lancamentos() {
        return faturas.get(0).lancamentos();
    }

    public Valor valorFatura(Competencia competencia) throws Exception {
        Fatura fatura = this.faturas.stream().filter(f -> f.ehDa(competencia)).findFirst().orElseThrow(Exception::new);
        return fatura.valor();
    }

    public Fatura fatura(Competencia competencia) throws Exception {
        return faturas.stream().filter(f -> f.ehDa(competencia)).findFirst().orElseThrow(Exception::new);
    }

    public void pagarFatura(Competencia competencia, Valor valorPagamento, ContaOrigem contaOrigem) {
        try {
            Fatura fatura = this.fatura(competencia);
            contaOrigem.saque(valorPagamento, TipoTransferencia.PAGAMENTO_DE_FATURA);
            limite.creditar(valorPagamento);

            if (ehPagamentoTotal(fatura, valorPagamento)) {
                fatura.setStatus(PAGA);
                return;
            }

            fatura.setStatus(PARCIALMENTE_PAGA);
            Valor valorRemanescente = fatura.valor().menos(valorPagamento);
            faturaEmAberto().novoLancamento(valorRemanescente, new Descricao("Valor remanescente de fatura"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean ehPagamentoTotal(Fatura fatura, Valor valorPagamento) {
        return fatura.valor().menos(valorPagamento).equals(Valor.ZERO);
    }

    private Fatura faturaEmAberto() throws Exception {
        return this.faturas.stream().filter(f -> f.status() == EM_ABERTO).findFirst().orElseThrow(Exception::new);
    }


}
