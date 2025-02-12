package com.ordermatic.app.seguranca.infra.database.entidades.cliente.endereco;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoORM {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "rua")
  private String rua;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "estado")
  private String estado;

  @Column(name = "cep")
  private String cep;

  @Column(name = "complemento")
  private String complemento;

  @Embedded
  private ApartamentoEmbeddedORM apartamento;

  @Embedded
  private CondominioEmbeddedORM condominio;
}
