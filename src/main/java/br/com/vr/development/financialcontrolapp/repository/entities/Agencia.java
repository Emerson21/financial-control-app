package br.com.vr.development.financialcontrolapp.repository.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agencia", schema = "financial_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "digito")
    private int digito;

    @JoinColumn(name = "id_banco", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Banco banco;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContaCorrente> contasCorrentes;

}
