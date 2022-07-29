package br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
public class TransacaoMessageDTO {

    private UUID correlationId;
    private Valor valor;
    private ContaOrigem contaOrigem;
    private ContaDestino contaDestino;
    private String tipoTransferencia;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AgenciaBancaria {
        private Banco banco;
        private int numero;
        private int digito;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Banco {
        private NomeFantasia nomeFantasia;
        private String codigo;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ContaDestino {
        private Banco banco;
        private AgenciaBancaria agenciaBancaria;
        private int numero;
        private int digito;
        private String nomeCorrentista;
        private Cpf cpf;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ContaOrigem {
        private NomeFantasia nomeFantasia;
        private String codigoBanco;
        private Banco banco;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Cpf {
        private String numero;
        private String tipoDocumento;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class NomeFantasia {
        private String nome;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Valor {
        private BigDecimal value;

        public boolean ehMenorOuIgualAZero() {
            return value.compareTo(BigDecimal.ZERO) <= 0;
        }
    }

}
