package Brocodex.BroxVault.dto.mq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MessageDTO {
    @NotNull
    @Size(max = 256)
    private Long userId;
    private Long chatId;

    @NotBlank
    @Size(max = 100)
    private String userName;
    private String firstName;
    private String LastName;

    @NotBlank
    private String message;
}
