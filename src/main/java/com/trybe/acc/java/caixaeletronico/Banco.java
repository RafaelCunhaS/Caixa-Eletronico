package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Random;

public class Banco {
  private ArrayList<PessoaCliente> pessoasClientes = new ArrayList<PessoaCliente>();
  private ArrayList<Conta> contas = new ArrayList<Conta>();

  /**
   * Gera um novo id de conta.
   * 
   * @return Uma String com o novo id.
   */
  public String gerarNumeroNovaConta() {
    Random gerador = new Random();
    String idConta = "";
    while (idConta.length() < 10 || !this.verificarIdConta(idConta)) {
      if (idConta.length() == 10) {
        idConta = "";
      }
      idConta += gerador.nextInt(10);
    }
    return idConta;
  }

  private boolean verificarIdConta(String idConta) {
    return contas.stream().map(conta -> conta.getIdConta()).noneMatch(conta -> conta == idConta);
  }

  /**
   * Adiciona uma nova pessoa cliente.
   * 
   * @param nome Nome da pessoa.
   * @param cpf Cpf da pessoa.
   * @param senha Senha da pessoa.
   * @return Retorna um objeto PessoaCliente.
   */
  public PessoaCliente adicionarPessoaCliente(String nome, String cpf, String senha) {
    PessoaCliente novoCliente = new PessoaCliente(nome, cpf, senha);
    pessoasClientes.add(novoCliente);
    return novoCliente;
  }

  /**
   * Adiciona uma conta nova ao sistema.
   * 
   * @param tipoConta Tipo da conta a ser criada (Poupança ou Corrente).
   * @param pessoaCliente Pessoa cliente que irá abrir a conta.
   */
  public void adicionarConta(String tipoConta, PessoaCliente pessoaCliente) {
    Conta novaConta = new Conta(tipoConta, pessoaCliente, new Banco());
    pessoaCliente.adicionarConta(novaConta);
    contas.add(novaConta);
  }

  /**
   * Login da pessoa no sistema.
   * 
   * @param cpf Cpf da pessoa.
   * @param senha Senha da pessoa.
   * @return Retorna um objeto PessoaCliente.
   */
  public PessoaCliente pessoaClienteLogin(String cpf, String senha) {
    PessoaCliente pessoaCliente = pessoasClientes.stream()
        .filter(cliente -> cliente.getCpf().equals(cpf)).findFirst().orElse(null);

    if (pessoaCliente != null && pessoaCliente.validarSenha(senha)) {
      return pessoaCliente;
    } else {
      return null;
    }
  }

  public void transferirFundos(PessoaCliente pessoaCliente, int daConta, int paraConta,
      double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(daConta, -quantia, "Transferência enviada");
    pessoaCliente.adicionarTransacaoContaEspecifica(paraConta, quantia, "Transferência recebida");
  }

  public void sacar(PessoaCliente pessoaCliente, int daConta, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(daConta, -quantia, "Saque efetuado");
  }

  public void depositar(PessoaCliente pessoaCliente, int paraConta, double quantia) {
    pessoaCliente.adicionarTransacaoContaEspecifica(paraConta, quantia, "Depósito recebido");
  }

  public void mostrarExtrato(PessoaCliente pessoaCliente, int conta) {
    pessoaCliente.retornarExtratoContaEspecifica(conta);
  }

}
