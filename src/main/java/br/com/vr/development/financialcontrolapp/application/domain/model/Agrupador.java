package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.Collection;
import java.util.Map;

public interface Agrupador extends Iterable<Map.Entry<Object, Collection>> {

    String getKeyNameField();

}
