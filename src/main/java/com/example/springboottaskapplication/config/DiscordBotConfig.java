package com.example.springboottaskapplication.config;

import com.example.springboottaskapplication.listeners.TaskListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DiscordBotConfig {

    @Value("${discord-token}")
    String token;

    @Autowired
    TaskListener taskListener;

    @Bean
    public DiscordApi discordApi() {
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .setAllNonPrivilegedIntents()
                .login()
                .join();

        api.addListener(taskListener);
        return api;
    }

}
