package backdevanderson.apipedidos.exceptions;

public record ErroCampo(
        String campo,
        String mensagem
) {
}
