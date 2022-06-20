package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransferenciaEventRepository extends MongoRepository<TransferenciaEvent, String> { }
