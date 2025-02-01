package com.ordermatic.app.seguranca.infra.database.entidades.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String rua;

    private Integer numero;

    private String cidade;

    private String estado;

    private String cep;

    private String complemento;

    private Boolean principal;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Apartamento {
        private String numero;
        private String bloco;
        private String andar;
        private String complemento;
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Condominio {
        private String nome;
        private Integer numeroCasa;
        private String complemento;
    }

    @Embedded
    private Apartamento apartamento;

    @Embedded
    private Condominio condominio;
}
