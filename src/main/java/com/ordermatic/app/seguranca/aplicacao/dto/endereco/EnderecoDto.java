package com.ordermatic.app.seguranca.aplicacao.dto.endereco;

import com.ordermatic.app.seguranca.dominio.cliente.Endereco;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {
  private String id;
  private String rua;
  private Integer numero;
  private String cidade;
  private String estado;
  private String cep;
  private String complemento;
  private Boolean principal;
  private CondominioDto condominio;
  private ApartamentoDto apartamento;
}
