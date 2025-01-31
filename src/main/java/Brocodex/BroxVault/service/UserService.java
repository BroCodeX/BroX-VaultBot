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
        var user = mapper.map(dto);
        user.setActive(true);
        repository.save(user);
        return menuCommand.greeting(dto);
    }

    public SendMessage deleteUser(MessageDTO dto) {
        if (!repository.existsByTelegramId(dto.getUserId())) {
            return notFoundMessage(dto);
        }
        repository.deleteByTelegramId(dto.getUserId());
        return SendMessage.builder()
                .chatId(dto.getChatId())
                .text("User with " + dto.getUserId() + " deleted")
                .build();
    }

    public SendMessage notFoundMessage(MessageDTO dto) {
        return SendMessage.builder()
                .chatId(dto.getChatId())
                .text("Your user hasn't found in the repo.\n" +
                        "Please type /start")
                .build();
    }
}
