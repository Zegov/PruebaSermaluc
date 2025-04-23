package com.sermaluc.prueba.apiuser.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionCustom {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error: " + ex.getMessage()));
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleEmailAlreadyExists(EmailExistsException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("El correo ya registrado"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Ocurrió un error inesperado: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private static class ErrorResponse {
        private String mensaje;

        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
