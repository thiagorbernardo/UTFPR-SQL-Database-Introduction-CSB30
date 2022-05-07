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

    public String getFormattedName(){
        StringBuilder builder = new StringBuilder();

        builder.append(this.name).append("  ");

        if(this.isPrimaryKey){
            builder.append("PRIMARY_KEY  ");
        }

        return builder.
                append(this.type).
                append("(").append(this.size).append(")")
                .toString();
    }
}
