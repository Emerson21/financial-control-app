package br.com.vr.development.financialcontrolapp.inbound.v1.dto;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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

    public ContaCorrente toContaCorrente(AgenciaBancaria agenciaBancaria, DepositoInicial depositoInicial) {

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

        return new ContaCorrente(agenciaBancaria, correntista, depositoInicial);
    }

}
