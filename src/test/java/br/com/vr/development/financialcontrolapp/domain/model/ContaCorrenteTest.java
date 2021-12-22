package br.com.vr.development.financialcontrolapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Celular;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cnpj;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.Email;
import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Nome;
import br.com.vr.development.financialcontrolapp.application.domain.model.NomeFantasia;
import br.com.vr.development.financialcontrolapp.application.domain.model.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;

@SpringBootTest
public class ContaCorrenteTest {
    
    @Autowired
    private DepositoInicialFactory depositoInicialFactory;

    @Test
    public void deveConstruirContaCorrente() {
        Banco banco = Banco.builder().cnpj(new Cnpj("73811174000108")).codigo("1").nomeFantasia(new NomeFantasia("Banco VR")).build();
        AgenciaBancaria agenciaBancaria = AgenciaBancaria.builder().digito(1).numero(1).banco(banco).build();

        banco.setAgencias(Arrays.asList(agenciaBancaria));

        ContaCorrente contaCorrente = new ContaCorrente(agenciaBancaria, getCorrentista(), depositoInicialFactory.create(new BigDecimal("50")));
        Assertions.assertNotNull(contaCorrente);
    }


    @Test
    public void naoDeveConstruirContaCorrenteComDepositoInicialMenorQue50() {
        Banco banco = Banco.builder().cnpj(new Cnpj("73811174000108")).codigo("1").nomeFantasia(new NomeFantasia("Banco VR")).build();
        AgenciaBancaria agenciaBancaria = AgenciaBancaria.builder().digito(1).numero(1).banco(banco).build();

        banco.setAgencias(Arrays.asList(agenciaBancaria));

        DepositoInicialException valorMinimoInvalido = Assertions.assertThrows(DepositoInicialException.class, () -> {
            new ContaCorrente(agenciaBancaria, getCorrentista(), depositoInicialFactory.create(new BigDecimal("49.9")));    ;
        }, "Valor inicial depositado menor que o permitido.");

        
        Assertions.assertEquals("Valor inicial depositado menor que o permitido.", valorMinimoInvalido.getMessage());
    }

    private Correntista getCorrentista() {
        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        RendaMensal renda = new RendaMensal(new BigDecimal("2000"));

        return Correntista.builder()
            .nome(new Nome("Emerson", "Haraguchi"))
            .email(email)
            .enderecos(this.getEnderecos())
            .cpf(new Cpf("29222004000"))
            .tipoDocumento(TipoDocumento.CPF)
            .dataNascimento(new DataNascimento(LocalDate.of(1988, 10, 21)))
            .celular(telefone)
            .rendaMensal(renda)
        .build();
    }


    private List<Endereco> getEnderecos() {
        Endereco endereco = new Endereco();
        endereco.setCep("13940-970");
        endereco.setBairro("Centro");
        endereco.setMunicipio("Águas de Lindóia");
        endereco.setEstado(UF.SAO_PAULO);
        endereco.setLogradouro("Avenida Brasil 160");
        endereco.setNumero("607");
        endereco.setTipoEndereco(TipoEndereco.RESIDENCIAL);
        return Arrays.asList(endereco);
    }

}
