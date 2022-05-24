package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;

@Repository
public interface AgenciaRepository extends JpaRepository<AgenciaBancaria, Long> {
    
}
