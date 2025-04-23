package com.sermaluc.prueba.apiuser.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sermaluc.prueba.apiuser.model.Telefono;
import com.sermaluc.prueba.apiuser.model.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Crear un usuario de prueba
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setNombre("Eusebio Jimenez");
        user.setEmail("euji@prueba4.cl");
        user.setPassword("Password1&");
        user.setTelefonos(List.of(new Telefono("1234567", "1", "57")));

        // Guardar el usuario
        User savedUser = userRepository.save(user);

        // Verificar que el usuario fue guardado correctamente
        assertNotNull(savedUser.getId());
        assertEquals("Eusebio Jimenez", savedUser.getNombre());
        assertEquals("euji@prueba4.cl", savedUser.getEmail());
        assertEquals(1, savedUser.getTelefonos().size());
    }

    @Test
    public void testFindByEmail() {
        // Crear y guardar un usuario
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setNombre("Maria Lopez");
        user.setEmail("malo@prueba5.cl");
        user.setPassword("Password2!");
        userRepository.save(user);

        // Buscar el usuario por correo
        User foundUser = userRepository.findByEmail("malo@prueba5.cl");

        // Verificar que el usuario fue encontrado
        assertNotNull(foundUser);
        assertEquals("Maria Lopez", foundUser.getNombre());
    }
}
