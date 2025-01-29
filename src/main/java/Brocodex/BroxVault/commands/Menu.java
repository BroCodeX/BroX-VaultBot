package Brocodex.BroxVault.commands;

import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class Menu implements Command {
    @Override
    public SendMessage apply(MessageDTO dto) {
        return null;
    }
}
