package com.sermaluc.prueba.apiuser.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sermaluc.prueba.apiuser.exception.EmailExistsException;
import com.sermaluc.prueba.apiuser.model.User;
import com.sermaluc.prueba.apiuser.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailExistsException("El correo ya se encuentra registrado");
        }

        try {            
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreado(LocalDateTime.now());
            user.setModificado(LocalDateTime.now());
            user.setUltimoLogin(LocalDateTime.now());
            user.setToken(UUID.randomUUID().toString());
            user.setEstaActivo(true);

            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el usuario", e);
        }
    }
}
