package com.example.springboottaskapplication.listeners;

import com.example.springboottaskapplication.models.Task;
import com.example.springboottaskapplication.models.TaskStatus;
import com.example.springboottaskapplication.services.TaskService;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskListener implements MessageCreateListener {

    @Autowired
    private TaskService taskService;

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        Message message = messageCreateEvent.getMessage();
        String messageContent = message.getContent();
        if (messageCreateEvent.getMessageAuthor().isBotUser()) {
            return;
        } else if (messageContent.startsWith("!task")) {
            String[] content = messageContent.split("!task ");
            Task task = new Task();
            task.setStatus(TaskStatus.BACKLOG);
            task.setDescription(content[1]);
            taskService.save(task);

            new MessageBuilder()
                    .append("Created a new Task: [" + task.getId() + "]")
                    .appendCode("java", "Description: " + task.getDescription() + "\n" + "Status: " + task.getStatus())
                    .send(messageCreateEvent.getChannel());
        }


    }
}
