package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Random;

public class Banco {
  private ArrayList<PessoaCliente> pessoasClientes = new ArrayList<PessoaCliente>();
  private ArrayList<Conta> contas = new ArrayList<Conta>();

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

  public PessoaCliente adicionarPessoaCliente(String nome, String cpf, String senha) {
    PessoaCliente novoCliente = new PessoaCliente(nome, cpf, senha);
    pessoasClientes.add(novoCliente);
    return novoCliente;
  }

  public void adicionarConta(String tipoConta, PessoaCliente pessoaCliente) {
    Conta novaConta = new Conta(tipoConta, pessoaCliente, new Banco());
    pessoaCliente.adicionarConta(novaConta);
    contas.add(novaConta);
  }

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
