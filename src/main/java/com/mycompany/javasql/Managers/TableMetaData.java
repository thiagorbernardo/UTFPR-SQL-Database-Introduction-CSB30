package com.mycompany.javasql.Managers;

import java.util.ArrayList;

enum TableType {
    TABLE, VIEW
}

public class TableMetaData {
    String catalog;
    String name;
    TableType type;
    ArrayList<String> primaryKeys;
    ArrayList<ColumnMetaData> columns;

    public TableMetaData(String catalog, String name, String type) {
        this.catalog = catalog;
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

    public void selfPrint() {
        System.out.println("Table catalog: " + this.catalog);
        System.out.println("Table name: " + this.name);
        System.out.println("Table type: " + this.type);
        StringBuilder columns = new StringBuilder("Columns:");
        for (ColumnMetaData col : this.columns) {
            if(col.isPrimaryKey){
                columns.append(" ").append("PRIMARY_KEY");
            }
            columns.append(" ").append(col.name).append(" ").append(col.type).append("(").append(col.size).append(")");
        }
        System.out.println(columns);
        System.out.println();
    }
}
