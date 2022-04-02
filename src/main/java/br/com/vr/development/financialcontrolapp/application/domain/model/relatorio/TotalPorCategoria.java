package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TotalPorCategoria {

    private Transacao.Categoria categoria;
    private Valor valor;

    public TotalPorCategoria(Transacao.Categoria categoria, Valor valor) {
        this.categoria = categoria;
        this.valor = valor;
    }
}
