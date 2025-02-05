package Brocodex.BroxVault.service.user;

import Brocodex.BroxVault.constants.TopicKeys;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class KafkaTest {
    @Autowired
    private KafkaTemplate<String, MessageDTO> template;

    private final CountDownLatch latch = new CountDownLatch(1);

    private String receivedMessage;

    @Test
    public void sendMessage() {
        String message = "Yandex test message";
        String topicKey = TopicKeys.DIRECT_MESSAGE.getKey();

//        var upd = new Update();
//        upd.setMessage(Message.builder().text(message).build());
        MessageDTO dto = new MessageDTO();
        dto.setTelegramId(12345L);
        dto.setChatId(54321L);
        dto.setUserName("Yandex Name");
        dto.setMessage("Yandex Message");

        // тут надо подумать как протестировать Kafka, что сообщения передаются
        template.send(topicKey, dto);
    }
}
