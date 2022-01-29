package br.com.vr.development.financialcontrolapp.application.inbound.v1.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Celular;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.model.Email;
import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Pessoa;
import br.com.vr.development.financialcontrolapp.application.domain.model.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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

    @NotNull
    private AgenciaBancariaDTO agenciaBancaria;

    public ContaCorrente toContaCorrente(AgenciaBancaria agenciaBancaria,  DepositoInicial depositoInicial) {

        Correntista correntista = new Correntista(
            this.prospect.getNome(), 
            this.prospect.getDocumento(), 
            this.prospect.getDocumento().getTipoDocumento(),
            this.prospect.getDataDeNascimento(),
            this.getRenda(),
            this.email,
            this.getTelefone(),
            getEnderecos()
        );

        ContaCorrente contaCorrente = new ContaCorrente(agenciaBancaria, correntista, depositoInicial);
        contaCorrente.adicionaDepositoInicialComoLancamento();
        
        return contaCorrente;
    }

}
