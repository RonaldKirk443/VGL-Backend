package com.uio443.vglbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class IgdbWebClientConfiguration {
    @Bean
    public WebClient igdbWebclient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        return WebClient
                .builder()
                .baseUrl("https://api.igdb.com/v4")
                .defaultHeader("Client-ID", "csrdtykjfk5w11ji3qgpnfupll53ei")
                .defaultHeader("Authorization", "Bearer 5x2fpwtpnr9go99uofqgciaqtd4uuz")
                .exchangeStrategies(strategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
