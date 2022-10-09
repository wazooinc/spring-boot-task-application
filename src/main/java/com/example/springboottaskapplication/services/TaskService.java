package com.example.springboottaskapplication.services;

import com.example.springboottaskapplication.models.Task;
import com.example.springboottaskapplication.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setCreatedAt(LocalDateTime.now());
        }

        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }

}
