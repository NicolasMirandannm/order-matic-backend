package com.ordermatic.app.seguranca.aplicacao.dto.endereco;

import lombok.Builder;

@Builder
public record ApartamentoDto(String numero, String bloco, String andar, String complemento) {}
