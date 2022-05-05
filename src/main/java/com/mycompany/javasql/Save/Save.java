package com.mycompany.javasql.Save;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Save {
    public long createdAt = System.currentTimeMillis();
    public long updatedAt;
    public ArrayList<ConnectionData> connections;

    public Save() {
        this.connections = new ArrayList<>();
    }

    public void addConnection(ConnectionData connection) {
        this.connections.add(connection);
    }

    public void setUpdatedAt(){
        this.updatedAt = System.currentTimeMillis();
    }

    public boolean isSaveEmpty() {
        return Objects.isNull(this.createdAt);
    }
}