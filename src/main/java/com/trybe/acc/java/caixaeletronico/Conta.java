package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

public class Conta {
  private String tipoConta;
  private String idConta;
  private PessoaCliente pessoaCliente;
  private ArrayList<Transacao> transacoes = new ArrayList<Transacao>();

  public Conta(String tipoConta, PessoaCliente pessoaCliente, Banco banco) {
    this.tipoConta = tipoConta;
    this.pessoaCliente = pessoaCliente;
    this.idConta = banco.gerarNumeroNovaConta();
  }

  public void adicionarTransacao(double quantia, String descricao) {
    Transacao novaTransacao = new Transacao(quantia, descricao);
    transacoes.add(novaTransacao);
  }

  public double retornarSaldo() {
    return transacoes.stream().map(transacao -> transacao.getQuantia()).reduce(0.0,
        (acc, quantia) -> acc + quantia);
  }

  public String getIdConta() {
    return idConta;
  }

  public PessoaCliente getPessoaCliente() {
    return pessoaCliente;
  }

  public String retornarResumoConta() {
    return "Id da conta: " + this.idConta + " Saldo: " + this.retornarSaldo() + " Tipo da conta: "
        + this.tipoConta;
  }

  public void retornarExtrato() {
    for (Transacao transacao : transacoes) {
      System.out.println(transacao.retornarResumoTransacao());
    }
  }

}
