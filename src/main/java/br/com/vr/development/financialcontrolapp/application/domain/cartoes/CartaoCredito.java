package br.com.vr.development.financialcontrolapp.application.domain.cartoes;

import java.math.BigDecimal;

import br.com.vr.development.financialcontrolapp.application.enums.FuncaoCartao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartaoCredito extends Cartao {
    
    @Override
    public FuncaoCartao getFuncao() {
        return FuncaoCartao.CREDITO;
    }

    public BigDecimal getLimite() {
        return BigDecimal.ZERO;
    }

}
   