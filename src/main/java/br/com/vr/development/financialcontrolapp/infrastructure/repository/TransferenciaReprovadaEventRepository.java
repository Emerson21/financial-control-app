package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaReprovadaEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TransferenciaReprovadaEventRepository extends MongoRepository<TransferenciaReprovadaEvent, String> {

    @Query("{'correlationId' : ?0 }")
    Optional<TransferenciaReprovadaEvent> findByCorrelationId(String correlationId);

}
