package Brocodex.BroxVault.service.user;

import Brocodex.BroxVault.constants.TopicKeys;
import Brocodex.BroxVault.controller.DirectController;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.verify;

@SpringBootTest
@EnableKafka
@EmbeddedKafka(
        topics = {"direct.message", "vault.message"},
        brokerProperties = "listeners=PLAINTEXT://localhost:9092"
)
public class KafkaTest {
    @Autowired
    private KafkaTemplate<String, MessageDTO> template;

    private final CountDownLatch latch = new CountDownLatch(1);

    private String receivedMessage;

    @Autowired
    private DirectController directController;

    @Test
    public void sendMessage() {
        String message = "Yandex test message";
        String topicKey = TopicKeys.DIRECT_MESSAGE.getKey();

        MessageDTO dto = new MessageDTO();
        dto.setTelegramId(12345L);
        dto.setChatId(54321L);
        dto.setUserName("Yandex Name");
        dto.setMessage("Yandex Message");

        template.send(topicKey, dto);

        ArgumentCaptor<MessageDTO> dtoCaptor = ArgumentCaptor.forClass(MessageDTO.class);

        await().atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    verify(directController).handleDirectMessage(dtoCaptor.capture());
                });

        MessageDTO actualDto = dtoCaptor.getValue();

        assertThat(actualDto.getTelegramId()).isEqualTo(dto.getTelegramId());
        assertThat(actualDto.getChatId()).isEqualTo(dto.getChatId());
        assertThat(actualDto.getUserName()).isEqualTo(dto.getUserName());
        assertThat(actualDto.getMessage()).isEqualTo(dto.getMessage());
    }
}
