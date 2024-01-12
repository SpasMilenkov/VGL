package com.jaba.vgl.resources;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.jaba.vgl.repositories", "com.jaba.vgl.repositories.impl"}
)
@ComponentScan(basePackages = "com.jaba.vgl")
public class TestConfiguration {
}
