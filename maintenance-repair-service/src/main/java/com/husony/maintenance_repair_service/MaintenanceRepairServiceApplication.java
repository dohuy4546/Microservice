package com.husony.maintenance_repair_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MaintenanceRepairServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceRepairServiceApplication.class, args);
	}

}
