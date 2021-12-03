package br.com.vr.development.financialcontrolapp.application.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente;
import br.com.vr.development.financialcontrolapp.repository.BancoRepository;
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

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public ContaCorrente abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");

        Banco banco =  bancoRepository.findByCodigo(contaCorrente.getAgencia().getBanco().getCodigo()).orElse(criaNovoBanco(contaCorrente));
        Agencia agencia = banco.getAgencias().stream().findFirst().orElse(criaNovaAgencia(contaCorrente, banco));

        EnderecoCorrentista enderecoCorrentista = getEnderecoCorrentista(contaCorrente.getCorrentista().getEndereco());

        Correntista correntista = Correntista.builder()
            .nome(contaCorrente.getCorrentista().getNome())
            .celular(contaCorrente.getCorrentista().getCelular())
            .email(contaCorrente.getCorrentista().getEmail())
            .dataNascimento(contaCorrente.getCorrentista().getDataNascimento())
            .rendaMensal(contaCorrente.getCorrentista().getRendaMensal())
            .tipoDocumento(contaCorrente.getCorrentista().getCpf().getTipoDocumento())
            .cpf(contaCorrente.getCorrentista().getCpf())
            .enderecoCorrentista(Arrays.asList(enderecoCorrentista))
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

    private Agencia criaNovaAgencia(ContaCorrente contaCorrente, Banco banco) {
        return Agencia.builder()
            .banco(banco)
            .numero(contaCorrente.getAgencia().getNumero())
            .digito(contaCorrente.getAgencia().getDigito())
            .build();
    }

    private Banco criaNovoBanco(ContaCorrente contaCorrente) {
        Banco banco = Banco.builder()
            .codigo(contaCorrente.getAgencia().getBanco().getCodigo())
            .nome(contaCorrente.getAgencia().getBanco().getNomeFantasia().getNome())
            .build();

        banco.setAgencias(Arrays.asList(criaNovaAgencia(contaCorrente, banco)));
        return banco;
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
        enderecoCorrentista.setTipoEndereco(endereco.getTipoEndereco());
        return enderecoCorrentista;
    }
    
}
