package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "correntista", schema = "financial_app")
public class Correntista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Embedded
    @AttributeOverride(name ="primeiroNome", column=@Column(name = "nome_completo", nullable = false))
    private Nome nome;

    @NotNull
    @Embedded
    @AttributeOverride(name = "numero", column = @Column(name = "numero_documento", nullable = false))
    private Cpf cpf;

    @NotNull
    @Column(name = "tipo_documento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @NotNull
    @Embedded
    @AttributeOverride(name = "data", column = @Column(name = "data_nascimento", nullable = false))
    private DataNascimento dataNascimento;

    @NotNull
    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "renda_mensal", nullable = false))
    private RendaMensal rendaMensal;

    @NotNull
    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false))
    private Email email;

    @NotNull
    @Embedded
    @AttributeOverride(name = "numero", column = @Column(name = "celular", nullable = false))
    private Celular celular;

    @NotNull
    @JoinColumn(name = "id_correntista")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    public Correntista(Nome nome, Cpf cpf, TipoDocumento tipoDocumento, DataNascimento dataNascimento, RendaMensal rendaMensal, Email email, Celular celular, List<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipoDocumento = tipoDocumento;
        this.dataNascimento = dataNascimento;
        this.rendaMensal = rendaMensal;
        this.email = email;
        this.celular = celular;
        this.enderecos = enderecos;
    }

}
