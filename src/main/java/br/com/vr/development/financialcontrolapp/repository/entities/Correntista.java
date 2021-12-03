package br.com.vr.development.financialcontrolapp.repository.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.*;

@Entity
@Table(name = "correntista", schema = "financial_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "tipo_documento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @NotNull
    @Column(name = "renda_mensal", nullable = false)
    private BigDecimal rendaMensal;

    @NotNull
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "celular", nullable = false)
    private String celular;


    @NotNull
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoCorrentista enderecoCorrentista;

}
