package br.com.vr.development.financialcontrolapp.application.domain.model.cartoes;

import br.com.vr.development.financialcontrolapp.application.enums.FuncaoCartao;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FuncoesCartao {

    private List<FuncaoCartao> funcoesCartao = new ArrayList<>();

    public void adicionarFuncao(FuncaoCartao funcao) {
        if (funcoesCartao.contains(funcao)) {
            log.error("Funcao {} já existente", funcao);
            throw new IllegalArgumentException("Funcao já existente");
        }

        this.funcoesCartao.add(funcao);
    }

    public boolean possui(FuncaoCartao funcao) {
        return this.funcoesCartao.contains(funcao);
    }
}
