package me.rodrigogalba.repository;

import me.rodrigogalba.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void createValidUser() {
        User user = new User("Airton Senna", "senna", "senna@email.com", "plain_password");
        repository.save(user);

        assertNotNull(user.getId());
        assertEquals("Airton Senna", user.getName());
        assertEquals("senna", user.getLogin());
        assertEquals("senna@email.com", user.getEmail());
        assertEquals("plain_password", user.getEncryptedPassword());
        assertFalse(user.isAdmin());
        assertNotNull(user.getCreatedDate());
        assertNull(user.getUpdatedDate());
    }
}
