package com.example.batch_service;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class BatchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchServiceApplication.class, args);
    }

}