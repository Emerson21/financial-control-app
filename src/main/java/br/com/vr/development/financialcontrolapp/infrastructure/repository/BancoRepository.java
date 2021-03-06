package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
    
    @Query("select b from Banco b where b.codigo = :codigo")
    public Optional<Banco> findByCodigo(@Param("codigo") String codigo);

}
