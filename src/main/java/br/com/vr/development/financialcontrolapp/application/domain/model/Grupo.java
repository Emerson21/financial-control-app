package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Grupo implements Iterable<Movimentacao> {

    private List<Movimentacao> movimentacoes;

    @Override
    public Iterator<Movimentacao> iterator() {
        return movimentacoes.iterator();
    }
}
