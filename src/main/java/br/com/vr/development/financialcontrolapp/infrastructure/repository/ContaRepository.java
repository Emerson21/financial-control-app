package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;

import java.util.Optional;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("select cc from ContaCorrente cc where cc.correntista.cpf = :cpf")
    Optional<ContaCorrente> findBy(@Param("cpf") Cpf cpf);

}
