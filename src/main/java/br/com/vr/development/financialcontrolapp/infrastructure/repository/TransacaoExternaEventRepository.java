package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.inbound.eventhandlers.TransacaoExternaEventHandler;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransacaoExternaEventRepository extends ElasticsearchRepository<TransacaoExternaEventHandler, String> {
}
