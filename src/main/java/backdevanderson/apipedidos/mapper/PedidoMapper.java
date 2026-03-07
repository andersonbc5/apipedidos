package backdevanderson.apipedidos.mapper;

import backdevanderson.apipedidos.dto.PedidoResponseDTO;
import backdevanderson.apipedidos.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ItemPedidoMapper.class)
public interface PedidoMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.nome", target = "clienteNome")
    PedidoResponseDTO toDto(Pedido pedido);

    List<PedidoResponseDTO> toDtoList(List<Pedido> pedidos);
}
