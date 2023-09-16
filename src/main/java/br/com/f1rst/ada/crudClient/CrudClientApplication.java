package br.com.f1rst.ada.crudClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@OpenAPIDefinition(info = @Info(title = "Client API",
		version = "1.0.0",
		description = "client api documentation"
))

@SpringBootApplication
public class CrudClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudClientApplication.class, args);
	}

}
