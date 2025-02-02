package com.ordermatic.app.seguranca.aplicacao.dto.endereco;

import lombok.Builder;

@Builder
public record EnderecoDto(
    String id,
    String rua,
    Integer numero,
    String cidade,
    String estado,
    String cep,
    String complemento,
    CondominioDto condominio,
    ApartamentoDto apartamento
) {
}
