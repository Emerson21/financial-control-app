package br.com.vr.development.financialcontrolapp.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "endereco_correntista", schema = "financial_app")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "cep", nullable = false)
    private String cep;
    
    @NotNull
    @NotBlank
    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero")
    private String numero;

    @NotNull
    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private UF estado;

    @Column(name = "complemento")
    private String complemento; 
    
    @NotNull
    @NotBlank
    @Column(name = "bairro", nullable = false)
    private String bairro;
    
    @NotNull
    @NotBlank
    @Column(name = "municipio", nullable = false)
    private String municipio;

    @NotNull
    @Column(name = "tipo_endereco")
    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;
}
