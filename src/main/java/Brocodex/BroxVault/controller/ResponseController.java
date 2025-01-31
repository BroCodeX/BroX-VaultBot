package Brocodex.BroxVault.controller;

import Brocodex.BroxVault.commands.Command;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Map;

@Component
public class ResponseController {
    @Setter
    private TelegramClient client;

    private Map<String, Command> commandMap;

    public ResponseController() {

    }

    public void sendMessage(SendMessage message) {
        try {
            client.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
