package br.com.vr.development.financialcontrolapp.application.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @JsonManagedReference(value = "banco.agencias")
    @Setter
    @OneToMany(mappedBy = "banco", fetch = FetchType.EAGER)
    private List<AgenciaBancaria> agencias;

    public Banco(String codigo) {
        this.codigo = codigo;
    }
}
