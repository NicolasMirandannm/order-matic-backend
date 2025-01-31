package com.ordermatic.app.seguranca.aplicacao.dto.endereco;

import lombok.Builder;

@Builder
public record CondominioDto(String nome, Integer numeroCasa, String complemento) {}
