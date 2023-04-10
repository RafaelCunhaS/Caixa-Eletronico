package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

public class PessoaCliente {
  private String nomeCompleto;
  private String cpf;
  private String senha;
  private ArrayList<Conta> contas = new ArrayList<Conta>();

  /**
   * Construtor.
   * 
   * @param nome Nome da pessoa.
   * @param cpf Cpf da pessoa.
   * @param senha Senha da pessoa.
   */
  public PessoaCliente(String nome, String cpf, String senha) {
    this.nomeCompleto = nome;
    this.cpf = cpf;
    this.senha = senha;
    System.out
        .println("Nova pessoa cliente " + this.nomeCompleto + " com CPF: " + this.cpf + " criada!");
  }

  public void adicionarConta(Conta conta) {
    contas.add(conta);
  }

  public int retornaNumeroDeContas() {
    return contas.size();
  }

  public double retornarSaldoContaEspecifica(int indice) {
    return contas.get(indice).retornarSaldo();
  }

  public String retornarIdContaEspecifica(int indice) {
    return contas.get(indice).getIdConta();
  }

  public void retornarExtratoContaEspecifica(int indice) {
    contas.get(indice).retornarExtrato();
  }

  public void adicionarTransacaoContaEspecifica(int indice, double quantia, String descricao) {
    contas.get(indice).adicionarTransacao(quantia, descricao);
  }

  /**
   * Validador de senhas.
   * 
   * @param senha Senha a ser validada.
   * @return Retorna um 'true' se a senha for a igual a senha cadastrada da pessoa, do contrário
   *         retorna um 'false'.
   */
  public boolean validarSenha(String senha) {
    if (this.senha.equals(senha)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Imprime resumo de todas as contas da pessoa.
   */
  public void retornarResumoContas() {
    for (Conta conta : contas) {
      System.out.println(conta.retornarResumoConta());
    }
  }

  public String getCpf() {
    return cpf;
  }

}
