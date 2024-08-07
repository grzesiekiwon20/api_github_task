package com.api_consumer.model;


import lombok.Data;

@Data
public class BranchesInfo {

    private String repoName;
    private String login;
    private String name;
    private Commit commit;

    public BranchesInfo(String repoName,String login,String name, Commit commit) {
        this.repoName = repoName;
        this.login = login;
        this.name = name;
        this.commit = commit;
    }
}
