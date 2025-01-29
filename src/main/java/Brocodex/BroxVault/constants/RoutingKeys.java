package Brocodex.BroxVault.constants;

import lombok.Getter;

@Getter
public enum RoutingKeys {
    USER_CREATE("users.create"),
    VAULT_CREATE("vault.create"),
    VAULT_UPDATE("vault.update"),
    VAULT_DELETE("vault.delete"),
    VAULT_GET("vault.get");

    private String key;

    RoutingKeys(String key) {
        this.key = key;
    }
}
