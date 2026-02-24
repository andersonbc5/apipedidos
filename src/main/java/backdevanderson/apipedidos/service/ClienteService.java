package backdevanderson.apipedidos.service;

import backdevanderson.apipedidos.dto.ClienteRequestDTO;
import backdevanderson.apipedidos.dto.ClienteResponseDTO;
import backdevanderson.apipedidos.mapper.ClienteMapper;
import backdevanderson.apipedidos.model.Cliente;
import backdevanderson.apipedidos.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    //CADASTRAR
    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto){
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    //LISTAR
    public List<ClienteResponseDTO> listarClientes(){
        return mapper.toDtoList(repository.findAll());
    }

    //BUSCAR POR ID
    public ClienteResponseDTO buscarClientePorId (UUID id){
        return mapper.toDto(repository.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException("Cliente com id: " + id + "não encontrado")));
    }

    //ATUALIZAR CADASTRO
    public ClienteResponseDTO atualizar(UUID id, ClienteRequestDTO dto){
        Cliente cliente = repository.findById(id)
                .orElseThrow(()->
                        new EntityNotFoundException("Cliente com id: " + id + "não encontrado"));

        mapper.atualizar(dto,cliente);
        Cliente clienteAtualizado = repository.save(cliente);

        return mapper.toDto(clienteAtualizado);

    }

    //DELETAR
    public void deletarCliente(UUID id){
        repository.deleteById(id);
    }
}
