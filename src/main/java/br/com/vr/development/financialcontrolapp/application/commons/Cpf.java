package br.com.vr.development.financialcontrolapp.application.commons;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Cpf implements Documento {

    private static final int TAMANHO_CPF = 11;

    @NotBlank
    @NotNull
    private String numero;

    @Override
    public boolean isValido() {
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
