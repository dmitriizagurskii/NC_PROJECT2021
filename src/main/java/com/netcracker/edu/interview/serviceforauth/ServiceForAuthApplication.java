package com.netcracker.edu.interview.serviceforauth;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceForAuthApplication {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/policy_db", "admin", "admin").load();
		flyway.migrate();

		SpringApplication.run(ServiceForAuthApplication.class, args);
	}

}
