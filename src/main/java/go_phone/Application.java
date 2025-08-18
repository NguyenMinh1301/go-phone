package go_phone;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		// Load .env
		loadEnv();

		SpringApplication.run(Application.class, args);
	}

	private static void loadEnv() {
		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.filename(".env")
				.load();

		// Database
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		// JWT
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		System.setProperty("JWT_ISSUER", dotenv.get("JWT_ISSUER"));
		System.setProperty("JWT_ACCESS_EXP_MINUTES", dotenv.get("JWT_ACCESS_EXP_MINUTES"));
	}

}
