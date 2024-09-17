package me.sukumdev.bookapi.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private  UserRepository userRepository;

    @Test
    void saveUserTest() {
        User user = User.builder()
                .email("test@gmail.com")
                .firstName("Test")
                .lastName("User")
                .age(5)
                .build();

        User savedUser = userRepository.save(user);
        assertEquals(user, savedUser);
    }
}
