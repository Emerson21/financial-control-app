package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransferenciaSolicitadaEventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TransferenciaSolicitadaEventRepository extends MongoRepository<TransferenciaSolicitadaEventModel, String> {

    @Query("{'correlationId' : ?0 }")
    Optional<TransferenciaSolicitadaEventModel> findByCorrelationId(String correlationId);

}
