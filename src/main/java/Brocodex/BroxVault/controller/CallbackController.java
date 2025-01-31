package Brocodex.BroxVault.controller;

import Brocodex.BroxVault.commands.Command;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Map;

@Component
public class CallbackController {
    @Setter
    private TelegramClient client;

    private Map<String, Command> commandMap;

    public CallbackController() {

    }

    public void handleCallbackMessage(MessageDTO dto) {

    }

    public void sendMessage(SendMessage message) {
        try {
            client.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
