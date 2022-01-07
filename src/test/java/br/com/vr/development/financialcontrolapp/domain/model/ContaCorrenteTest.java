package br.com.vr.development.financialcontrolapp.domain.model;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TED;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Celular;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cnpj;
import br.com.vr.development.financialcontrolapp.application.domain.model.CodigoBanco;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.DadosBancarios;
import br.com.vr.development.financialcontrolapp.application.domain.model.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.Email;
import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Nome;
import br.com.vr.development.financialcontrolapp.application.domain.model.NomeFantasia;
import br.com.vr.development.financialcontrolapp.application.domain.model.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public class ContaCorrenteTest {

    private static final BigDecimal CINQUENTA = new BigDecimal("50");

    private DepositoInicialFactory depositoInicialFactory;

    @BeforeEach
    protected void setUp() {
        this.depositoInicialFactory = new DepositoInicialFactory(new BigDecimal(50));
    }

    @Test
    public void deveConstruirContaCorrente() {
        Banco banco = getBanco();
        AgenciaBancaria agenciaBancaria = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agenciaBancaria));

        ContaCorrente contaCorrente = new ContaCorrente(agenciaBancaria, getCorrentista(), depositoInicialFactory.create(CINQUENTA));
        Assertions.assertNotNull(contaCorrente);
    }

    @Test
    public void naoDeveConstruirContaCorrenteComDepositoInicialMenorQue50() {
        Banco banco = getBanco();
        AgenciaBancaria agenciaBancaria = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agenciaBancaria));

        DepositoInicialException valorMinimoInvalido = Assertions.assertThrows(DepositoInicialException.class, () -> {
            new ContaCorrente(agenciaBancaria, getCorrentista(), depositoInicialFactory.create(new BigDecimal("49.9")));    ;
        }, "Valor inicial depositado menor que o permitido.");

        
        Assertions.assertEquals("Valor inicial depositado menor que o permitido.", valorMinimoInvalido.getMessage());
    }

    @Test
    protected void deveRealizarUmaMovimentacaoDeValoresEntreContasCorrentes() {

        ContaCorrente contaCorrente = criaContaCorrente(CINQUENTA);
        ContaCorrente contaDestino = criaContaCorrente(CINQUENTA);
        Valor valor = new Valor(CINQUENTA);

        contaCorrente.transferir(valor, contaDestino);

        Assertions.assertEquals(new BigDecimal("0"), contaCorrente.getSaldo() );
        Assertions.assertEquals(new BigDecimal("100"), contaDestino.getSaldo());
    }

    @Test
    protected void naoDeveTransferirSeOSaldoForMenorQueOValorSolicitado() {
        ContaCorrente contaCorrente = criaContaCorrente(CINQUENTA);
        ContaCorrente contaDestino = criaContaCorrente(CINQUENTA);
        Valor valor = new Valor(new BigDecimal("50.01"));

        Assertions.assertThrows(SaldoInsuficienteException.class, () -> {
            contaCorrente.transferir(valor, contaDestino);
        }, "Saldo indisponivel para realizar a operacao.");
    }

    @Test
    protected void deveFazerUmaTransferenciaExternaParaOutroBancoDestino() {
        ContaCorrente contaCorrente = criaContaCorrente(CINQUENTA);

        AgenciaBancaria agencia = new AgenciaBancaria(123, 1);
        DadosBancarios dadosBancarios = 
            new DadosBancarios(new CodigoBanco(341), agencia, new ContaDestino(123234345, 2), new Nome("nomeDestinatario"), new Cpf("cpfDestinatario"));

        Valor valor = new Valor(CINQUENTA.subtract(TED.taxa()));
        Transferencia transferencia = contaCorrente.tranfere(valor, dadosBancarios, TED);

        org.assertj.core.api.Assertions.assertThat(transferencia).isNotNull();
        org.assertj.core.api.Assertions.assertThat(contaCorrente.getSaldo()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    protected void deveLancarSaldoInsuficienteExceptionQuandoOValorMaisATaxaForemMaiorQueOSaldoDisponivel() {
        ContaCorrente contaCorrente = criaContaCorrente(CINQUENTA);
        
        AgenciaBancaria agencia = new AgenciaBancaria(123, 1);
        DadosBancarios dadosBancarios = 
            new DadosBancarios(new CodigoBanco(341), agencia, new ContaDestino(123234345, 2), new Nome("nomeDestinatario"), new Cpf("cpfDestinatario"));

        Valor valor = new Valor(CINQUENTA);

        org.assertj.core.api.Assertions.assertThatExceptionOfType(SaldoInsuficienteException.class)
            .isThrownBy(() -> {contaCorrente.tranfere(valor, dadosBancarios, TED);})
            .withMessage("Saldo indisponivel para realizar a operacao.");
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

    private AgenciaBancaria getAgenciaBancaria(Banco banco) {
        return AgenciaBancaria.builder().digito(1).numero(1).banco(banco).build();
    }

    private Banco getBanco() {
        return Banco.builder().cnpj(new Cnpj("73811174000108")).codigo("1").nomeFantasia(new NomeFantasia("Banco VR")).build();
    }

    private ContaCorrente criaContaCorrente(BigDecimal valorInicial) {
        return new ContaCorrente(getAgenciaBancaria(getBanco()), getCorrentista(), depositoInicialFactory.create(valorInicial));
    }


}
