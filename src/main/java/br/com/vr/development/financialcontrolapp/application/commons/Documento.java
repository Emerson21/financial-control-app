package br.com.vr.development.financialcontrolapp.application.commons;

import java.io.Serializable;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;

public interface Documento extends Serializable {
    
    boolean isValido();
    String getNumero();
    TipoDocumento getTipoDocumento();

}
