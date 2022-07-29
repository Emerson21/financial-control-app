package br.com.vr.development.financialcontrolapp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableRabbit
@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories
//@EnableOpenApi
public class FinancialControlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialControlAppApplication.class, args);
	}

}
