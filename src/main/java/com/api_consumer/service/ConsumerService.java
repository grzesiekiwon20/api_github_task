package com.api_consumer.service;

import com.api_consumer.model.BranchesInfo;
import reactor.core.publisher.Flux;

public interface ConsumerService {

    Flux<BranchesInfo> getRepositoryPlusBranches(String username);
}
