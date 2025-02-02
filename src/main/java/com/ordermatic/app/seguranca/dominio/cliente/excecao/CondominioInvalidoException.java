package com.ordermatic.app.seguranca.dominio.cliente.excecao;

public class CondominioInvalidoException extends IllegalArgumentException {
  public CondominioInvalidoException(String atributo) {
    super("O " + atributo + " não pode ser vazio.");
  }

  public CondominioInvalidoException(String atributo, String mensagem) {
    super("O " + atributo + " do condomínio " + mensagem + ".");
  }
}
