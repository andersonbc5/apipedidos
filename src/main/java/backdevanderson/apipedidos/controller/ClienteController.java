package backdevanderson.apipedidos.controller;

import backdevanderson.apipedidos.dto.ClienteRequestDTO;
import backdevanderson.apipedidos.dto.ClienteResponseDTO;
import backdevanderson.apipedidos.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO dto) {
        ClienteResponseDTO clienteSalvo = service.cadastrarCliente(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteSalvo.id())
                .toUri();
        return ResponseEntity.created(location).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes(){
        List<ClienteResponseDTO> listaClientes = service.listarClientes();
        if (listaClientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaClientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> listarPorId(@PathVariable ("id") UUID id){
        return ResponseEntity.ok().body(service.buscarClientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ClienteRequestDTO dto){

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") UUID id){
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
