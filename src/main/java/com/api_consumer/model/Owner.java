package com.api_consumer.model;

import lombok.Data;


@Data
public class Owner{
        private String login;
        private Integer id;
        private String node_id;
        private String type;
        private boolean site_admin;

        public Owner(String login, Integer id, String node_id, String type, boolean site_admin) {
                this.login = login;
                this.id = id;
                this.node_id = node_id;
                this.type = type;
                this.site_admin = site_admin;
        }
}
