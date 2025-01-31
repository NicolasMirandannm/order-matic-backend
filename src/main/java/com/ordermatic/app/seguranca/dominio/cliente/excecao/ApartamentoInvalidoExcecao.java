package com.ordermatic.app.seguranca.dominio.cliente.excecao;

public class ApartamentoInvalidoExcecao extends IllegalArgumentException {
  public ApartamentoInvalidoExcecao(String atributo) {
    super("O " +atributo+ " do apartamento não pode ser vazio");
  }
}
