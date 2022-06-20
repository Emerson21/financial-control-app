package br.com.vr.development.financialcontrolapp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@SpringBootApplication
@EnableFeignClients
//@EnableOpenApi
public class FinancialControlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialControlAppApplication.class, args);
	}

}
