package backdevanderson.apipedidos.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer estoque
) {
}
