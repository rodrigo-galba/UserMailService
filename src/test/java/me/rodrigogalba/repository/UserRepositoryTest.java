package me.rodrigogalba.repository;

import me.rodrigogalba.model.User;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void createValidUser() {
        User user = new User();
        user.setName("Username");
        user.setLogin("user_login");
        user.setEmail("user@email.com");
        user.setPassword("plain_password");
        repository.save(user);

        assertNotNull(user.getId());
        assertEquals("Username", user.getName());
        assertEquals("user_login", user.getLogin());
        assertEquals("user@email.com", user.getEmail());
        assertEquals("plain_password", user.getEncryptedPassword());
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
            repository.save(user);
            fail("should validates null name");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar em branco"));
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
            repository.save(user);
            fail("should validates empty name");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar em branco"));
        }
    }

    @Test
    public void validatesNullLogin() {
        try {
            User user = new User();
            user.setName("Username");
            user.setEmail("user@email.com");
            user.setPassword("plain_password");
            repository.save(user);
            fail("should validates null login");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar em branco"));
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
            repository.save(user);
            fail("should validates empty login");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar em branco"));
        }
    }

    @Test
    public void validatesNullEmail() {
        try {
            User user = new User();
            user.setName("Username");
            user.setLogin("user_login");
            user.setPassword("plain_password");
            repository.save(user);
            fail("should validates null email");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar em branco"));
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
            repository.save(user);
            fail("should validates empty email");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar em branco"));
        }
    }

    @Test
    public void validatesNullPassword() {
        try {
            User user = new User();
            user.setName("Username");
            user.setLogin("user_login");
            user.setEmail("user@email.com");
            repository.save(user);
            fail("should validates null password");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("could not execute statement"));
        }
    }
}
