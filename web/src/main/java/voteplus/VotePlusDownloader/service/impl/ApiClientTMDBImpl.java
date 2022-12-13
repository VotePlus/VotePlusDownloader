package voteplus.VotePlusDownloader.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import reactor.core.publisher.Mono;
import voteplus.VotePlusDownloader.configuration.RestApiCaller;
import voteplus.VotePlusDownloader.exception.ApiClientException;
import voteplus.VotePlusDownloader.service.ApiClientTMDB;
import voteplus.VotePlusDownloader.utils.TMDBConstants;

import java.util.List;

//TODO create interface or abstrac class for it
@Service
@Slf4j
public class ApiClientTMDBImpl implements ApiClientTMDB {

    private final RestApiCaller restApiCaller;

    @Value("${tmdb.api.key.v3}")
    private String TMDB_API_KEY_V3;

    @Autowired
    public ApiClientTMDBImpl(RestApiCaller restApiCaller) {
        this.restApiCaller = restApiCaller;
    }


    //TODO Parse json to object
    public String callServiceV3(List<String> pathVariables, MultiValueMap<String,String> queryParametersMap) throws ApiClientException {
        if(pathVariables.isEmpty() || queryParametersMap.isEmpty()){
            throw new ApiClientException("0000", "MISSING PATH OR QUERY PARAMETERS");
        }

        String path = String.join("/", pathVariables);
        Mono<String> response = restApiCaller.createWebClientV3()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/" + path)
                        .queryParams(queryParametersMap)
                        .queryParam(TMDBConstants.API_KEY_QUERY, TMDB_API_KEY_V3)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        log.info(response.block());
        return response.block();
    }


    public String callServiceV4(List<String> pathVariables, MultiValueMap<String,String> queryParametersMap) throws ApiClientException {
        if(pathVariables.isEmpty() || queryParametersMap.isEmpty()){
            throw new ApiClientException("0000", "MISSING PATH OR QUERY PARAMETERS");
        }
        String path = String.join("/", pathVariables);
        Mono<String> response = restApiCaller.createWebClientV4()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/" + path)
                        .queryParams(queryParametersMap)
                        .queryParam(TMDBConstants.API_KEY_QUERY, TMDB_API_KEY_V3)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        log.info(response.block());
        return response.block();
    }
}
