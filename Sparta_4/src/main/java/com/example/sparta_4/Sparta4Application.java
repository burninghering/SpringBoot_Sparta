package com.example.sparta_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //시간 자동 변경 가능
public class Sparta4Application {

    public static void main(String[] args) {
        SpringApplication.run(Sparta4Application.class, args);

    }

}
