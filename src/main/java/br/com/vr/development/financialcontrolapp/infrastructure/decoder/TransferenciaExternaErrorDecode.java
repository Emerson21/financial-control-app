package br.com.vr.development.financialcontrolapp.infrastructure.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;

public class TransferenciaExternaErrorDecode implements ErrorDecoder {

    private static final String ERRO_INTERNO_DESC = "Erro Interno.";
    private static final String ERRO_INTERNO_CODIGO = "-99";

    @Override
    public Exception decode(String methodKey, Response response) {
        return new Default().decode(methodKey, response);
    }
}
