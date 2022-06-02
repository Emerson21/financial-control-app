package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestinoInterna;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transacoes_processadas")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cpf;

    @Embedded
    private Valor valor;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoTransferencia tipo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Embedded
    private ContaDestinoDTO conta;

    public Transacao(String cpf, Valor valor, TipoTransferencia tipo, ContaDestinoDTO contaDestino, LocalDateTime dataHora) {
        this.cpf = cpf;
        this.valor = valor;
        this.tipo = tipo;
        this.conta = contaDestino;
        this.dataHora = dataHora;
    }

    public ContaDestinoInterna toModel() {
        return null;
    }

    public br.com.vr.development.financialcontrolapp.application.domain.model.Valor toValorModel() {
        return new br.com.vr.development.financialcontrolapp.application.domain.model.Valor(valor.getValue());
    }

    public Cpf getCpfModel() {
        return new Cpf(cpf);
    }
}
