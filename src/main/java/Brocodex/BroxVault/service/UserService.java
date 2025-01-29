package Brocodex.BroxVault.service;

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

    public MessageDTO createUser(MessageDTO dto) {
        return null;
    }

    public SendMessage deleteUser(MessageDTO dto) {
        return null;
    }

    public SendMessage updateUser(MessageDTO dto) {
        return null;
    }
}
