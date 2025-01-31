package Brocodex.BroxVault.commands;

import Brocodex.BroxVault.dto.mq.MessageDTO;
import Brocodex.BroxVault.factory.KeyboardFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class Menu implements Command {
    @Override
    public SendMessage apply(MessageDTO dto) {
        var keyboard = KeyboardFactory.getVaultOperaitons();
        return SendMessage.builder()
                .chatId(dto.getChatId())
                .replyMarkup(keyboard)
                .text("Please choose the following option:\n")
                .build();
    }
}
