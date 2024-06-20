package com.makersacademy.toon_together.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import se.michaelthelin.spotify.SpotifyApi;

@Configuration
@PropertySource("classpath:application-secret.properties") // Specify your secret properties file
public class SpotifyConfig {

    @Value("${spotify.access-token}")
    private String accessToken;

    @Bean
    public SpotifyApi spotifyApi() {
        return new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
    }
}
