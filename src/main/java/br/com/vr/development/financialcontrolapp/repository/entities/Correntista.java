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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.Data;

@Entity
@Table(name = "correntista", schema = "financial_app")
@Data
public class Correntista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "nomeCompleto", nullable = false)
    private String nomeCompleto;
    
    @NotNull
    @NotBlank
    @Column(name = "numeroDocumento", nullable = false)
    private String numeroDocumento;

    @NotNull
    @NotBlank
    @Column(name = "tipoDocumento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @JoinColumn(columnDefinition = "", referencedColumnName = "")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "")
    private List<EnderecoCorrentista> enderecos;

    @NotNull
    @NotBlank
    @Column(name = "dataDeNascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @NotNull
    @NotBlank
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

}
