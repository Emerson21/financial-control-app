package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.inbound.eventhandlers.TransacaoInternaEventHandler;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransacaoInternaEventRepository extends ElasticsearchRepository<TransacaoInternaEventHandler, String> {
}
