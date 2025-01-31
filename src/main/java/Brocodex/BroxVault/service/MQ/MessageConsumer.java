package Brocodex.BroxVault.service.MQ;

import Brocodex.BroxVault.config.RabbitConfig;
import Brocodex.BroxVault.controller.CallbackController;
import Brocodex.BroxVault.controller.DirectController;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @Autowired
    private CallbackController callbackController;
    @Autowired
    private DirectController directController;

    @RabbitListener(queues = RabbitConfig.directQueue)
    public void receiveDirectMessages(MessageDTO dto) {
        directController.handleDirectMessage(dto);
    }

    @RabbitListener(queues = RabbitConfig.vaultQueue)
    public void receiveVaultMessages(MessageDTO dto) {
        callbackController.handleCallbackMessage(dto);
    }
}
