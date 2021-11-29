package br.com.vr.development.financialcontrolapp.repository.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn(name ="id_agencia", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Agencia agencia;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "digito")
    private int digito;

    @JoinColumn(name = "id_correntista")
    @OneToOne(cascade = CascadeType.ALL)
    private Correntista correntista;

}
