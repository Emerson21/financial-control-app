package br.com.vr.development.financialcontrolapp.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;

public class AgenciaBancariaFixture implements TemplateLoader {

    @Override
    public void load() {
        
        Fixture.of(AgenciaBancaria.class).addTemplate("valid", new Rule() {{
            add("id", 1L);
            add("banco", one(Banco.class, "valid"));
            add("numero", 123);
            add("digito", 1);
        }}); 
        
    }


}
