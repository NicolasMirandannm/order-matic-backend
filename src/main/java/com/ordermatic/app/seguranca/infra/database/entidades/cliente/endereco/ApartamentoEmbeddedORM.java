package com.ordermatic.app.seguranca.infra.database.entidades.cliente.endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApartamentoEmbeddedORM {

    @Column(name = "apartamento_numero")
    private String numero;

    @Column(name = "apartamento_bloco")
    private String bloco;

    @Column(name = "apartamento_andar")
    private String andar;

    @Column(name = "apartamento_complemento")
    private String complemento;
}
