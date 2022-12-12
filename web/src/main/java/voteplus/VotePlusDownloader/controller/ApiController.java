package voteplus.VotePlusDownloader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voteplus.VotePlusDownloader.service.TMDBService;
import voteplus.VotePlusDownloader.utils.TMDBConstants;

import java.util.ArrayList;
import java.util.List;

@Profile("local-dev")
@RestController
@RequestMapping("/manual-test-call")
public class ApiController {

    private final TMDBService tmdbService;

    @Value("${tmdb.api.key.v3}")
    private String TMDB_API_KEY_V3;

    @Autowired
    public ApiController(TMDBService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/netflix-call")
    private ResponseEntity<String> testNetflixCall(){
        List<String> pathVariable = new ArrayList<>();
        pathVariable.add(TMDBConstants.SEARCH);
        pathVariable.add(TMDBConstants.MOVIE);
        MultiValueMap<String,String> stringStringMultiValueMap = new LinkedMultiValueMap<>();
        stringStringMultiValueMap.add(TMDBConstants.QUERY, TMDBConstants.NETFLIX);
        return ResponseEntity.ok(tmdbService.callServiceV3(pathVariable, stringStringMultiValueMap));
    }
}
