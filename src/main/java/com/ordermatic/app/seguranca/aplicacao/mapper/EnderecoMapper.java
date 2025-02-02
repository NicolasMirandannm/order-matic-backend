package com.ordermatic.app.seguranca.aplicacao.mapper;

import com.ordermatic.app.seguranca.aplicacao.dto.endereco.ApartamentoDto;
import com.ordermatic.app.seguranca.aplicacao.dto.endereco.CondominioDto;
import com.ordermatic.app.seguranca.aplicacao.dto.endereco.EnderecoDto;
import com.ordermatic.app.seguranca.dominio.cliente.Apartamento;
import com.ordermatic.app.seguranca.dominio.cliente.Condominio;
import com.ordermatic.app.seguranca.dominio.cliente.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

  public EnderecoDto toDto(Endereco endereco) {
    if (endereco == null) {
      return null;
    }

    return EnderecoDto.builder()
        .id(endereco.getId())
        .rua(endereco.getRua())
        .numero(endereco.getNumero())
        .cidade(endereco.getCidade())
        .estado(endereco.getEstado())
        .cep(endereco.getCep())
        .complemento(endereco.getComplemento())
        .condominio(toCondominioValObj(endereco.getCondominio()))
        .apartamento(toApartamentoValObj(endereco.getApartamento()))
        .build();
  }

  public Endereco toDomain(EnderecoDto enderecoDto) {
    if (enderecoDto == null) {
      return null;
    }

    return Endereco.builder()
        .id(enderecoDto.getId())
        .rua(enderecoDto.getRua())
        .numero(enderecoDto.getNumero())
        .cidade(enderecoDto.getCidade())
        .estado(enderecoDto.getEstado())
        .cep(enderecoDto.getCep())
        .complemento(enderecoDto.getComplemento())
        .condominio(toCondominioDomain(enderecoDto.getCondominio()))
        .apartamento(toApartamentoDomain(enderecoDto.getApartamento()))
        .build();
  }

  private CondominioDto toCondominioValObj(Condominio condominio) {
    if (condominio == null) {
      return null;
    }

    return CondominioDto.builder()
        .nome(condominio.nome())
        .numeroCasa(condominio.numeroCasa())
        .complemento(condominio.complemento())
        .build();
  }

  private Condominio toCondominioDomain(CondominioDto condominioDto) {
    if (condominioDto == null) {
      return null;
    }

    return Condominio.builder()
        .nome(condominioDto.nome())
        .numeroCasa(condominioDto.numeroCasa())
        .complemento(condominioDto.complemento())
        .build();
  }

  private ApartamentoDto toApartamentoValObj(Apartamento apartamento) {
    if (apartamento == null) {
      return null;
    }

    return ApartamentoDto.builder()
        .numero(apartamento.numero())
        .bloco(apartamento.bloco())
        .andar(apartamento.andar())
        .complemento(apartamento.complemento())
        .build();
  }

  private Apartamento toApartamentoDomain(ApartamentoDto apartamentoDto) {
    if (apartamentoDto == null) {
      return null;
    }

    return Apartamento.builder()
        .numero(apartamentoDto.numero())
        .bloco(apartamentoDto.bloco())
        .andar(apartamentoDto.andar())
        .complemento(apartamentoDto.complemento())
        .build();
  }
}
