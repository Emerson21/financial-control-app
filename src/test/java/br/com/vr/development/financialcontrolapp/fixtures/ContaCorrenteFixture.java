package br.com.vr.development.financialcontrolapp.fixtures;

import java.math.BigDecimal;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;

public class ContaCorrenteFixture implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(ContaCorrente.class).addTemplate("valid", new Rule() {{
            add("id", 1L);
            add("agencia", one(AgenciaBancaria.class, "valid"));
            add("numero", 123456789L);
            add("digito", 1);
            add("depositoInicial", new DepositoInicialFactory(new BigDecimal("50")).create(new BigDecimal("50")));
        }});
    }

    public static ContaCorrente create() {
        Banco banco = Banco.builder()
                .cnpj(new Cnpj("73811174000108"))
                .codigo("1")
                .nomeFantasia(new NomeFantasia("Banco VR"))
                .build();

        AgenciaBancaria agencia = AgenciaBancaria.builder().digito(1).numero(1).banco(banco).build();

        DepositoInicial depositoInicial = new DepositoInicialFactory(new BigDecimal(50)).create(new BigDecimal(50));

        return new ContaCorrente(agencia, CorrentistaFixture.create(), depositoInicial);
    }
    
}
