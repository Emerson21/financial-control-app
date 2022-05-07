package br.com.vr.development.financialcontrolapp.application.domain.model.lancamento;

import br.com.vr.development.financialcontrolapp.application.domain.model.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.CREDITO;
import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.DEBITO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "lancamento", schema = "financial_app")
public class Lancamento implements Movimentacao {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
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

    @JsonBackReference
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

    @Override
    public String imprimir() {
        return String.format("%s | %s | %s | %s | %s", tipoLancamento, tipoTransferencia, valor, descricao, dataHora.format(formatter));
    }

    @Override
    public LocalDate getData() {
        return this.dataHora.toLocalDate();
    }

    public boolean isCredito() {
        return CREDITO == tipoLancamento;
    }

    public boolean isDebito() {
        return DEBITO == tipoLancamento;
    }

}
