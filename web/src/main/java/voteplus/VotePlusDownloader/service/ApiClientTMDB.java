package voteplus.VotePlusDownloader.service;

import org.springframework.util.MultiValueMap;
import voteplus.VotePlusDownloader.exception.ApiClientException;

import java.util.List;

public interface ApiClientTMDB {
     String callServiceV3(List<String> pathVariables, MultiValueMap<String,String> queryParametersMap) throws ApiClientException;
     String callServiceV4(List<String> pathVariables, MultiValueMap<String,String> queryParametersMap) throws ApiClientException;

}
