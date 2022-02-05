package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "banco", schema = "financial_app")
public class Banco {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    @AttributeOverride(name = "nome", column = @Column(name = "nome", nullable = false))
    private NomeFantasia nomeFantasia;

    @Getter
    @Column(name = "codigo")
    private String codigo;

    @Embedded
    @AttributeOverride(name = "numero", column = @Column(name = "cnpj", nullable = false))
    private Cnpj cnpj;

    @Setter
    @OneToMany(mappedBy = "banco", fetch = FetchType.EAGER)
    private List<AgenciaBancaria> agencias;
}
