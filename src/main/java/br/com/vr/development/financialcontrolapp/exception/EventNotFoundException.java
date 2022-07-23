package br.com.vr.development.financialcontrolapp.exception;

import lombok.Getter;

@Getter
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super("Evento nao encontrado.");
    }

}
