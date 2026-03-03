package backdevanderson.apipedidos.mapper;


import backdevanderson.apipedidos.dto.ClienteRequestDTO;
import backdevanderson.apipedidos.dto.ProdutoRequestDTO;
import backdevanderson.apipedidos.dto.ProdutoResponseDTO;
import backdevanderson.apipedidos.model.Produto;
import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoRequestDTO dto);

    ProdutoResponseDTO toDto(Produto produto);

    List<ProdutoResponseDTO> toDtoList (List<Produto> produtos);

    void atualizar(ProdutoRequestDTO dto, @MappingTarget Produto produto);
}
