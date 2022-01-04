package br.com.vr.development.financialcontrolapp.application.domain.model.lancamento;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.CREDITO;
import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.DEBITO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import br.com.vr.development.financialcontrolapp.exception.LancamentoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "lancamento", schema = "financial_app")
public class Lancamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_lancamento")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "valor")
    @Embedded
    private Valor valor;

    @JoinColumn(name = "id_conta_corrente", referencedColumnName = "id")
    @ManyToOne
    private ContaCorrente contaCorrente;

    @Column(name = "descricao")
    @Embedded
    private Descricao descricao;

    private Lancamento(Valor valor, Descricao descricao, ContaCorrente contaCorrente, TipoLancamento tipoLancamento) {
        this.valor = valor;
        this.descricao = descricao;
        this.contaCorrente = contaCorrente;
        this.dataHora = LocalDateTime.now();
        this.tipoLancamento = tipoLancamento;
    }

    public void addContaCorrente(ContaCorrente contaCorrente) {
        if (this.contaCorrente == null) {
            this.contaCorrente = contaCorrente;
        }
    }

    public static Lancamento criaLancamentoPositivo(Valor valor, Descricao descricao, ContaCorrente contaCorrente) {
        if (valor.ehNegativo()) {
            throw new LancamentoInvalidoException();
        }
        
        return new Lancamento(CREDITO.calcularSinal(valor),descricao, contaCorrente, CREDITO);
    }

    public static Lancamento criaLancamentoNegativo(Valor valor, Descricao descricao, ContaCorrente contaCorrente) {
        if (!valor.ehNegativo()) {
            throw new LancamentoInvalidoException();
        }

        return new Lancamento(valor, descricao, contaCorrente, DEBITO); 
    }

}
