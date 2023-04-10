package com.trybe.acc.java.caixaeletronico;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes para a classe Banco")
class BancoTest {

  @Test
  @DisplayName("1 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    Banco banco = new Banco();
    String numeroConta = banco.gerarNumeroNovaConta();
    int digitos = 10;
    assertTrue(numeroConta.matches("^\\d+$"));
    assertEquals(digitos, numeroConta.length());
  }

  @Test
  @DisplayName("2 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    assertTrue(pessoa != null);
  }

  @Test
  @DisplayName("3 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
  void pessoaClienteLoginTest() {
    Banco banco = new Banco();
    banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    PessoaCliente pessoa = banco.pessoaClienteLogin("1234567890", "senhasecreta");
    assertTrue(pessoa != null);
  }

  @Test
  @DisplayName("4 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    banco.adicionarConta("Poupança", pessoa);
    banco.adicionarConta("Corrente", pessoa);
    banco.depositar(pessoa, 1, 400.0);
    banco.transferirFundos(pessoa, 1, 0, 350.0);
    assertEquals(350, pessoa.retornarSaldoContaEspecifica(0));
    assertEquals(50, pessoa.retornarSaldoContaEspecifica(1));
  }

  @Test
  @DisplayName("5 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    Banco banco = new Banco();
    PessoaCliente pessoa = banco.adicionarPessoaCliente("João", "1234567890", "senhasecreta");
    banco.adicionarConta("Poupança", pessoa);
    banco.depositar(pessoa, 0, 400.0);
    banco.sacar(pessoa, 0, 100.0);
    assertEquals(300, pessoa.retornarSaldoContaEspecifica(0));
  }

}
