package Brocodex.BroxVault.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.List;

public class KeyboardFactory {
    public static InlineKeyboardMarkup getVaultOperaitons() {
        // Buttons
        InlineKeyboardButton save = new InlineKeyboardButton("SAVE");
        InlineKeyboardButton get = new InlineKeyboardButton("GET");
        InlineKeyboardButton delete = new InlineKeyboardButton("DELETE");
        InlineKeyboardButton update = new InlineKeyboardButton("UPDATE");

        // Set data
        save.setCallbackData("save");
        get.setCallbackData("get");
        delete.setCallbackData("delete");
        update.setCallbackData("update");

        // Row's
        InlineKeyboardRow firstRow = new InlineKeyboardRow(save, get);
        InlineKeyboardRow secondRow = new InlineKeyboardRow(delete, update);

        // Keyboard
        List<InlineKeyboardRow> keyboard = List.of(firstRow, secondRow);

        return new InlineKeyboardMarkup(keyboard);
    }
}
