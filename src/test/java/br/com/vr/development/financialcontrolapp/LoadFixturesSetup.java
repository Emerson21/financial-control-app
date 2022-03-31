package br.com.vr.development.financialcontrolapp;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;

public abstract class LoadFixturesSetup {

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.vr.development.financialcontrolapp.fixtures");
    }

}
