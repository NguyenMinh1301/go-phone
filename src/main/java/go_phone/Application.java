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

		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		System.setProperty("ACCESS-TOKEN-EXP", dotenv.get("ACCESS-TOKEN-EXP"));
		System.setProperty("REFRESH-TOKEN-EXP", dotenv.get("REFRESH-TOKEN-EXP"));
	}

}
