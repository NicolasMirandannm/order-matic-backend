package com.ordermatic.app.seguranca.aplicacao.mapper;

import com.ordermatic.app.seguranca.aplicacao.dto.endereco.ApartamentoDto;
import com.ordermatic.app.seguranca.aplicacao.dto.endereco.CondominioDto;
import com.ordermatic.app.seguranca.aplicacao.dto.endereco.EnderecoDto;
import com.ordermatic.app.seguranca.dominio.cliente.Apartamento;
import com.ordermatic.app.seguranca.dominio.cliente.Condominio;
import com.ordermatic.app.seguranca.dominio.cliente.Endereco;

public class EnderecoMapper {

  public static EnderecoDto toDto(Endereco endereco) {
    if (endereco == null) {
      return null;
    }

    return EnderecoDto.builder()
        .id(endereco.getId())
        .rua(endereco.getRua())
        .numero(endereco.getNumbero())
        .cidade(endereco.getCidade())
        .estado(endereco.getEstado())
        .cep(endereco.getCep())
        .complemento(endereco.getComplemento())
        .principal(endereco.getPrincipal())
        .condominio(toDto(endereco.getCondominio()))
        .apartamento(toDto(endereco.getApartamento()))
        .build();
  }

  public static Endereco toDomain(EnderecoDto enderecoDto) {
    if (enderecoDto == null) {
      return null;
    }

    return Endereco.builder()
        .id(enderecoDto.getId())
        .rua(enderecoDto.getRua())
        .numbero(enderecoDto.getNumero())
        .cidade(enderecoDto.getCidade())
        .estado(enderecoDto.getEstado())
        .cep(enderecoDto.getCep())
        .complemento(enderecoDto.getComplemento())
        .principal(enderecoDto.getPrincipal())
        .condominio(toDomain(enderecoDto.getCondominio()))
        .apartamento(toDomain(enderecoDto.getApartamento()))
        .build();
  }

  public static ApartamentoDto toDto(Apartamento apartamento) {
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

  public static Apartamento toDomain(ApartamentoDto apartamentoDto) {
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

  public static CondominioDto toDto(Condominio condominio) {
    if (condominio == null) {
      return null;
    }

    return CondominioDto.builder()
        .nome(condominio.nome())
        .numeroCasa(condominio.numeroCasa())
        .complemento(condominio.complemento())
        .build();
  }

  public static Condominio toDomain(CondominioDto condominioDto) {
    if (condominioDto == null) {
      return null;
    }

    return Condominio.builder()
        .nome(condominioDto.nome())
        .numeroCasa(condominioDto.numeroCasa())
        .complemento(condominioDto.complemento())
        .build();
  }
}
