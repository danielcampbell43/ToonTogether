package com.makersacademy.toon_together.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;

import java.io.IOException;

@Configuration
@PropertySource("classpath:application-secret.properties") // Specify your secret properties file
public class SpotifyConfig {

    @Value("${spotify.access-token}")
    private String accessToken;

    @Value("${spotify.refresh-token}")
    private String refreshToken;

    @Value("${spotify.client-id}")
    private String clientId;

    @Value("${spotify.client-secret}")
    private String clientSecret;

    private SpotifyApi spotifyApi;

    @Bean
    public SpotifyApi spotifyApi() {
        this.spotifyApi =  new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRefreshToken(refreshToken)
                .build();

        refreshAccessToken();

        return spotifyApi;

    }

    @Scheduled(fixedRate = 3500000)
    private void refreshAccessToken() {
        AuthorizationCodeRefreshRequest refreshRequest = spotifyApi.authorizationCodeRefresh().build();

        try {
            AuthorizationCodeCredentials credentials = refreshRequest.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());
            System.out.println("Access token obtained successfully.");
        } catch (IOException | se.michaelthelin.spotify.exceptions.SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            e.printStackTrace();
            System.err.println("Failed to obtain access token.");
        }
    }


}
