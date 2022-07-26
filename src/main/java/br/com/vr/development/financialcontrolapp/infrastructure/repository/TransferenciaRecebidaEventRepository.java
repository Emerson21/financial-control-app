package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaRecebidaEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TransferenciaRecebidaEventRepository extends MongoRepository<TransferenciaRecebidaEvent, String> {

    @Query("{'correlationId' : ?0 }")
    Optional<TransferenciaRecebidaEvent> findByCorrelationId(String correlationId);

}
