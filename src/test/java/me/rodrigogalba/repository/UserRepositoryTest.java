package me.rodrigogalba.repository;

import me.rodrigogalba.Application;
import me.rodrigogalba.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createValidUser() {
        User user = new User();
        user.setName("Username");
        user.setLogin("user_login");
        user.setEmail("user@email.com");
        user.setPassword("plain_password");
        userRepository.save(user);

        assertNotNull(user.getId());
        assertEquals("Username", user.getName());
        assertEquals("user_login", user.getLogin());
        assertEquals("user@email.com", user.getEmail());
        assertNotNull(user.getEncryptedPassword());
        assertFalse(user.isAdmin());
        assertNotNull(user.getCreatedDate());
        assertNotNull(user.getUpdatedDate());
    }

    @Test
    public void validatesNullName() {
        try {
            User user = new User();
            user.setLogin("user_login");
            user.setEmail("user@email.com");
            user.setPassword("plain_password");
            userRepository.save(user);
            fail("should validates null name");
        } catch (Exception ex) {
            String message = extractErrorMessage(ex);
            assertThat(message, containsString("Não pode estar em branco"));
        }
    }

    @Test
    public void validatesEmptyName() {
        try {
            User user = new User();
            user.setName("");
            user.setLogin("user_login");
            user.setEmail("user@email.com");
            user.setPassword("plain_password");
            userRepository.save(user);
            fail("should validates empty name");
        } catch (Exception ex) {
            String message = extractErrorMessage(ex);
            assertThat(message, containsString("Não pode estar em branco"));
        }
    }

    @Test
    public void validatesNullLogin() {
        try {
            User user = new User();
            user.setName("Username");
            user.setEmail("user@email.com");
            user.setPassword("plain_password");
            userRepository.save(user);
            fail("should validates null login");
        } catch (Exception ex) {
            String message = extractErrorMessage(ex);
            assertThat(message, containsString("Não pode estar em branco"));
        }
    }

    @Test
    public void validatesEmptyLogin() {
        try {
            User user = new User();
            user.setName("Username");
            user.setLogin("");
            user.setEmail("user@email.com");
            user.setPassword("plain_password");
            userRepository.save(user);
            fail("should validates empty login");
        } catch (Exception ex) {
            String message = extractErrorMessage(ex);
            assertThat(message, containsString("Tamanho deve estar entre 3 e 2147483647"));
        }
    }

    @Test
    public void validatesNullEmail() {
        try {
            User user = new User();
            user.setName("Username");
            user.setLogin("user_login");
            user.setPassword("plain_password");
            userRepository.save(user);
            fail("should validates null email");
        } catch (Exception ex) {
            String message = extractErrorMessage(ex);
            assertThat(message, containsString("Não pode estar em branco"));
        }
    }

    @Test
    public void validatesEmptyEmail() {
        try {
            User user = new User();
            user.setName("Username");
            user.setLogin("user_login");
            user.setEmail("");
            user.setPassword("plain_password");
            userRepository.save(user);
            fail("should validates empty email");
        } catch (Exception ex) {
            String message = extractErrorMessage(ex);
            assertThat(message, containsString("Não pode estar em branco"));
        }
    }

    private String extractErrorMessage(Exception ex) {
        return ((ConstraintViolationException) ex).getConstraintViolations().stream().findFirst().get().getMessage();
    }

    @Test
    public void validatesNullPassword() {
        try {
            User user = new User();
            user.setName("Username");
            user.setLogin("user_login");
            user.setEmail("user@email.com");
            userRepository.save(user);
            fail("should validates null password");
        } catch (Exception ex) {
            String message = ex.getMessage();
            assertThat(message, containsString("Senha deve ser informada."));
        }
    }
}
