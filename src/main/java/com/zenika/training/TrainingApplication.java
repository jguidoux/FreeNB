package com.zenika.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrainingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(TrainingApplication.class, args);


    }


}
