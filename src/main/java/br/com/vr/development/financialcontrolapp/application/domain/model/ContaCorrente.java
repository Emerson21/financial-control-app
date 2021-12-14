package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import br.com.vr.development.financialcontrolapp.exception.ValorMinimoInvalidoExcepton;
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
    @Column(name = "numero")
    private Long numero;
    
    @NotNull
    @Column(name = "digito")
    private int digito;

    @JoinColumn(name = "id_correntista", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Correntista correntista;

    @OneToMany(mappedBy = "contaCorrente")
    private List<Lancamento> lancamentos;

    public ContaCorrente(AgenciaBancaria agencia, Correntista correntista, List<Lancamento> lancamentos, BigDecimal valorMinimoPermitido) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(Integer.MAX_VALUE);
        this.correntista = correntista;
        this.lancamentos = lancamentos;
        this.validaValorMinimo(valorMinimoPermitido);
    }

    private void validaValorMinimo(BigDecimal valorMinimoPermitido) {
        if (!Optional.ofNullable(this.lancamentos).isPresent()) {
            throw new ValorMinimoInvalidoExcepton();
        }

        Optional.ofNullable(this.lancamentos).ifPresent(values -> {
            Lancamento lancamento = values.stream().findFirst().orElseThrow(ValorMinimoInvalidoExcepton::new);
            if (lancamento.getValor().compareTo(valorMinimoPermitido) < 0) {
                throw new ValorMinimoInvalidoExcepton();
            }
        });

    }

}
