package br.com.vr.development.financialcontrolapp.application.commons;

import org.apache.commons.lang3.StringUtils;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cpf implements Documento {

    private String numero;

    @Override
    public boolean isValido() {
        if (StringUtils.isEmpty(this.numero)) {
            return false;
        }

        String cpf = this.numero.replaceAll("\\D", "");
        return StringUtils.isNotBlank(cpf) && cpf.length() == 11;
    }

    @Override
    public String getNumero() {
        return this.numero;
    }

    @Override
    public TipoDocumento getTipoDocumento() {
        return TipoDocumento.CPF;
    }
    
}
