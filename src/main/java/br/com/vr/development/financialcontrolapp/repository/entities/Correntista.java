package br.com.vr.development.financialcontrolapp.repository.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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

import br.com.vr.development.financialcontrolapp.application.commons.Celular;
import br.com.vr.development.financialcontrolapp.application.commons.Cpf;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.domain.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.domain.Nome;
import br.com.vr.development.financialcontrolapp.application.domain.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Embedded
    @AttributeOverride(name ="nome", column=@Column(name = "nome_completo", nullable = false))
    private Nome nome;
    
    @NotNull
    @NotBlank
    @Embedded
    @AttributeOverride(name = "numero", column = @Column(name = "numero_documento", nullable = false))
    private Cpf cpf;

    @NotNull
    @Column(name = "tipo_documento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private DataNascimento dataNascimento;

    @NotNull
    @Column(name = "renda_mensal", nullable = false)
    private RendaMensal rendaMensal;

    @NotNull
    @NotBlank
    @Column(name = "email", nullable = false)
    private Email email;

    @NotNull
    @NotBlank
    @Column(name = "celular", nullable = false)
    private Celular celular;


    @NotNull
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoCorrentista enderecoCorrentista;

}
