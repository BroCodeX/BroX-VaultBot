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

    public SendMessage greeting(MessageDTO dto) {
        var keyboard = KeyboardFactory.getVaultOperaitons();
        String text = String.format("""
                Hello %s!
                ===
                This is a Hashicorp's Vault Bot.
                You can save, get, delete or update
                your credentials using the commands below.
                """, dto.getFirstName());
        return SendMessage.builder()
                .chatId(dto.getChatId())
                .replyMarkup(keyboard)
                .text(text)
                .build();
    }
}
