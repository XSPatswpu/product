package com.cherry.cell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CellApplication {

	public static void main(String[] args) {
		SpringApplication.run(CellApplication.class, args);
	}
}
