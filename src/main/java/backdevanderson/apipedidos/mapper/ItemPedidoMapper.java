package backdevanderson.apipedidos.mapper;

import backdevanderson.apipedidos.dto.ItemPedidoRequestDTO;
import backdevanderson.apipedidos.dto.ItemPedidoResponseDTO;
import backdevanderson.apipedidos.model.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    ItemPedido toEntity(ItemPedidoRequestDTO dto);

    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "produto.nome", target = "produtoNome")
    @Mapping(target = "subTotal", expression = "java(itemPedido.getSubTotal())")
    ItemPedidoResponseDTO toDto(ItemPedido itemPedido);

    List<ItemPedidoResponseDTO> toDtoList(List<ItemPedido> itens);

    void atualizar(ItemPedidoRequestDTO dto, @MappingTarget ItemPedido itemPedido);

}
