package com.makersacademy.toon_together;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.makersacademy.toon_together.service.SpotifyService.getTrack_Async;
import static com.makersacademy.toon_together.service.SpotifyService.getTrack_Sync;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        getTrack_Sync();
        getTrack_Async();
    }


}
