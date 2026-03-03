package backdevanderson.apipedidos.service;


import backdevanderson.apipedidos.dto.ClienteRequestDTO;
import backdevanderson.apipedidos.dto.ProdutoRequestDTO;
import backdevanderson.apipedidos.dto.ProdutoResponseDTO;
import backdevanderson.apipedidos.exceptions.RecursoNaoEncontradoException;
import backdevanderson.apipedidos.mapper.ProdutoMapper;
import backdevanderson.apipedidos.model.Produto;
import backdevanderson.apipedidos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    @Transactional
    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto){
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    //buscar todos
    public List<ProdutoResponseDTO> listarProdutos(){
        return mapper.toDtoList(repository.findAll());
    }

    //buscar por id
    public ProdutoResponseDTO buscarPorId(UUID id){
        return mapper.toDto(repository.findById(id).orElseThrow(()->
                new RecursoNaoEncontradoException("Produto com id: " + id + " não encontrado")));
    }

    //buscar por nome
    public ProdutoResponseDTO buscarPorNome(String nome){
        return mapper.toDto(repository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome));
    }

    @Transactional
    public ProdutoResponseDTO atualizar(UUID id, ProdutoRequestDTO dto){
        Produto produto = repository.findById(id).orElseThrow(()->
                new RecursoNaoEncontradoException("Produto com id: " + id + " não encontrado"));

        mapper.atualizar(dto, produto);

        return mapper.toDto(repository.save(produto));
    }

    @Transactional
    public void deletar(UUID id){
        Produto produto = repository.findById(id).orElseThrow(()->
                new RecursoNaoEncontradoException("Produto com id: " + id + " não encontrado"));

        repository.deleteById(id);
    }
}
