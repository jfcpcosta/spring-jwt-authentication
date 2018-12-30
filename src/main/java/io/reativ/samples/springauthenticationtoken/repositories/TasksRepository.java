package io.reativ.samples.springauthenticationtoken.repositories;

import io.reativ.samples.springauthenticationtoken.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Long> {
}
