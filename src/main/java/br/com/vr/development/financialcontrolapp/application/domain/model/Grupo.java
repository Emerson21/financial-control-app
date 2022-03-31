package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Iterator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Grupo implements Iterable<Movimentacao> {

    @Getter
    private Object agrupador;
    private Collection<Movimentacao> movimentacoes;


    @Override
    public Iterator<Movimentacao> iterator() {
        return movimentacoes.iterator();
    }
}
