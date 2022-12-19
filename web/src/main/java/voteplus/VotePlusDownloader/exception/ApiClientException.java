package voteplus.VotePlusDownloader.exception;

import lombok.Getter;

@Getter
public class ApiClientException extends Exception {
    private String errorCode;
    private String errorMessage;

    public ApiClientException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
