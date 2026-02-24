package backdevanderson.apipedidos.mapper;


import backdevanderson.apipedidos.dto.ClienteRequestDTO;
import backdevanderson.apipedidos.dto.ClienteResponseDTO;
import backdevanderson.apipedidos.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteRequestDTO dto);

    ClienteResponseDTO toDto(Cliente cliente);

    List<ClienteResponseDTO> toDtoList(List<Cliente> clientes);

    void atualizar(ClienteRequestDTO dto, @MappingTarget Cliente cliente);
}
