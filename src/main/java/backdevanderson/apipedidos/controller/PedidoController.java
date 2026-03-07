package backdevanderson.apipedidos.controller;

import backdevanderson.apipedidos.dto.PedidoRequestDTO;
import backdevanderson.apipedidos.dto.PedidoResponseDTO;

import backdevanderson.apipedidos.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrar(@Valid @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO pedidoResponseDTO = service.cadastrarPedido(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedidoResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(pedidoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        List<PedidoResponseDTO> list = service.listarPedidos();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.buscarPedidoPorId(id));
    }



}
