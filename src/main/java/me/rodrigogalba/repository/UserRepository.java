package me.rodrigogalba.repository;

import me.rodrigogalba.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
