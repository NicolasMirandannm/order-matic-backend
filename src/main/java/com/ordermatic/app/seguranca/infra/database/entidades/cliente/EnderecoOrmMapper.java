package com.ordermatic.app.seguranca.infra.database.entidades.cliente;


import com.ordermatic.app.seguranca.dominio.cliente.Apartamento;
import com.ordermatic.app.seguranca.dominio.cliente.Condominio;
import com.ordermatic.app.seguranca.dominio.cliente.Endereco;

public class EnderecoOrmMapper {

    public static Endereco toDomain(EnderecoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Endereco(
                entity.getId(),
                entity.getRua(),
                entity.getNumero(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep(),
                entity.getComplemento(),
                entity.getPrincipal(),
                entity.getApartamento() != null ? new Apartamento(
                        entity.getApartamento().getNumero(),
                        entity.getApartamento().getBloco(),
                        entity.getApartamento().getAndar(),
                        entity.getApartamento().getComplemento()
                ) : null,
                entity.getCondominio() != null ? new Condominio(
                        entity.getCondominio().getNome(),
                        entity.getCondominio().getNumeroCasa(),
                        entity.getCondominio().getComplemento()
                ) : null
        );
    }
}
