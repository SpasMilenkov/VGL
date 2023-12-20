package com.jaba.vgl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@SpringBootApplication
@EnableJpaRepositories(
		basePackages = "com.jaba.vgl.repositories"
)
@EnableFeignClients(
		clients = {
			//TODO: add steam client...
		}
)
@ConfigurationPropertiesScan
//@EnableConfigurationProperties
public class VglApplication {

	public static void main(String[] args) {
		SpringApplication.run(VglApplication.class, args);
	}

}
