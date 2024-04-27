package com.zenyatta.nttdata.challenge.app;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zenyatta.nttdata.challenge")
public class Application {
    public static void main(final String... args) {
        run(Application.class, args);
    }
}
