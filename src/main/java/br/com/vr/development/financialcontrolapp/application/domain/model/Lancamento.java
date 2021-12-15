package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Entity
@Table(name = "lancamento", schema = "financial_app")
public class Lancamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_lancamento")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "valor")
    private BigDecimal valor;

    @JoinColumn(name = "id_conta_corrente", referencedColumnName = "id")
    @ManyToOne
    private ContaCorrente contaCorrente;

    @Column(name = "descricao")
    private String descricao;
    
    public void addContaCorrente(ContaCorrente contaCorrente) {
        if (this.contaCorrente == null) {
            this.contaCorrente = contaCorrente;
        }
    }

}
