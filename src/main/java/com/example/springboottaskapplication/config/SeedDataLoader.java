package com.example.springboottaskapplication.config;

import com.example.springboottaskapplication.models.Task;
import com.example.springboottaskapplication.models.TaskStatus;
import com.example.springboottaskapplication.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDataLoader implements CommandLineRunner {

    @Autowired
    TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        taskService.deleteAll();

        Task task1 = new Task();
        task1.setDescription("generated Task 1");
        task1.setStatus(TaskStatus.BACKLOG);

        Task task2 = new Task();
        task2.setDescription("another generated Task");
        task2.setStatus(TaskStatus.DOING);

        taskService.save(task1);
        taskService.save(task2);
    }

}
