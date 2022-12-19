package voteplus.VotePlusDownloader.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RestApiCaller {

    @Value("${tmdb.api.url.v3}")
    private String TMDB_API_URL_V3;

    @Value("${tmdb.api.url.v4}")
    private String TMDB_API_URL_V4;



    @Value("${tmdb.api.key.v4}")
    private String TMDB_API_KEY_V4;

    @Bean
    public WebClient createWebClientV3(){
        return WebClient.builder()
                .baseUrl(TMDB_API_URL_V3)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient createWebClientV4(){
        return WebClient.builder()
                .baseUrl(TMDB_API_URL_V4)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ TMDB_API_KEY_V4)
                .build();
    }

}
