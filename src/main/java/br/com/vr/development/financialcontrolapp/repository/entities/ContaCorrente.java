package br.com.vr.development.financialcontrolapp.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conta_corrente", schema = "financial_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Agencia agencia;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "digito")
    private int digito;

    @OneToOne(mappedBy = "contaCorrente")
    private Correntista correntista;

}
