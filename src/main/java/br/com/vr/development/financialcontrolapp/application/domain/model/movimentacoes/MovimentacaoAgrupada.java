package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Grupo;

import java.util.Iterator;
import java.util.List;

public class MovimentacaoAgrupada implements Iterable<Grupo> {

    public static final String ANSI_GREEN = "\u001B[32m";

    private List<Grupo> grupos;
    private String keyNameField;

    public MovimentacaoAgrupada(List<Grupo> grupos, String keyNameField) {
        this.grupos = grupos;
        this.keyNameField = keyNameField;
    }

    @Override
    public Iterator<Grupo> iterator() {
        return this.grupos.iterator();
    }

    public String lerNome() {
        return ANSI_GREEN + keyNameField + " : " + grupos.stream()
                .findFirst()
                .get()
                .getAgrupador().toString() + " | Movimentações";
    }
}
