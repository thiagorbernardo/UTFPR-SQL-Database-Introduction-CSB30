package com.mycompany.javasql.Managers;

import java.util.ArrayList;

enum TableType {
    TABLE, VIEW
}

public class TableMetaData {
    private String name;
    private TableType type;
    private ArrayList<String> primaryKeys;
    private ArrayList<ColumnMetaData> columns;

    public TableMetaData(String name, String type) {
        this.name = name;
        this.type = TableType.valueOf(type);
        this.primaryKeys = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    public void addPrimaryKey(String key) {
        this.primaryKeys.add(key);
    }

    public void addColumn(String name, String type, Integer size) {
        boolean isPrimaryKey = this.primaryKeys.contains(name);
        this.columns.add(new ColumnMetaData(name, type, size, isPrimaryKey));
    }

    public String getName(){
        return this.name;
    }

    public TableType getType(){
        return this.type;
    }

    public ArrayList<ColumnMetaData> getColumns(){
        return columns;
    }

    public boolean isTable(){
        return this.type == TableType.TABLE;
    }
}
