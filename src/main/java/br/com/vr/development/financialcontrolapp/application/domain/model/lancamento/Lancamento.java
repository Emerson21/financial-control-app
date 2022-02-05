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
import javax.persistence.Transient;

import br.com.vr.development.financialcontrolapp.application.domain.model.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Poupanca;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Getter
    @Column(name = "valor")
    @Embedded
    private Valor valor;

    @JoinColumn(name = "id_conta_corrente", referencedColumnName = "id")
    @ManyToOne
    private ContaCorrente contaCorrente;

    @Transient
    private Poupanca poupanca;

    @Column(name = "descricao")
    @Embedded
    private Descricao descricao;


    private Lancamento(Valor valor, Descricao descricao, TipoLancamento tipoLancamento) {
        this.valor = tipoLancamento.calcularSinal(valor);
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
        this.tipoLancamento = tipoLancamento;
    }


    private Lancamento(Valor valor, Descricao descricao, Conta conta, TipoLancamento tipoLancamento) {
        this(valor, descricao, tipoLancamento);
        
        //nao gostei do if dentro do construtor
        if (conta instanceof ContaCorrente ) {
            this.contaCorrente = (ContaCorrente) conta;    
        } else {
            this.poupanca = (Poupanca) conta;
        }
    }


    //nao gostei da solucao
    private Lancamento(Valor valor, Descricao descricao, ContaCorrente contaCorrente, TipoLancamento tipoLancamento) {
        this(valor, descricao, tipoLancamento);
        this.contaCorrente = contaCorrente;
    }

    //nao gostei da solucao
    private Lancamento(Valor valor, Descricao descricao, Poupanca poupanca, TipoLancamento tipoLancamento) {
        this(valor, descricao, tipoLancamento);
        this.poupanca = poupanca;
    }

    public static Lancamento criaLancamentoPositivo(Valor valor, Descricao descricao, Conta conta) {
        return new Lancamento(valor, descricao, conta, CREDITO);
    }


    // public static Lancamento criaLancamentoPositivo(Valor valor, Descricao descricao, Conta conta) {
    //     if (conta instanceof ContaCorrente ) {
    //         return new Lancamento(valor, descricao, (ContaCorrente) conta, CREDITO);
    //     }

    //     return new Lancamento(valor, descricao, (Poupanca) conta, CREDITO);
        
    // }

    public static Lancamento criaLancamentoNegativo(Valor valor, Descricao descricao, Conta conta) {
        if (conta instanceof ContaCorrente) {
            return new Lancamento(valor, descricao, (ContaCorrente) conta, DEBITO); 
        }

        return new Lancamento(valor, descricao, (Poupanca) conta, DEBITO); 
    }


}
