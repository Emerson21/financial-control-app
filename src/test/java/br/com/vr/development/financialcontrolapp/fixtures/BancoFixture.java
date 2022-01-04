package br.com.vr.development.financialcontrolapp.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cnpj;
import br.com.vr.development.financialcontrolapp.application.domain.model.NomeFantasia;

public class BancoFixture implements TemplateLoader {

    @Override
    public void load() {
        
        Fixture.of(Banco.class).addTemplate("valid", new Rule() {{
            add("id", 1L);
            add("nomeFantasia", new NomeFantasia("Banco Teste"));
            add("codigo", "Banco Test");
            add("cnpj", new Cnpj(cnpj().toString()));
            // add("agencias", has(1).of(AgenciaBancaria.class, "valid"));
        }});
        
    }
    
}
