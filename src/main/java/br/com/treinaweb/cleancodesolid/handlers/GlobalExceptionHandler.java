package br.com.treinaweb.cleancodesolid.handlers;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.treinaweb.cleancodesolid.dtos.outputs.ErrorResponse;
import br.com.treinaweb.cleancodesolid.exceptions.ValidacaoException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErrorResponse> handleValidacaoException(ValidacaoException exception) {
        ErrorResponse body = new ErrorResponse();

        body.setTimestamp(LocalDateTime.now());
        body.setStatus("BAD REQUEST");
        body.setMensagem(exception.getLocalizedMessage());

        return ResponseEntity.badRequest().body(body);
    }

}
