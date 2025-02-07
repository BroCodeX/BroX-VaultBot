package Brocodex.BroxVault.service.user;

import Brocodex.BroxVault.commands.Command;
import Brocodex.BroxVault.commands.Menu;
import Brocodex.BroxVault.commands.Start;
import Brocodex.BroxVault.controller.DirectController;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DirectControllerTest {
    private DirectController directController;

    @Mock
    private TelegramClient client;

    @Mock
    private Start startCommand;

    @Mock
    private Menu menuCommand;

    MessageDTO dto;

    @BeforeEach
    public void init() {
        dto = new MessageDTO();
        dto.setTelegramId(12345L);
        dto.setChatId(54321L);
        dto.setUserName("Yandex Name");
        dto.setMessage("Yandex Message");

        directController = new DirectController(startCommand, menuCommand);
        directController.setClient(client);
    }

    @Test
    public void incorrectCommandTest() throws TelegramApiException {
        directController.handleDirectMessage(dto);

        ArgumentCaptor<SendMessage> captor = ArgumentCaptor.forClass(SendMessage.class);
        verify(client).execute(captor.capture());

        SendMessage actualMessage = captor.getValue();
        assertThat(actualMessage.getText()).
                isEqualTo(String.format("Something went wrong. Command '%s' not found", dto.getMessage()));
        assertThat(actualMessage.getChatId()).isEqualTo(dto.getChatId());
    }

    @Test
    public void correctCommandTest() throws TelegramApiException {
        dto.setMessage("/start");
        SendMessage expectedResponse = SendMessage.builder()
                .chatId(dto.getChatId())
                .text("Test successful")
                .build();

        when(startCommand.apply(dto)).thenReturn(expectedResponse);

        directController.handleDirectMessage(dto);

        ArgumentCaptor<SendMessage> captor = ArgumentCaptor.forClass(SendMessage.class);
        verify(client).execute(captor.capture());

        SendMessage actualMessage = captor.getValue();
        assertThat(actualMessage.getChatId()).isEqualTo(dto.getChatId());
        assertThat(actualMessage.getText()).isEqualTo("Test successful");
    }
}
