package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Celular;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cnpj;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.model.Email;
import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.model.NomeFantasia;
import br.com.vr.development.financialcontrolapp.application.domain.model.Pessoa;
import br.com.vr.development.financialcontrolapp.application.domain.model.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormularioAberturaConta {

    @NotNull
    @Valid
    private Pessoa prospect;
    
    @NotNull
    @Valid
    private List<Endereco> enderecos;
    
    @NotNull
    @Valid
    private Celular telefone;
    
    @NotNull
    @Valid
    private Email email;

    @NotNull
    @Valid
    private RendaMensal renda;

    @NotNull
    private BigDecimal valorDepositoAbertura;

    public ContaCorrente toContaCorrente(DepositoInicial depositoInicial) {

        Banco banco =  Banco.builder()
            .cnpj(new Cnpj("42500796000191"))
            .codigo("077")
            .nomeFantasia(new NomeFantasia("INTER"))
            .build();

        AgenciaBancaria agencia = AgenciaBancaria.builder()
            .banco(banco)
            .numero(1)
            .digito(1).build();
        
        Correntista correntista = Correntista.builder()
            .nome(this.prospect.getNome())
            .email(this.email)
            .enderecos(this.enderecos)
            .cpf(this.prospect.getDocumento())
            .tipoDocumento(this.prospect.getDocumento().getTipoDocumento())
            .dataNascimento(this.prospect.getDataDeNascimento())
            .celular(this.getTelefone())
            .rendaMensal(this.getRenda())
            .build();

        return new ContaCorrente(agencia, correntista, depositoInicial);
    }

}
