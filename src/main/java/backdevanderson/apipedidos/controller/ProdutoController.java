package backdevanderson.apipedidos.controller;

import backdevanderson.apipedidos.dto.ClienteResponseDTO;
import backdevanderson.apipedidos.dto.ProdutoRequestDTO;
import backdevanderson.apipedidos.dto.ProdutoResponseDTO;
import backdevanderson.apipedidos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO produtoResponseDTO = service.cadastrarProduto(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(produtoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos(){
        List<ProdutoResponseDTO> list = service.listarProdutos();
        if (list.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> listarPorId(@PathVariable("id") UUID id ){
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoResponseDTO> buscarNome(@PathVariable("nome") String nome){
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.ok(service.atualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
