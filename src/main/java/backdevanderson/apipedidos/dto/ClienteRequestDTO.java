package backdevanderson.apipedidos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "Campo Obrigatório")
        String nome,
        @Email(message = "Formato de e-mail inválido")
        @NotBlank(message = "Campo Obrigatório")
        String email,
        @NotBlank(message = "Campo Obrigatório")
        String telefone
) {
}
