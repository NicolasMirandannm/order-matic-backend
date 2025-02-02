package com.ordermatic.app.seguranca.infra.database.entidades.cliente.endereco;


import com.ordermatic.app.seguranca.dominio.cliente.Apartamento;
import com.ordermatic.app.seguranca.dominio.cliente.Condominio;
import com.ordermatic.app.seguranca.dominio.cliente.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoOrmMapper {

  public Endereco toDomain(EnderecoORM entity) {
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
        .condominio(toCondominioDomain(entity.getCondominio()))
        .apartamento(toApartamentoDomain(entity.getApartamento()))
        .build();
  }

  public EnderecoORM toORM(Endereco domain) {
    if (domain == null) {
      return null;
    }

    return EnderecoORM.builder()
        .id(domain.getId())
        .rua(domain.getRua())
        .numero(domain.getNumero())
        .cidade(domain.getCidade())
        .estado(domain.getEstado())
        .cep(domain.getCep())
        .complemento(domain.getComplemento())
        .condominio(toCondominioValObj(domain.getCondominio()))
        .apartamento(toApartamentoValObj(domain.getApartamento()))
        .build();
  }

  private Condominio toCondominioDomain(CondominioEmbeddedORM condominioEntity) {
    if (condominioEntity == null) {
      return null;
    }

    return Condominio.builder()
        .nome(condominioEntity.getNome())
        .numeroCasa(condominioEntity.getNumeroCasa())
        .complemento(condominioEntity.getComplemento())
        .build();
  }

  private CondominioEmbeddedORM toCondominioValObj(Condominio condominioDomain) {
    if (condominioDomain == null) {
      return null;
    }

    return CondominioEmbeddedORM.builder()
        .nome(condominioDomain.nome())
        .numeroCasa(condominioDomain.numeroCasa())
        .complemento(condominioDomain.complemento())
        .build();
  }

  private Apartamento toApartamentoDomain(ApartamentoEmbeddedORM apartamentoEntity) {
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

  private ApartamentoEmbeddedORM toApartamentoValObj(Apartamento apartamentoDomain) {
    if (apartamentoDomain == null) {
      return null;
    }

    return ApartamentoEmbeddedORM.builder()
        .numero(apartamentoDomain.numero())
        .bloco(apartamentoDomain.bloco())
        .andar(apartamentoDomain.andar())
        .complemento(apartamentoDomain.complemento())
        .build();
  }
}
