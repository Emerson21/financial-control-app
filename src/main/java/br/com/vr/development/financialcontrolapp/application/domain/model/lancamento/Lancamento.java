package br.com.vr.development.financialcontrolapp.application.domain.model.lancamento;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.CREDITO;
import static br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento.DEBITO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "lancamento", schema = "financial_app")
public class Lancamento implements Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(name = "tipo_lancamento")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @ToString.Include
    @Getter
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @ToString.Include
    @Getter
    @Column(name = "valor")
    @Embedded
    private Valor valor;

    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    @ManyToOne
    private Conta conta;

    @Getter
    @ToString.Include
    @Column(name = "descricao")
    @Embedded
    private Descricao descricao;


    private Lancamento(Valor valor, Descricao descricao, Conta conta, TipoLancamento tipoLancamento) {
        this.valor = tipoLancamento.calcularSinal(valor);
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
        this.tipoLancamento = tipoLancamento;
        this.conta = conta;
    }

    public static Lancamento criaLancamentoPositivo(Valor valor, Descricao descricao, Conta conta) {
        return new Lancamento(valor, descricao, conta, CREDITO);
    }

    public static Lancamento criaLancamentoNegativo(Valor valor, Descricao descricao, Conta conta) {
            return new Lancamento(valor, descricao, conta, DEBITO);
    }

    public String toString() {
        return String.format("%s | %s ", valor, descricao);
    }
}
