package Brocodex.BroxVault.component;

import Brocodex.BroxVault.constants.RoutingKeys;
import Brocodex.BroxVault.controller.CallbackController;
import Brocodex.BroxVault.controller.DirectController;
import Brocodex.BroxVault.dto.mq.MessageDTO;
import Brocodex.BroxVault.service.MQ.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Component
public class TelegramBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private String token;
    private final TelegramClient telegramClient;
    private final DirectController directController;
    private final CallbackController callbackController;

    private final MessageProducer producer;

    @Autowired
    public TelegramBot(@Value("${telegram.bot.token}") String token, MessageProducer producer,
                       DirectController directController, CallbackController callbackController) {
        this.token = token;
        this.producer = producer;
        telegramClient = new OkHttpTelegramClient(getBotToken());
        this.directController = directController;
        this.directController.setClient(telegramClient);
        this.callbackController = callbackController;
        this.callbackController.setClient(telegramClient);
    }

    @Override
    public void consume(Update update) {
        MessageDTO messageDTO = new MessageDTO();
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageDTO.setTelegramId(update.getMessage().getFrom().getId());
            messageDTO.setChatId(update.getMessage().getChatId());
            messageDTO.setUserName(update.getMessage().getFrom().getUserName());
            messageDTO.setFirstName(update.getMessage().getFrom().getFirstName());
            messageDTO.setLastName(update.getMessage().getFrom().getLastName());
            messageDTO.setMessage(update.getMessage().getText());
            producer.sendMessage(messageDTO, RoutingKeys.DIRECT_MESSAGE);
        }
        if (update.hasCallbackQuery()) {
            messageDTO.setTelegramId(update.getCallbackQuery().getFrom().getId());
            messageDTO.setChatId(update.getCallbackQuery().getMessage().getChatId());
            messageDTO.setUserName(update.getCallbackQuery().getFrom().getUserName());
            messageDTO.setFirstName(update.getCallbackQuery().getFrom().getFirstName());
            messageDTO.setLastName(update.getCallbackQuery().getFrom().getLastName());
            messageDTO.setMessage(update.getCallbackQuery().getData());
            producer.sendMessage(messageDTO, RoutingKeys.VAULT_MESSAGE);
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @AfterBotRegistration
    public void afterRegistration(BotSession botSession) {
        System.out.println("Registered bot running state is: " + botSession.isRunning());
    }
}
