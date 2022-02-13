package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public abstract class Conta implements ContaDestino {

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

    @OneToMany(cascade = CascadeType.ALL)
    Set<Lancamento> lancamentos;

    @Embedded
    @NotNull
    @Column(name = "deposito_inicial", nullable = false)
    DepositoInicial depositoInicial;

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

    protected Set<Lancamento> getLancamentos() {
        return Optional.of(this.lancamentos).isEmpty()
            ? new HashSet<>()
            : this.lancamentos;
    }

}
