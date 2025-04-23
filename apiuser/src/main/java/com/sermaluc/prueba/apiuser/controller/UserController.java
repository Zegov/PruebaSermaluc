package com.sermaluc.prueba.apiuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sermaluc.prueba.apiuser.exception.EmailExistsException;
import com.sermaluc.prueba.apiuser.model.User;
import com.sermaluc.prueba.apiuser.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        
        try {
            
            User createdUser = userService.registerUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (EmailExistsException e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse("Error registrando al usuario"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Object createErrorResponse(String message) {
        return new ErrorResponse(message);
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
