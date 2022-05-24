package br.com.vr.development.financialcontrolapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinancialControlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialControlAppApplication.class, args);
	}

}
