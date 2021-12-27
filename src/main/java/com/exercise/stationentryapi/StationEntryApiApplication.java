package com.exercise.stationentryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.exercise.stationentryapi"})
public class StationEntryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StationEntryApiApplication.class, args);
    }

}
