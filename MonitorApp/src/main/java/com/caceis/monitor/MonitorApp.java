package com.caceis.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitorApp {
	public static void main(String[] args) {

		SpringApplication.run(MonitorApp.class, args);
	}
}
