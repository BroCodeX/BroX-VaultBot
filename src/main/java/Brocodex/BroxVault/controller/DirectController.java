package Brocodex.BroxVault.controller;

import Brocodex.BroxVault.commands.Command;
import Brocodex.BroxVault.commands.Menu;
import Brocodex.BroxVault.commands.Start;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Map;

@Component
public class DirectController {
    @Setter
    private TelegramClient client;

    private Map<String, Command> commandMap;

    public DirectController(Start start, Menu menu) {
        this.commandMap = Map.of("/start", start, "/menu", menu);
    }

    public void handleDirectMessage(MessageDTO dto) {
        if (!commandMap.containsKey(dto.getMessage())) {
            var message = SendMessage.builder()
                    .chatId(dto.getChatId())
                    .text(String.format("Something went wrong. Command '%s' not found", dto.getMessage()))
                    .build();
            sendMessage(message);
        } else {
            var command = commandMap.get(dto.getMessage());
            var result = command.apply(dto);
            sendMessage(result);
        }

    }

    public void sendMessage(SendMessage message) {
        try {
            client.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
