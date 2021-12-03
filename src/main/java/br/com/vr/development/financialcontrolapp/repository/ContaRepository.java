package br.com.vr.development.financialcontrolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente;


@Repository
public interface ContaRepository extends JpaRepository<ContaCorrente, Long> {
    
}
