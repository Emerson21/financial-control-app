package br.com.vr.development.financialcontrolapp.application.domain.model.conta;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestinoInterna;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento.criaLancamentoNegativo;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta")
public class Conta implements ContaDestinoInterna, ContaOrigem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name ="id_agencia", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    protected AgenciaBancaria agencia;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @NotNull
    @Column(name = "digito", nullable = false)
    private int digito;

    @JoinColumn(name = "id_correntista", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Correntista correntista;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private Set<Lancamento> lancamentos = new HashSet<>();

    @Embedded
    @NotNull
    @Column(name = "deposito_inicial", nullable = false)
    private DepositoInicial depositoInicial;

    public Conta(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(Integer.MAX_VALUE);
        this.correntista = correntista;
        this.depositoInicial = depositoInicial;
        this.adicionaDepositoInicialComoLancamento();
    }

    public Conta(Conta conta) {
        this(conta.agencia, conta.correntista, conta.depositoInicial);
    }

    public Valor getSaldo() {
        return !possuiSaldo()
                ? Valor.ZERO
                : getLancamentos().stream()
                .map(Lancamento::getValor)
                .reduce(Valor.ZERO, Valor::mais);
    }

    void adicionaDepositoInicialComoLancamento() {
        this.lancamentos.add(this.depositoInicial.toLancamento(this));
    }

    protected void adicionar(Lancamento lancamento) {
        getLancamentos().add(lancamento);
    }

    protected boolean possuiSaldo() {
        return getLancamentos() != null && !getLancamentos().isEmpty();
    }

    public Set<Lancamento> getLancamentos() {
        return this.lancamentos;
    }

    @Override
    public Banco getBanco() {
        return this.agencia.getBanco();
    }

    @Override
    public void deposita(Valor valor, TipoTransferencia tipoTransferencia) {
        adicionar(Lancamento.criaLancamentoPositivo(valor, new Descricao("Transferencia entre contas"), this, tipoTransferencia));
    }

    private boolean possuiSaldoDisponivel(Valor valor) {
        return getSaldo().compareTo(valor) >= 0;
    }

    @Override
    public void saque(Valor valor, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        if (!possuiSaldoDisponivel(valor)) {
            throw new SaldoInsuficienteException();
        }

        adicionar(criaLancamentoNegativo(valor, tipoTransferencia.descricao(), this, tipoTransferencia));
    }

    public void pagar(Fatura fatura, Valor valor) throws SaldoInsuficienteException {
        this.saque(valor, TipoTransferencia.PAGAMENTO_DE_FATURA);
        fatura.pagar(valor);
    }
}
