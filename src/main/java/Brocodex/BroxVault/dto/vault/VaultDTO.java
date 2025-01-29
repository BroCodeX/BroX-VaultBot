package Brocodex.BroxVault.dto.vault;

import Brocodex.BroxVault.constants.OperationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VaultDTO {
    @NotBlank(message = "Ключ не может быть пустым")
    private String key;
    @NotBlank(message = "Значение не может быть пустым")
    private String value;
    private OperationType operationType;
}
