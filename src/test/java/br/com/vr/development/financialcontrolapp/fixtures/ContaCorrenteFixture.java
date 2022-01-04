package br.com.vr.development.financialcontrolapp.fixtures;

import java.math.BigDecimal;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;

public class ContaCorrenteFixture implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(ContaCorrente.class).addTemplate("valid", new Rule() {{
            add("id", 1L);
            add("agencia", one(AgenciaBancaria.class, "valid"));
            add("numero", 123456789L);
            add("digito", 1);
            // add("correntista", one(Correntista.class, "valid"));
            // add("lancamentos", has(1).of(Lancamento.class, "valid"));
            add("depositoInicial", new DepositoInicialFactory(new BigDecimal("50")).create(new BigDecimal("50")));
        }});

        
    }
    
}
