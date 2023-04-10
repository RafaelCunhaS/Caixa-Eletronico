package com.trybe.acc.java.caixaeletronico;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste dos métodos da classe PessoaCliente")
class PessoaClienteTest {

  @Test
  @DisplayName("12 - Testa o construtor da classe Pessoa Cliente.")
  void construtorTest() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    PessoaCliente pessoa = new PessoaCliente("João", "1234567890", "senhasecreta");
    assertEquals("Nova pessoa cliente João com CPF: 1234567890 criada!\n", outContent.toString());
    assertTrue(pessoa instanceof PessoaCliente);
    System.setOut(originalOut);
  }

  @Test
  @DisplayName("13 - Testa o método adicionar conta e o método retornar número de contas.")
  void adicionarContaTestRetornaNumeroDeContasTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta contaPoupanca = new Conta("Poupança", pessoa, banco);
    Conta contaCorrente = new Conta("Corrente", pessoa, banco);
    pessoa.adicionarConta(contaPoupanca);
    pessoa.adicionarConta(contaCorrente);
    assertEquals(2, pessoa.retornaNumeroDeContas());
  }

  @Test
  @DisplayName("14 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta contaPoupanca = new Conta("Poupança", pessoa, banco);
    pessoa.adicionarConta(contaPoupanca);
    contaPoupanca.adicionarTransacao(200, "Depósito recebido");
    assertEquals(200, pessoa.retornarSaldoContaEspecifica(0));
  }


  @Test
  @DisplayName("15 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta contaPoupanca = new Conta("Poupança", pessoa, banco);
    pessoa.adicionarConta(contaPoupanca);
    assertTrue(pessoa.retornarIdContaEspecifica(0).matches("^\\d+$"));
    assertEquals(10, pessoa.retornarIdContaEspecifica(0).length());
  }

  @Test
  @DisplayName("16 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta contaPoupanca = new Conta("Poupança", pessoa, banco);
    pessoa.adicionarConta(contaPoupanca);
    contaPoupanca.adicionarTransacao(150, "Depósito recebido");
    contaPoupanca.adicionarTransacao(100, "Saque efetuado");
    pessoa.retornarExtratoContaEspecifica(0);
    assertTrue(outContent.toString().contains("150"));
    assertTrue(outContent.toString().contains("Depósito recebido"));
    assertTrue(outContent.toString().contains("100"));
    assertTrue(outContent.toString().contains("Saque efetuado"));
    System.setOut(originalOut);
  }

  @Test
  @DisplayName("17 - Testa o método adiciona transacao de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta contaPoupanca = new Conta("Poupança", pessoa, banco);
    pessoa.adicionarConta(contaPoupanca);
    pessoa.adicionarTransacaoContaEspecifica(0, 200, "Depósito recebido");
    assertEquals(200, pessoa.retornarSaldoContaEspecifica(0));
  }

  @Test
  @DisplayName("18 - Testa o método validar senha.")
  void validarSenhaTest() {
    PessoaCliente pessoa = new PessoaCliente("João", "1234567890", "senhasecreta");
    assertFalse(pessoa.validarSenha("senhainvalida"));
    assertTrue(pessoa.validarSenha("senhasecreta"));
  }

  @Test
  @DisplayName("19 - Testa o método retornar resumo contas.")
  void retornarResumoContasTest() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta contaPoupanca = new Conta("Poupança", pessoa, banco);
    pessoa.adicionarConta(contaPoupanca);
    contaPoupanca.adicionarTransacao(150, "Depósito recebido");
    contaPoupanca.adicionarTransacao(100, "Saque efetuado");
    pessoa.retornarResumoContas();
    assertTrue(outContent.toString().contains("50"));
    assertTrue(outContent.toString().contains("Poupança"));
    System.setOut(originalOut);
  }

  @Test
  @DisplayName("20 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    PessoaCliente pessoa = new PessoaCliente("João", "1234567890", "senhasecreta");
    assertEquals("1234567890", pessoa.getCpf());
  }

}
