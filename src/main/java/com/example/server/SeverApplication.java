package com.example.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.server.mapper")
public class SeverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeverApplication.class, args);
    }

}
