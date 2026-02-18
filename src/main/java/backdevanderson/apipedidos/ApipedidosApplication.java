package backdevanderson.apipedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApipedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApipedidosApplication.class, args);
	}

}
