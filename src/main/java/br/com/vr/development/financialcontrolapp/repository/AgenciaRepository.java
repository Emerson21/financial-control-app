package br.com.vr.development.financialcontrolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vr.development.financialcontrolapp.repository.entities.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    
}
