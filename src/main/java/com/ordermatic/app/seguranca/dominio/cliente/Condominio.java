package com.ordermatic.app.seguranca.dominio.cliente;

import com.ordermatic.app.seguranca.dominio.cliente.excecao.CondominioInvalidoException;
import lombok.Builder;

@Builder
public record Condominio(String nome, Integer numeroCasa, String complemento) {
    public Condominio {
        if (nome == null || nome.isBlank()) {
            throw new CondominioInvalidoException("nome");
        }
        if (numeroCasa == null) {
            throw new CondominioInvalidoException("número da casa");
        }
        if (numeroCasa <= 0) {
            throw new CondominioInvalidoException("número da casa", "não pode ser menor que um");
        }
    }
}
