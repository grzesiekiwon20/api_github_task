package com.api_consumer.util;

public class RepoNotFoundException extends RuntimeException {
    public RepoNotFoundException(String repoNotFound) {
        super(repoNotFound);
    }
}