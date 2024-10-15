package com.sparta.scheduleplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchedulePlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulePlusApplication.class, args);
    }

}
