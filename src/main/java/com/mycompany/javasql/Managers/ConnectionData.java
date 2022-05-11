package com.mycompany.javasql.Managers;

import java.util.UUID;

public class ConnectionData {
    private String id;
    private String url;
    private String user;
    private String password;

    public ConnectionData(String url, String user, String password) {
        this.id = UUID.randomUUID().toString();
        this.url = "jdbc:"+url;
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return this.user;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return this.url.replace("jdbc:", "");
    }
}
