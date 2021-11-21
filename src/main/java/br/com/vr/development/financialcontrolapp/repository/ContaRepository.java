package br.com.vr.development.financialcontrolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vr.development.financialcontrolapp.repository.entiies.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    
}
