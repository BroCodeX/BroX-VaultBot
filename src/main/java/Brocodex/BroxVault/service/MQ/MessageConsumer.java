package Brocodex.BroxVault.service.MQ;

import Brocodex.BroxVault.config.RabbitConfig;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @RabbitListener(queues = RabbitConfig.directQueue)
    public void receiveDirectMessages(MessageDTO dto) {

    }

    @RabbitListener(queues = RabbitConfig.vaultQueue)
    public void receiveDirectMessages(MessageDTO dto) {

    }
}
