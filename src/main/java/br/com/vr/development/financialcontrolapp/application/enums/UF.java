package br.com.vr.development.financialcontrolapp.application.enums;

public enum UF {

    ACRE("AC", "Rio Branco"),
    ALAGOAS("AL", "Maceió"),
    AMAPA("AP", "Macapá"),
    AMAZONAS("AM", "Manaus"),
    BAHIA("BA",	"Salvador"),
    CEARA("CE", "Fortaleza"),
    DISTRITO_FEDERAL("DF", "Brasília"),
    ESPIRITO_SANTO("ES", "Vitória"),
    GOIAS("GO",	"Goiânia"),
    MARANHAO("MA", "São Luís"),
    MATO_GROSSO("MT", "Cuiabá"),
    MATO_GROSSO_SUL("MS", "Campo Grande"),
    MINAS_GERAIS("MG", "Belo Horizonte"),
    PARA("PA", "Belém"),
    PARAIBA("PB", "João Pessoa"),
    PARANA("PR", "Curitiba"),
    PERNAMBUCO("PE", "Recife"),
    PIAUI("PI",	"Teresina"),
    RIO_DE_JANEIRO("RJ", "Rio de Janeiro"),
    RIO_GRANDE_DO_NORTE("RN", "Natal"),
    RIO_GRANDE_DO_SUL("RS", "Porto Alegre"),
    RONDONIA("RO", "Porto Velho"),
    RORAIMA("RR", "Boa Vista"),
    SANTA_CATARINA("SC", "Florianópolis"),
    SAO_PAULO("SP", "São Paulo"),
    SERGIPE("SE", "Aracaju"),
    TOCANTINS("TO", "Palmas");

    private String sigla;
    private String capital;

    private UF(String sigla, String capital) {
        this.sigla = sigla;
        this.capital = capital;
    }


    public String getSigla() {
        return  this.sigla;
    }

    public String getCapital() {
        return this.capital;
    }

}
