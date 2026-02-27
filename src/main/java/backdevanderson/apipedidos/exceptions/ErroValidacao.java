package backdevanderson.apipedidos.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ErroValidacao(
        int status,
        String erro,
        String mensagem,
        LocalDateTime timestamp,
        List<ErroCampo> erros
) {
}
