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
public class CondominioEmbeddedORM {

  @Column(name = "condominio_nome")
  private String nome;

  @Column(name = "condominio_numero_casa")
  private Integer numeroCasa;

  @Column(name = "condominio_complemento")
  private String complemento;
}
