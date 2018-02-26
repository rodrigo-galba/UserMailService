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
        User user = new User("Username", "user_login", "user@email.com", "plain_password");
        repository.save(user);

        assertNotNull(user.getId());
        assertEquals("Username", user.getName());
        assertEquals("user_login", user.getLogin());
        assertEquals("user@email.com", user.getEmail());
        assertEquals("plain_password", user.getEncryptedPassword());
        assertFalse(user.isAdmin());
        assertNotNull(user.getCreatedDate());
        assertNull(user.getUpdatedDate());
    }

    @Test
    public void validatesNullName() {
        try {
            User user = new User(null, "senna", "senna@email.com", "plain_password");
            repository.save(user);
            fail("should validates null name");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("não pode ser nulo"));
        }
    }

    @Test
    public void validatesEmptyName() {
        try {
            User user = new User("", "senna", "senna@email.com", "plain_password");
            repository.save(user);
            fail("should validates empty name");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar vazio"));
        }
    }

    @Test
    public void validatesNullLogin() {
        try {
            User user = new User("name", null, "senna@email.com", "plain_password");
            repository.save(user);
            fail("should validates null login");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("não pode ser nulo"));
        }
    }

    @Test
    public void validatesEmptyLogin() {
        try {
            User user = new User("name", "", "senna@email.com", "plain_password");
            repository.save(user);
            fail("should validates empty login");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar vazio"));
        }
    }

    @Test
    public void validatesNullEmail() {
        try {
            User user = new User("name", "login", null, "plain_password");
            repository.save(user);
            fail("should validates null email");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("não pode ser nulo"));
        }
    }

    @Test
    public void validatesEmptyEmail() {
        try {
            User user = new User("name", "login", "", "plain_password");
            repository.save(user);
            fail("should validates empty email");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar vazio"));
        }
    }

    @Test
    public void validatesNullPassword() {
        try {
            User user = new User("name", "login", "", null);
            repository.save(user);
            fail("should validates null password");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("não pode ser nulo"));
        }
    }

    @Test
    public void validatesEmptyPassword() {
        try {
            User user = new User("name", "login", "email", "");
            repository.save(user);
            fail("should validates empty password");
        } catch (Exception ex) {
            assertThat(ex.getLocalizedMessage(), containsString("Não pode estar vazio"));
        }
    }
}
