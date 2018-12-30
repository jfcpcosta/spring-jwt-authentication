package io.reativ.samples.springauthenticationtoken.security.repositories;

import io.reativ.samples.springauthenticationtoken.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
