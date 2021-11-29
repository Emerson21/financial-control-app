package br.com.vr.development.financialcontrolapp.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "agencia", schema = "financial_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "digito")
    private int digito;

    @ManyToOne
    private Banco banco;

}
