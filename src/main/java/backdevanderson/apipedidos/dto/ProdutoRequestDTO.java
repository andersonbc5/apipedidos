package backdevanderson.apipedidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        @Positive
        BigDecimal preco,
        @PositiveOrZero
        @NotNull
        Integer estoque
) {
}
