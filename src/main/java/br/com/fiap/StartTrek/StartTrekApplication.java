package br.com.fiap.StartTrek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.fiap.StartTrek.repository")
public class StartTrekApplication {
	public static void main(String[] args) {
		SpringApplication.run(StartTrekApplication.class, args);
	}
}
