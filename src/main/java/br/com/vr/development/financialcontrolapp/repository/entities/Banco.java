package br.com.vr.development.financialcontrolapp.repository.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banco", schema = "financial_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Banco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nome")
    private String nome;

    // @JoinColumn(referencedColumnName = "id")
    @OneToMany(mappedBy = "banco")
    private List<Agencia> agencias;

}
