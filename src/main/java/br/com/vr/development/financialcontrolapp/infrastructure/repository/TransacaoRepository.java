package br.com.vr.development.financialcontrolapp.infrastructure.repository;

import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("select t from Transacao t where t.cpf = :cpf and t.dataHora = :dataHora")
    Optional<Transacao> findByCpfAndDataHora(@Param("cpf") String cpf, @Param("dataHora") LocalDateTime dataHora);

}
