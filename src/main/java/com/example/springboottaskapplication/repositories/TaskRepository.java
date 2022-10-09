package com.example.springboottaskapplication.repositories;

import com.example.springboottaskapplication.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
