package com.ordermatic.app.seguranca.dominio.cliente;

import com.ordermatic.app.seguranca.dominio.cliente.excecao.ApartamentoInvalidoExcecao;
import lombok.*;

import static java.util.Objects.isNull;

@Builder
public record Apartamento(String numero, String bloco, String andar, String complemento) {
  public Apartamento {
    if (isNull(numero) || numero.isBlank()) {
      throw new ApartamentoInvalidoExcecao("número");
    }
    if (andar == null || andar.isBlank()) {
      throw new ApartamentoInvalidoExcecao("andar");
    }
  }
}
