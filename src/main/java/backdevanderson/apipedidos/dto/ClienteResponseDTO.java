package backdevanderson.apipedidos.dto;



import java.util.UUID;

public record ClienteResponseDTO(
        UUID id,
        String nome,
        String email,
        String telefone
) {
}
