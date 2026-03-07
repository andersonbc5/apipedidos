package backdevanderson.apipedidos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ItemPedidoRequestDTO(
        @NotNull
        UUID produtoId,
        @NotNull
        @Positive
        Integer quantidade
) {
}
