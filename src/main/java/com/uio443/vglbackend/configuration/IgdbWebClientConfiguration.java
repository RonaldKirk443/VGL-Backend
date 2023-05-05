package com.uio443.vglbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class IgdbWebClientConfiguration {
    public static final String IGDB_CLIENT_ID = System.getenv("IGDB_CLIENT_ID");
    public static final String IGDB_TOKEN = System.getenv("IGDB_TOKEN");

    @Bean
    public WebClient igdbWebclient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        return WebClient
                .builder()
                .baseUrl("https://api.igdb.com/v4")
                .defaultHeader("Client-ID", IGDB_CLIENT_ID)
                .defaultHeader("Authorization", String.format("Bearer %s", IGDB_TOKEN))
                .exchangeStrategies(strategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
