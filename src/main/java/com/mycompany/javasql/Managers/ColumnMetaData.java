package com.mycompany.javasql.Managers;

public class ColumnMetaData {
    String name;
    String type;
    Integer size;
    boolean isPrimaryKey;

    public ColumnMetaData(String name, String type, Integer size, boolean isPrimaryKey) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getName(){
        return  this.name;
    }
}
