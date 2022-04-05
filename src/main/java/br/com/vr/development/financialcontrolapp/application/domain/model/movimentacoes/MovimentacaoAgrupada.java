package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MovimentacaoAgrupada {

    public static final String ANSI_GREEN = "\u001B[32m";

    @Getter
    private Grupo grupo;
    private String keyNameField;
    private String agrupador;

    public String lerNome() {
        return ANSI_GREEN + keyNameField + " : " + agrupador + " | Movimentacoes";
    }
}
