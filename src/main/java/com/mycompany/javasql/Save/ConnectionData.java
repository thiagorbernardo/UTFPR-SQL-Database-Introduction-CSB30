package com.mycompany.javasql.Save;

import java.util.UUID;

public class ConnectionData {
    private String id;
    private String url;
    private String user;
    private String password;

    public ConnectionData(String url, String user, String password) {
        this.id = UUID.randomUUID().toString();
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
