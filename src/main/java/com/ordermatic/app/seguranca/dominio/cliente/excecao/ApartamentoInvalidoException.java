package com.ordermatic.app.seguranca.dominio.cliente.excecao;

public class ApartamentoInvalidoException extends IllegalArgumentException {
  public ApartamentoInvalidoException(String atributo) {
    super("O " +atributo+ " do apartamento não pode ser vazio");
  }
}
