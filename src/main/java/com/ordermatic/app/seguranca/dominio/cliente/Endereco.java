package com.ordermatic.app.seguranca.dominio.cliente;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String id;
    private String rua;
    private Integer numbero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
    private Boolean principal;
    private Condominio condominio;
    private Apartamento apartamento;
}
