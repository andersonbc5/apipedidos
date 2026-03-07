package backdevanderson.apipedidos.dto;

import backdevanderson.apipedidos.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDTO(
        UUID id,
        UUID clienteId,
        String clienteNome,
        LocalDateTime dataPedido,
        StatusPedido statusPedido,
        BigDecimal valorTotal,
        List<ItemPedidoResponseDTO> itens
) {
}
