package backdevanderson.apipedidos.service;

import backdevanderson.apipedidos.dto.ClienteRequestDTO;
import backdevanderson.apipedidos.dto.ClienteResponseDTO;
import backdevanderson.apipedidos.exceptions.RecursoNaoEncontradoException;
import backdevanderson.apipedidos.exceptions.RegistroDuplicadoException;
import backdevanderson.apipedidos.exceptions.RegraDeNegocioException;
import backdevanderson.apipedidos.mapper.ClienteMapper;
import backdevanderson.apipedidos.model.Cliente;
import backdevanderson.apipedidos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    //CADASTRAR
    @Transactional
    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new RegistroDuplicadoException("Email já cadastrado");
        }
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    //LISTAR
    public List<ClienteResponseDTO> listarClientes() {
        return mapper.toDtoList(repository.findAll());
    }

    //BUSCAR POR ID
    public ClienteResponseDTO buscarClientePorId(UUID id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente com id: " + id + " não encontrado")));
    }

    //ATUALIZAR CADASTRO
    @Transactional
    public ClienteResponseDTO atualizar(UUID id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Cliente com id: " + id + " não encontrado"));

        mapper.atualizar(dto, cliente);


        return mapper.toDto(repository.save(cliente));

    }

    //DELETAR
    @Transactional
    public void deletarCliente(UUID id) {
        Cliente cliente = repository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Cliente com id: " + id + " não encontrado"));

        repository.deleteById(id);
    }


}


