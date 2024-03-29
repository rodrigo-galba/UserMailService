package me.rodrigogalba.repository;

import me.rodrigogalba.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    List<User> findByAdmin(Boolean isAdmin);

    User findByEmail(String email);

    User findById(Long id);
}
