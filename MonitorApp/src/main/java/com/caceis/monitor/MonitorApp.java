package com.caceis.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.caceis.monitor.repository")
public class MonitorApp {
	public static void main(String[] args) {

		SpringApplication.run(MonitorApp.class, args);
	}
}
