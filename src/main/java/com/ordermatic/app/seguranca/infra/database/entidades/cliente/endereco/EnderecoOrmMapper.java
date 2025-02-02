package com.ordermatic.app.seguranca.infra.database.entidades.cliente.endereco;


import com.ordermatic.app.seguranca.dominio.cliente.Apartamento;
import com.ordermatic.app.seguranca.dominio.cliente.Condominio;
import com.ordermatic.app.seguranca.dominio.cliente.Endereco;

public class EnderecoOrmMapper {

  public static Endereco toDomain(EnderecoEntity entity) {
    if (entity == null) {
      return null;
    }

    return Endereco.builder()
        .id(entity.getId())
        .rua(entity.getRua())
        .numero(entity.getNumero())
        .cidade(entity.getCidade())
        .estado(entity.getEstado())
        .cep(entity.getCep())
        .complemento(entity.getComplemento())
        .principal(entity.getPrincipal())
        .condominio(toDomain(entity.getCondominio()))
        .apartamento(toDomain(entity.getApartamento()))
        .build();
  }

  public static EnderecoEntity toEntity(Endereco domain) {
    if (domain == null) {
      return null;
    }

    return EnderecoEntity.builder()
        .id(domain.getId())
        .rua(domain.getRua())
        .numero(domain.getNumero())
        .cidade(domain.getCidade())
        .estado(domain.getEstado())
        .cep(domain.getCep())
        .complemento(domain.getComplemento())
        .principal(domain.getPrincipal())
        .condominio(toEntity(domain.getCondominio()))
        .apartamento(toEntity(domain.getApartamento()))
        .build();
  }

  public static Condominio toDomain(CondominioEmbeddedEntity condominioEntity) {
    if (condominioEntity == null) {
      return null;
    }

    return Condominio.builder()
        .nome(condominioEntity.getNome())
        .numeroCasa(condominioEntity.getNumeroCasa())
        .complemento(condominioEntity.getComplemento())
        .build();
  }

  public static CondominioEmbeddedEntity toEntity(Condominio condominioDomain) {
    if (condominioDomain == null) {
      return null;
    }

    return CondominioEmbeddedEntity.builder()
        .nome(condominioDomain.nome())
        .numeroCasa(condominioDomain.numeroCasa())
        .complemento(condominioDomain.complemento())
        .build();
  }

  public static Apartamento toDomain(ApartamentoEmbeddedEntity apartamentoEntity) {
    if (apartamentoEntity == null) {
      return null;
    }

    return Apartamento.builder()
        .numero(apartamentoEntity.getNumero())
        .bloco(apartamentoEntity.getBloco())
        .andar(apartamentoEntity.getAndar())
        .complemento(apartamentoEntity.getComplemento())
        .build();
  }

  public static ApartamentoEmbeddedEntity toEntity(Apartamento apartamentoDomain) {
    if (apartamentoDomain == null) {
      return null;
    }

    return ApartamentoEmbeddedEntity.builder()
        .numero(apartamentoDomain.numero())
        .bloco(apartamentoDomain.bloco())
        .andar(apartamentoDomain.andar())
        .complemento(apartamentoDomain.complemento())
        .build();
  }
}
