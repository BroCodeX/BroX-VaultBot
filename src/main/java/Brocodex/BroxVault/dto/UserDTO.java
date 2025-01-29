package Brocodex.BroxVault.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDTO {
    private Long id;
    private Long telegramId;
    private String userName;
    private String firstName;
    private String LastName;
    private LocalDate createdAt;
}
