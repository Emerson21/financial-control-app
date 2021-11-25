package br.com.vr.development.financialcontrolapp.application.commons;

import org.apache.commons.lang3.StringUtils;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Cnpj implements Documento {

    private static final int TAMANHO_CNPJ = 14;

    private String numero;

    @Override
    public boolean isValido() {
        if (StringUtils.isEmpty(this.numero)) {
            return false;
        }

        String cnpj = this.numero.replaceAll("\\D", "");
        return StringUtils.isNotBlank(cnpj) && cnpj.length() == TAMANHO_CNPJ;
    }

    @Override
    public String getNumero() {
        return this.numero;
    }

    @Override
    public TipoDocumento getTipoDocumento() {
        return TipoDocumento.CNPJ;
    }
    
}
