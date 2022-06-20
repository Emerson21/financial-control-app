package br.com.vr.development.financialcontrolapp.application.domain.model.elasticsearch;

import org.springframework.data.annotation.Id;

public abstract class ElasticSearchModel {

    @Id
    protected String id;

}
