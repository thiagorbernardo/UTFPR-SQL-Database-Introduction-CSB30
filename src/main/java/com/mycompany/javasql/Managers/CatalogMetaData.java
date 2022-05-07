package com.mycompany.javasql.Managers;

import java.util.ArrayList;

public class CatalogMetaData {
    private final String name;
    private final ArrayList<TableMetaData> tables;

    public CatalogMetaData(String name, ArrayList<TableMetaData> tables) {
        this.name = name;
        this.tables = tables;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<TableMetaData> getTables(){
        return this.tables;
    }
}
