package br.com.vr.development.financialcontrolapp.application.domain.model.conta;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import br.com.vr.development.financialcontrolapp.infrastructure.gateway.TransferenciaExternaClient;

public class ContaExternaTest {

    private TransferenciaExternaClient client;

    void deveRealizarUmDepositoParaUmBancoDiferenteDaContaOrigem() {

        Conta conta = ContaCorrenteFixture.create();


    }

}
