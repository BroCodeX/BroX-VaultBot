package Brocodex.BroxVault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BroxVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(BroxVaultApplication.class, args);
	}

}
