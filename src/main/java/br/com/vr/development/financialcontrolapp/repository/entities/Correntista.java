package br.com.vr.development.financialcontrolapp.repository.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "correntista", schema = "financial_app")
@Data
@Builder
public class Correntista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    
    @NotNull
    @NotBlank
    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @NotNull
    @Column(name = "tipoDocumento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    // @JoinColumn(referencedColumnName = "id_endereco")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "correntista")
    private List<EnderecoCorrentista> enderecos;

    @NotNull
    @Column(name = "dataDeNascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @NotNull
    @Column(name = "rendaMensal", nullable = false)
    private BigDecimal rendaMensal;

    @NotNull
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "celular", nullable = false)
    private String celular;

    @JoinColumn(name = "id_conta_corrente", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private ContaCorrente contaCorrente;

}
