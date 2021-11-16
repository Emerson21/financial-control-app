package br.com.vr.development.financialcontrolapp.application.domain.cartoes;

import br.com.vr.development.financialcontrolapp.application.enums.FuncaoCartao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartaoDebito extends Cartao {

    @Override
    public FuncaoCartao getFuncao() {
        return FuncaoCartao.DEBITO;
    }
    
}
