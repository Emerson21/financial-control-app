package br.com.vr.development.financialcontrolapp.application.domain.model.conta;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@ToString
@Getter
@AllArgsConstructor
public class ContaExterna implements ContaDestino {

    private Banco banco;

    private AgenciaBancaria agenciaBancaria;

    private Integer numero;

    private Integer digito;

    private String nomeCorrentista;

    private Cpf cpf;

    @Override
    public Set<Lancamento> getLancamentos() {
        return new HashSet<>();
    }

    @Override
    public TransacaoMessageDTO.ContaDestino toContaDestinoDTO() {
        TransacaoMessageDTO.Banco bancoDTO = new TransacaoMessageDTO.Banco(new TransacaoMessageDTO.NomeFantasia(nomeCorrentista), this.banco.getCodigo());

        return new TransacaoMessageDTO.ContaDestino(
                bancoDTO,
                new TransacaoMessageDTO.AgenciaBancaria(bancoDTO, agenciaBancaria.getNumero().intValue(), agenciaBancaria.getDigito().intValue()),
                this.numero.intValue(),
                this.digito,
                nomeCorrentista,
                new TransacaoMessageDTO.Cpf(cpf.getNumero(), "CPF")
        );
    }
}
