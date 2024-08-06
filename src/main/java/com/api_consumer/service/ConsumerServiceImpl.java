package com.api_consumer.service;


import com.api_consumer.model.BranchesInfo;
import com.api_consumer.model.Repo;
import com.api_consumer.util.RepoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    private WebClient webClient;

    public Flux<BranchesInfo> getRepositoryPlusBranches(String username) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/" + username + "/repos")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RepoNotFoundException("Repository not found for username: " + username)))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Server error while fetching repositories.")))
                .bodyToFlux(Repo.class)
                .filter(repo -> !repo.isFork())
                .flatMapSequential(repoNames ->
                        webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                        .path("/repos/" + username + "/" + repoNames.getName() + "/branches")
                                        .build())
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Branches for repository not found.")))
                                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Server error while fetching branches")))
                                .bodyToFlux(BranchesInfo.class)
                                .map(branch -> new BranchesInfo(
                                        repoNames.getName(),
                                        repoNames.getOwner().getLogin(),
                                        branch.getName(),
                                        branch.getCommit())));
    }
}
