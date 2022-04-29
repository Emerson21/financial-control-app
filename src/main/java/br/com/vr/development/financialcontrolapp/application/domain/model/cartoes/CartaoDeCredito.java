package br.com.vr.development.financialcontrolapp.application.domain.model.cartoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.CARTAO_CREDITO;

@Slf4j
public class CartaoDeCredito implements Cartao {

    private Limite limite;
    private List<Fatura> faturas = new ArrayList<>();

    public CartaoDeCredito(Limite limite, Fatura fatura) {
        this.limite = limite;
        this.faturas.add(fatura);
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

    public void pagarFatura(Competencia competencia, Valor valorPagamento, ContaCorrente contaOrigem) throws Exception {
        Fatura fatura = this.fatura(competencia);
        contaOrigem.pagar(fatura, valorPagamento);
        limite.creditar(valorPagamento);

        if (fatura.status().isParcialmentePaga()) {
            Valor valorRemanescente = fatura.valor().menos(valorPagamento);
            faturaEmAberto(competencia).novoLancamento(valorRemanescente, new Descricao("Valor remanescente de fatura"));
        }
    }

    private Fatura faturaEmAberto(Competencia competencia) throws Exception {
        return this.faturas.stream()
                .filter(f -> f.status().isAberta())
                .findFirst()
                .orElse(criarNovaFatura(competencia.proxima()));
    }

    private Fatura criarNovaFatura(Competencia competencia) {
        Fatura fatura = new Fatura(competencia, Vencimento.dia(5));
        this.faturas.add(fatura);
        return fatura;
    }

}
