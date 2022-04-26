package br.com.vr.development.financialcontrolapp.application.enums;

public enum StatusFatura {
    PAGA, EM_ABERTO, PARCIALMENTE_PAGA;

    public boolean isAberta() {
        return this == EM_ABERTO;
    }
}
