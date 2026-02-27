package backdevanderson.apipedidos.exceptions;

import java.time.LocalDateTime;

public record ErroPadrao(
        int status,
        String error,
        String mensagem,
        LocalDateTime timestamp,
        String path
) {
}
