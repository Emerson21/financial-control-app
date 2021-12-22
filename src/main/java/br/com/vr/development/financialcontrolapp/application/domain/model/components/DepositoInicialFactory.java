package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;

@Component
public class DepositoInicialFactory {

    @Value("${conta.abertura.valorMinimo}")
    private BigDecimal valorMinimoPermitido;

    public DepositoInicial create(BigDecimal valorInicial) {
        DepositoInicial depositoInicial = new DepositoInicial(valorInicial);

        if (depositoInicial.ehMenorQue(valorMinimoPermitido)) {
            throw new DepositoInicialException();
        }

        return depositoInicial;
    }

}
