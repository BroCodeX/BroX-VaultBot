package Brocodex.BroxVault.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDTO {
    private Long id;
    @NotNull
    @Size(max = 256)
    private Long telegramId;

    @NotBlank
    @Size(max = 100)
    private String userName;

    private String firstName;
    private String LastName;
    private LocalDate createdAt;
}
