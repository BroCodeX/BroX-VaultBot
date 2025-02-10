package Brocodex.BroxVault.service.user;

import Brocodex.BroxVault.dto.mq.MessageDTO;
import Brocodex.BroxVault.model.User;
import Brocodex.BroxVault.repository.UserRepository;
import Brocodex.BroxVault.service.UserService;
import Brocodex.BroxVault.utils.ModelGenerator;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DirectTest {
    @Autowired
    private ModelGenerator generator;
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    private User user;

    @BeforeEach
    public void prepare() {
        repository.deleteAll();
        user = Instancio.of(generator.getUserModel()).create();
    }

    @Test
    public void addUserRepo() {
        repository.save(user);

        var maybeUser = repository.findByTelegramId(user.getTelegramId()).orElse(null);
        assertThat(maybeUser).isNotNull();
        assertThat(maybeUser.getUserName()).isEqualTo(user.getUserName());
        assertThat(maybeUser.getTelegramId()).isEqualTo(user.getTelegramId());
        assertThat(maybeUser.getCreatedAt()).hasYear(2025);
    }

    @Test
    public void addUserService() {
        MessageDTO dto = new MessageDTO();
        dto.setTelegramId(12345L);
        dto.setChatId(54321L);
        dto.setUserName("Yandex Name");
        dto.setMessage("Yandex Message");

        var message = service.userEntrypoint(dto);
        assertThat(message).isNotNull();
        assertThat(message.getChatId()).isEqualTo(String.valueOf(dto.getChatId()));
        assertThat(message.getText()).isEqualTo(dto.getMessage());

        var maybeUser = repository.findByTelegramId(dto.getTelegramId()).orElse(null);
        assertThat(maybeUser).isNotNull();
        assertThat(maybeUser.getUserName()).isEqualTo(dto.getUserName());
    }
}
