package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento.criaLancamentoNegativo;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta")
public class Conta implements ContaDestino, ContaOrigem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name ="id_agencia", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    AgenciaBancaria agencia;

    @NotNull
    @Column(name = "numero", nullable = false)
    Long numero;

    @NotNull
    @Column(name = "digito", nullable = false)
    int digito;

    @JoinColumn(name = "id_correntista", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    Correntista correntista;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    Set<Lancamento> lancamentos;

    @Embedded
    @NotNull
    @Column(name = "deposito_inicial", nullable = false)
    DepositoInicial depositoInicial;

    public Conta(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(Integer.MAX_VALUE);
        this.correntista = correntista;
        this.depositoInicial = depositoInicial;
        this.adicionaDepositoInicialComoLancamento();
    }


    public Valor getSaldo() {
        return !possuiSaldo()
                ? Valor.ZERO
                : getLancamentos().stream()
                .map(Lancamento::getValor)
                .reduce(Valor.ZERO, Valor::adicionar);
    }

    void adicionaDepositoInicialComoLancamento() {
        if (this.lancamentos == null) {
            lancamentos = new HashSet<>();
        }

        this.lancamentos.add(this.depositoInicial.toLancamento(this));
    };

    protected void adicionar(Lancamento lancamento) {
        getLancamentos().add(lancamento);
    }

    protected boolean possuiSaldo() {
        return getLancamentos() != null && !getLancamentos().isEmpty();
    }

    public Set<Lancamento> getLancamentos() {
        if (this.lancamentos == null)
            this.lancamentos = new HashSet<>();

        return this.lancamentos;
    }

    @Override
    public void deposita(Valor valor) {
        adicionar(Lancamento.criaLancamentoPositivo(valor, new Descricao("Transferencia entre contas"), this));
    }

    private boolean possuiSaldoDisponivel(Valor valor) {
        return getSaldo().compareTo(valor) >= 0;
    }

    @Override
    public void saque(Valor valor) {
        if (!possuiSaldoDisponivel(valor)) {
            throw new SaldoInsuficienteException();
        }

        adicionar(
                criaLancamentoNegativo(valor, new Descricao("Transferencia entre contas correntes"), this)
        );
    }

    Extrato getExtrato(Periodo periodo) {

        return new Extrato(getLancamentos(), new Periodo(LocalDate.now().minusDays(7L), LocalDate.now()));
    }

}

