package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste da classe Conta")
class ContaTest {

  @Test
  @DisplayName("6 - Testa o construtor da classe conta.")
  void construtorTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta novaConta = new Conta("Poupança", pessoa, banco);
    assertTrue(novaConta instanceof Conta);
  }

  @Test
  @DisplayName("7 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta novaConta = new Conta("Poupança", pessoa, banco);
    novaConta.adicionarTransacao(150, "Depósito recebido");
    assertEquals(150, novaConta.retornarSaldo());
  }

  @Test
  @DisplayName("8 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta novaConta = new Conta("Poupança", pessoa, banco);
    novaConta.adicionarTransacao(150, "Depósito recebido");
    assertTrue(novaConta.retornarResumoConta().contains("150"));
    assertTrue(novaConta.retornarResumoConta().contains("Poupança"));
  }

  @Test
  @DisplayName("9 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta novaConta = new Conta("Poupança", pessoa, banco);
    novaConta.adicionarTransacao(150, "Depósito recebido");
    novaConta.adicionarTransacao(100, "Saque efetuado");
    novaConta.retornarExtrato();
    assertTrue(outContent.toString().contains("150"));
    assertTrue(outContent.toString().contains("Depósito recebido"));
    assertTrue(outContent.toString().contains("100"));
    assertTrue(outContent.toString().contains("Saque efetuado"));
    System.setOut(originalOut);
  }

  @Test
  @DisplayName("10 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta novaConta = new Conta("Poupança", pessoa, banco);
    assertTrue(novaConta.getIdConta().matches("^\\d+$"));
    assertEquals(10, novaConta.getIdConta().length());
  }

  @Test
  @DisplayName("11 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    Conta novaConta = new Conta("Poupança", pessoa, banco);
    assertEquals(pessoa, novaConta.getPessoaCliente());
  }

}
