package br.com.vr.development.financialcontrolapp.application.domain.model;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.StringUtils;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
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
