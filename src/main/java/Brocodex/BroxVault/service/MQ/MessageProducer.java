package Brocodex.BroxVault.service.MQ;

import Brocodex.BroxVault.constants.TopicKeys;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessage(TopicKeys topic, MessageDTO message) {
        template.send(topic.getKey(), message);
    }
}
