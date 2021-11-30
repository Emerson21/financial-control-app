package br.com.vr.development.financialcontrolapp.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import br.com.vr.development.financialcontrolapp.repository.entities.Agencia;
import br.com.vr.development.financialcontrolapp.repository.entities.Banco;
import br.com.vr.development.financialcontrolapp.repository.entities.Correntista;
import br.com.vr.development.financialcontrolapp.repository.entities.EnderecoCorrentista;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public ContaCorrente abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");

        Banco banco = Banco.builder()
            .codigo(contaCorrente.getAgencia().getBanco().getCodigo())
            .nome(contaCorrente.getAgencia().getBanco().getNome().getNomeCompleto())
            .build();

        Agencia agencia = Agencia.builder()
            .banco(banco)
            .numero(contaCorrente.getAgencia().getNumero())
            .digito(contaCorrente.getAgencia().getDigito())
            .build();

        EnderecoCorrentista enderecoCorrentista = getEnderecoCorrentista(contaCorrente.getCorrentista().getEndereco());

        Correntista correntista = Correntista.builder()
            .nomeCompleto(contaCorrente.getCorrentista().getNomeCompleto())
            .celular(contaCorrente.getCorrentista().getCelular().toString())
            .email(contaCorrente.getCorrentista().getEmail().getEmail())
            .dataDeNascimento(contaCorrente.getCorrentista().getDataDeNascimento())
            .rendaMensal(contaCorrente.getCorrentista().getRendaMensal().getValor())
            .tipoDocumento(contaCorrente.getCorrentista().getDocumento().getTipoDocumento())
            .numeroDocumento(contaCorrente.getCorrentista().getDocumento().getNumero())
            .enderecoCorrentista(enderecoCorrentista)
            .build();

        br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente entity = 
            br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente.builder()
                .agencia(agencia)
                .numero(contaCorrente.getNumero())
                .digito(contaCorrente.getDigito())
                .correntista(correntista)
                .build();

        contaRepository.save(entity);
 
        return contaCorrente;
    }

    private EnderecoCorrentista getEnderecoCorrentista(Endereco endereco) {
        EnderecoCorrentista enderecoCorrentista = new EnderecoCorrentista();
        enderecoCorrentista.setBairro(endereco.getBairro());
        enderecoCorrentista.setCep(endereco.getCep());
        enderecoCorrentista.setComplemento(endereco.getComplemento());
        enderecoCorrentista.setEstado(endereco.getEstado());
        enderecoCorrentista.setLogradouro(endereco.getLogradouro());
        enderecoCorrentista.setMunicipio(endereco.getMunicipio());
        enderecoCorrentista.setNumero(endereco.getNumero());
        return enderecoCorrentista;
    }
    
}
