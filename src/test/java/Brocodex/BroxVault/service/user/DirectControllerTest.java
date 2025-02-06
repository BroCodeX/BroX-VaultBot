package Brocodex.BroxVault.service.user;

import Brocodex.BroxVault.controller.DirectController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@ExtendWith(MockitoExtension.class)
public class DirectControllerTest {
    @InjectMocks
    private DirectController directController;

    @Mock
    private TelegramClient client;
}
