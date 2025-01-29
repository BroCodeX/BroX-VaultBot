package Brocodex.BroxVault.service.MQ;

import Brocodex.BroxVault.config.RabbitConfig;
import Brocodex.BroxVault.constants.RoutingKeys;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    @Autowired
    private RabbitTemplate template;

    public void sendMessage(MessageDTO dto, RoutingKeys key) {
        template.convertAndSend(RabbitConfig.exchangeName, key.getKey(), dto);
    }
}
