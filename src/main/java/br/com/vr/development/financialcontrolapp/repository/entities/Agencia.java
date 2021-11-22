package br.com.vr.development.financialcontrolapp.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "agencia", schema = "financial_app")
@Data
public class Agencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "digito")
    private int digito;

    @ManyToOne
    private Banco banco;

}
