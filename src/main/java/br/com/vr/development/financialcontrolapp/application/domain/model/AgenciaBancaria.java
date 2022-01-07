package br.com.vr.development.financialcontrolapp.application.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "agencia", schema = "financial_app")
public class AgenciaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_banco", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Banco banco;

    @NotNull
    @Column(name = "numero")
    private Integer numero;

    @NotNull
    @Column(name = "digito")
    private Integer digito;


    public AgenciaBancaria(Integer numero, Integer digito) {
        this.numero = numero;
        this.digito = digito;
    }

}
