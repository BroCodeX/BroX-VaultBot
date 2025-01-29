package Brocodex.BroxVault.commands;

import Brocodex.BroxVault.dto.mq.MessageDTO;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command {
    SendMessage apply(MessageDTO dto);
}
