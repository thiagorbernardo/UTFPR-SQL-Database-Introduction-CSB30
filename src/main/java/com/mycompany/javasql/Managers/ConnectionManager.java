package com.mycompany.javasql.Managers;

import com.mycompany.javasql.Save.*;

import java.sql.*;
import java.util.*;

public class  ConnectionManager {
    private final SaveJSON saveJson = new SaveJSON("src/main/resources/save/save.json");
    private final Save save;
    private Connection connection;
    private DatabaseMetaData databaseMetaData;

    public ConnectionManager() {
        this.save = this.saveJson.read();
    }

    public ArrayList<ConnectionData> getConnections() {
        return this.save.connections;
    }

    public void newConnection(ConnectionData connectionData) throws SQLException {
        this.useConnection(connectionData);
        this.save.addConnection(connectionData);
        this.saveJson.save(this.save);
    }

    public void useConnection(ConnectionData connectionData) throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.dispose();
        }
        this.connection = DriverManager.getConnection(
                connectionData.getUrl(),
                connectionData.getUser(),
                connectionData.getPassword()
        );
        this.databaseMetaData = this.connection.getMetaData();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public ArrayList<CatalogMetaData> getCatalogs() throws SQLException {
        String[] types = {"TABLE", "VIEW"};

        ArrayList<CatalogMetaData> result = new ArrayList<>();
        ResultSet catalogs = this.databaseMetaData.getCatalogs();
        while(catalogs.next()){
            ArrayList<TableMetaData> schemas = new ArrayList<>();
            String catalogName = catalogs.getString(1);
            ResultSet tables = this.databaseMetaData.getTables(catalogName, null, "%", types);
            while (tables.next()) {
                TableMetaData table = new TableMetaData(
                        tables.getString("TABLE_NAME"),
                        tables.getString("TABLE_TYPE")
                );
                    ResultSet primaryKeys = this.databaseMetaData.getPrimaryKeys(catalogName, table.getType().toString(), table.getName());

                    while (primaryKeys.next()) {
                        table.addPrimaryKey(primaryKeys.getString("COLUMN_NAME"));
                    }

                    ResultSet columns = this.databaseMetaData.getColumns(catalogName, null, table.getName(), "%");

                    while (columns.next()) {
                        table.addColumn(
                                columns.getString("COLUMN_NAME"),
                                columns.getString("TYPE_NAME"),
                                columns.getInt("COLUMN_SIZE")
                        );
                    }
                schemas.add(table);
            }
            result.add(new CatalogMetaData(catalogName, schemas));
        }

        return result;
    }

    public void dispose() throws SQLException {
        this.connection.close();
    }

}
