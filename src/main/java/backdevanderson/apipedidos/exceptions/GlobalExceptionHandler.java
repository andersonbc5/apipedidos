package backdevanderson.apipedidos.exceptions;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, WebRequest request) {
        ErroPadrao erroPadrao = new ErroPadrao(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroPadrao);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroPadrao> handleRegraDeNegocio(RegraDeNegocioException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new ErroPadrao(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        ex.getMessage(),
                        LocalDateTime.now(),
                        request.getDescription(false).replace("uri=", "")

                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> handleValidacao(MethodArgumentNotValidException ex) {
        List<ErroCampo> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErroCampo(
                        error.getField(),
                        error.getDefaultMessage()
                )).toList();

        ErroValidacao erroValidacao = new ErroValidacao(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Campos inválidos",
                LocalDateTime.now(),
                erros
        );

        return ResponseEntity.badRequest().body(erroValidacao);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ErroPadrao> handleRecursoDuplicado(
            RegistroDuplicadoException ex, WebRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroPadrao(
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage(),
                        LocalDateTime.now(),
                        request.getDescription(false).replace("uri=", "")

                ));
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> handleExceptionGenerica(
            Exception ex, WebRequest request) {

        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroPadrao(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        "Erro inesperado. Contate o suporte.",
                        LocalDateTime.now(),
                        request.getDescription(false).replace("uri=", "")
                ));
    }
}
