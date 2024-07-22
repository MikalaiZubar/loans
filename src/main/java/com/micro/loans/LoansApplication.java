package com.micro.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "loansAuditorAware")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API documentation",
				description = "Study project Loans microservice REST API documentation",
				version = "v1",
				contact = @Contact(
						name = "Mikalai Zubar",
						email = "some_email@gmail.com"
				),
				license = @License(
						name = "MikZed",
						url = "https://www.google.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Open API documentation",
				url = "https://springdoc.org/#getting-started"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
