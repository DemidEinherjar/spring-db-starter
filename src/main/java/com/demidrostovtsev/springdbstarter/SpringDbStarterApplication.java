package com.demidrostovtsev.springdbstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SpringDbStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDbStarterApplication.class, args);
	}

}
