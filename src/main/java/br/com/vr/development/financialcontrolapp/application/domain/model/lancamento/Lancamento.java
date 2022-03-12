package br.com.vr.development.financialcontrolapp.application.domain.model.lancamento;

import br.com.vr.development.financialcontrolapp.application.domain.model.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.CREDITO;
import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.DEBITO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "lancamento", schema = "financial_app")
public class Lancamento implements Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_lancamento")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @Getter
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Getter
    @Column(name = "valor")
    @Embedded
    private Valor valor;

    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    @ManyToOne
    private Conta conta;

    @Getter
    @Column(name = "descricao")
    @Embedded
    private Descricao descricao;

    @Getter
    @Column(name = "tipo_transferencia")
    @Enumerated(EnumType.STRING)
    private TipoTransferencia tipoTransferencia;


    private Lancamento(Valor valor, Descricao descricao, Conta conta, TipoLancamento tipoLancamento, TipoTransferencia tipoTransferencia) {
        this.valor = tipoLancamento.calcularSinal(valor);
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
        this.tipoLancamento = tipoLancamento;
        this.conta = conta;
        this.tipoTransferencia = tipoTransferencia;
    }

    public static Lancamento criaLancamentoPositivo(Valor valor, Descricao descricao, Conta conta, TipoTransferencia tipoTransferencia) {
        return new Lancamento(valor, descricao, conta, CREDITO, tipoTransferencia);
    }

    public static Lancamento criaLancamentoNegativo(Valor valor, Descricao descricao, Conta conta, TipoTransferencia tipoTransferencia) {
            return new Lancamento(valor, descricao, conta, DEBITO, tipoTransferencia);
    }

    public String toString() {
        return String.format("%s | %s | %s ", tipoTransferencia, valor, descricao);
    }
}
