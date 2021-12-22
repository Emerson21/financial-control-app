package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
@Entity
@Table(name = "conta_corrente", schema = "financial_app")
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name ="id_agencia", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private AgenciaBancaria agencia;
    
    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;
    
    @NotNull
    @Column(name = "digito", nullable = false)
    private int digito;

    @JoinColumn(name = "id_correntista", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Correntista correntista;

    @OneToMany(mappedBy = "contaCorrente", cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;

    @Embedded
    @NotNull
    @Column(name = "deposito_inicial", nullable = false)
    private DepositoInicial depositoInicial;

    public ContaCorrente(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(Integer.MAX_VALUE);
        this.correntista = correntista;
        this.depositoInicial = depositoInicial;
    }

    public String getCodigoBanco() {
        return this.agencia.getBanco().getCodigo();
    }

    public void adicionaDepositoInicialComoLancamento() {
        if (lancamentos == null) {
            lancamentos = new ArrayList<>();
        }
        
        this.lancamentos.add(this.depositoInicial.toLancemento(this));
    }


}
