package Brocodex.BroxVault.commands;

import Brocodex.BroxVault.dto.mq.MessageDTO;
import Brocodex.BroxVault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class Start implements Command{
    @Autowired
    private UserService service;

    @Override
    public SendMessage apply(MessageDTO dto) {
        return service.userEntrypoint(dto);
    }
}
