package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

public class PessoaCliente {
  private String nomeCompleto;
  private String cpf;
  private String senha;
  private ArrayList<Conta> contas = new ArrayList<Conta>();

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

  public boolean validarSenha(String senha) {
    if (this.senha.equals(senha)) {
      return true;
    } else {
      return false;
    }
  }

  public void retornarResumoContas() {
    for (Conta conta : contas) {
      System.out.println(conta.retornarResumoConta());
    }
  }

  public String getCpf() {
    return cpf;
  }

}
