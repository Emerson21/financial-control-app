package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;

public interface Documento {
    
    boolean isValido();
    String getNumero();
    TipoDocumento getTipoDocumento();

}
