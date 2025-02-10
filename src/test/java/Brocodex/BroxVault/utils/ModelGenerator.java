package Brocodex.BroxVault.utils;

import Brocodex.BroxVault.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
public class ModelGenerator {
    @Autowired
    private Faker faker;

    private Model<User> userModel;

    @PostConstruct
    public void init() {
        userModel = makeUser();
    }

    private Model<User> makeUser() {
        return Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .ignore(Select.field(User::getFirstName))
                .ignore(Select.field(User::getLastName))
                .supply(Select.field(User::getTelegramId), () -> faker.idNumber())
                .supply(Select.field(User::getUserName), () -> faker.funnyName().name())
                .supply(Select.field(User::getIsActive), () -> true)
                .supply(Select.field(User::getCreatedAt), () -> LocalDate.now())
                .toModel();
    }
}
