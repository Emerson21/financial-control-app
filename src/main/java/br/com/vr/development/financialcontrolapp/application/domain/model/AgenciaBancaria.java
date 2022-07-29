package br.com.vr.development.financialcontrolapp.application.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @JsonBackReference(value = "agenciaBancaria.banco")
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
