package com.example.springboottaskapplication.controllers;

import com.example.springboottaskapplication.models.Task;
import com.example.springboottaskapplication.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public Iterable<Task> getTasks() {
        logger.debug("REST TaskController::getTasks");
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable Long id) {
        logger.debug("REST TaskController::getTaskById: {}", id);
        Optional<Task> optionalTask = taskService.getById(id);
        return optionalTask;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        logger.debug("REST TaskController::deleteTaskById: {}", id);
        taskService.delete(id);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewTask(@RequestBody Task newTask) {
        logger.debug("REST TaskController::createTask: {}", newTask);
        Task task = new Task();
        task.setDescription(newTask.getDescription());
        task.setStatus(newTask.getStatus());
        task = taskService.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task newTask) {
        logger.debug("REST TaskController::updateTask[{}]: {}", id, newTask);
        Optional<Task> optionalTask = taskService.getById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(newTask.getDescription());
            task.setStatus(newTask.getStatus());
            task = taskService.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
