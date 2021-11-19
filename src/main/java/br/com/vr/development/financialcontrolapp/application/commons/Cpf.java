package br.com.vr.development.financialcontrolapp.application.commons;

import org.apache.commons.lang3.StringUtils;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Cpf implements Documento {

    private static final int TAMANHO_CPF = 11;

    private String numero;

    @Override
    public boolean isValido() {
        if (StringUtils.isEmpty(this.numero)) {
            return false;
        }

        String cpf = this.numero.replaceAll("\\D", "");
        return StringUtils.isNotBlank(cpf) && cpf.length() == TAMANHO_CPF;
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
