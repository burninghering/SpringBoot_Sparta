package com.example.sparta_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스프링 부트에서 우리가 만든 스케줄러가 작동하게 권한을 줍니다.
@EnableJpaAuditing // 시간 자동 변경이 가능하도록 합니다.
@SpringBootApplication // 스프링 부트임을 선언합니다.
public class Sparta4Application {

    public static void main(String[] args) {
        SpringApplication.run(Sparta4Application.class, args);

    }

}
