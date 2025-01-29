package Brocodex.BroxVault.constants;

import lombok.Getter;

@Getter
public enum RoutingKeys {
    DIRECT_MESSAGE("direct.message"),
    VAULT_MESSAGE("vault.message");
//    VAULT_CREATE("vault.create"),
//    VAULT_UPDATE("vault.update"),
//    VAULT_DELETE("vault.delete"),
//    VAULT_GET("vault.get");

    private String key;

    RoutingKeys(String key) {
        this.key = key;
    }
}
