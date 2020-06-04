package pl.ice.GameMasterHelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GameMasterHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameMasterHelperApplication.class, args);
	}

}
