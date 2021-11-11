package com.netcracker.demo;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/policy_db", "admin", "admin").load();
		flyway.migrate();

		SpringApplication.run(DemoApplication.class, args);
	}

}
