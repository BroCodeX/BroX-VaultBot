package Brocodex.BroxVault.service;

import Brocodex.BroxVault.commands.Menu;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import Brocodex.BroxVault.mapper.UserMapper;
import Brocodex.BroxVault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private Menu menuCommand;

    public SendMessage userEntrypoint(MessageDTO dto) {
        var maybeUser = repository.findByTelegramId(dto.getUserId());
        if (maybeUser.isEmpty()) {
            return createUser(dto);
        } else if(maybeUser.isPresent() && !maybeUser.get().isActive()) {
            return SendMessage.builder()
                    .chatId(dto.getChatId())
                    .text("Your user is inactive inside the Vault.\n" +
                            "Please contact to your administrator")
                    .build();
        }
        return menuCommand.apply(dto);
    }

    public SendMessage createUser(MessageDTO dto) {
        return null;
    }

    public SendMessage deleteUser(MessageDTO dto) {
        return null;
    }

    public SendMessage updateUser(MessageDTO dto) {
        return null;
    }
}
