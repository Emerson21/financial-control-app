package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TransferenciaEventRepository extends MongoRepository<TransferenciaEvent, String> {

    @Query("{'correlationId' : ?0 }")
    Optional<TransferenciaEvent> findByCorrelationId(String correlationId);

}
