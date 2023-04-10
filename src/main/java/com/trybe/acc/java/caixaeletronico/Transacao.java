package com.trybe.acc.java.caixaeletronico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
  private double quantia;
  private String instante;
  private String descricao;

  /**
   * Construtor.
   * 
   * @param quantia Quantia em R$ transferida na transação.
   * @param descricao Descrição da transação (Saque, Depósito).
   */
  public Transacao(double quantia, String descricao) {
    this.quantia = quantia;
    this.descricao = descricao;
    this.instante = this.retornarInstante();
  }

  public double getQuantia() {
    return quantia;
  }

  public String retornarResumoTransacao() {
    return instante + " " + quantia + " " + descricao;
  }

  public String retornarInstante() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return LocalDateTime.now().format(formatter);
  }

}

