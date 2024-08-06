package com.api_consumer.controller;


import com.api_consumer.model.BranchesInfo;
import com.api_consumer.service.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/")
public class GitHubController {

    @Autowired
    private ConsumerServiceImpl gitHubService;

    @GetMapping("/{username}")
    public Flux<BranchesInfo> getUserReposAndBranches(@PathVariable String username){
        return gitHubService.getRepositoryPlusBranches(username);
    }
}
