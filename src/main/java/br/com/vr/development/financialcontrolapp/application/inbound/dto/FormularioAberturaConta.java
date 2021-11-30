package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.commons.Celular;
import br.com.vr.development.financialcontrolapp.application.commons.Cnpj;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.EnderecoResidencial;
import br.com.vr.development.financialcontrolapp.application.domain.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.Renda;
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
    private EnderecoResidencial endereco;
    
    @NotNull
    @Valid
    private Celular telefone;
    
    @NotNull
    @Valid
    private Email email;

    @NotNull
    @Valid
    private Renda renda;

    @NotNull
    private BigDecimal valorDepositoAbertura;

    public boolean isValorDepositoPermitido(BigDecimal valorMinimoPermitido) {
        return this.valorDepositoAbertura.compareTo(valorMinimoPermitido) < 0;
    }

    public ContaCorrente toContaCorrente() {
        AgenciaBancaria agencia = 
            new AgenciaBancaria(
                new Banco(new Nome("INTER", ""), "077", new Cnpj("42500796000191")),
                 1, 
                 1);
        
        Correntista correntista = Correntista.builder()
            .nomeCompleto(this.prospect.getNome().getNomeCompleto())
            .email(this.email)
            .endereco(this.endereco)
            .documento(this.prospect.getDocumento())
            .dataDeNascimento(this.prospect.getDataDeNascimento().getData())
            .celular(this.getTelefone())
            .rendaMensal(this.getRenda())
            .build();

        return new ContaCorrente(agencia, correntista);
    }

}
