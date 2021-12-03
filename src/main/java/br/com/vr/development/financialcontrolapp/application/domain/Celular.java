package br.com.vr.development.financialcontrolapp.application.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
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
 
    public String toString() {
        return String.format("%s%s", this.ddd, this.numero);
    }

}
