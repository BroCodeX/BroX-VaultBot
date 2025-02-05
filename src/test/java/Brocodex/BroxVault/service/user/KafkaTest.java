package Brocodex.BroxVault.service.user;

import Brocodex.BroxVault.constants.TopicKeys;
import Brocodex.BroxVault.controller.DirectController;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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

    @MockitoBean
    private DirectController directController;

    @Captor
    private ArgumentCaptor<MessageDTO> dtoCaptor;

    @Test
    public void sendMessage() {
        String topicKey = TopicKeys.DIRECT_MESSAGE.getKey();

        MessageDTO dto = new MessageDTO();
        dto.setTelegramId(12345L);
        dto.setChatId(54321L);
        dto.setUserName("Yandex Name");
        dto.setMessage("Yandex Message");

        template.send(topicKey, dto);

        await().atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    verify(directController).handleDirectMessage(dtoCaptor.capture());
                });

        MessageDTO actualDto = dtoCaptor.getValue();

        assertThat(actualDto).usingRecursiveComparison().isEqualTo(dto);
    }
}
