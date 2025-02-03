package Brocodex.BroxVault.service.MQ;

import Brocodex.BroxVault.controller.CallbackController;
import Brocodex.BroxVault.controller.DirectController;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @Autowired
    private CallbackController callbackController;
    @Autowired
    private DirectController directController;

    @KafkaListener(topics = "direct.message", groupId = "vault_group", containerFactory = "groupVaultContainerFactory")
    public void receiveDirectMessages(MessageDTO dto) {
        directController.handleDirectMessage(dto);
    }

    @KafkaListener(topics = "vault.message", groupId = "vault_group", containerFactory = "groupVaultContainerFactory")
    public void receiveVaultMessages(MessageDTO dto) {
        callbackController.handleCallbackMessage(dto);
    }
}
