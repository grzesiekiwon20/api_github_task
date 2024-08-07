package com.api_consumer.model;

import lombok.Data;


@Data
public class Repo {
    private Integer id;
    private String node_id;
    private String name;
    private String full_name;
    private Owner owner;
    private boolean fork;

    public Repo(Integer id, String node_id, String name, String full_name, Owner owner, boolean fork) {
        this.id = id;
        this.node_id = node_id;
        this.name = name;
        this.full_name = full_name;
        this.owner = owner;
        this.fork = fork;
    }
}
