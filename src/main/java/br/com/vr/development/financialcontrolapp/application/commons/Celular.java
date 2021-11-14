package br.com.vr.development.financialcontrolapp.application.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Celular implements Telefone {

    private String ddd;
    private String numero;

    @Override
    public String getDdd() {
        return this.ddd;
    }

    @Override
    public String getNumero() {
        return this.numero;
    }
    
}
