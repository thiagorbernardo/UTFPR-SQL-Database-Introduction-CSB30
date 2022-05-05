package com.mycompany.javasql.Managers;

import com.mycompany.javasql.Save.*;

import java.sql.*;
import java.util.*;

public class ConnectionManager {
    private final SaveJSON saveJson = new SaveJSON();
    private final Save save;
    private Connection connection;
    private DatabaseMetaData databaseMetaData;

    public ConnectionManager() {
        this.save = this.saveJson.read();
    }

    public ArrayList<ConnectionData> getConnections() {
        return this.save.connections;
    }

    public void newConnection(ConnectionData connectionData) {
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

    public ArrayList<TableMetaData> getTables() throws SQLException {
        String[] types = {"TABLE", "VIEW"};
        ResultSet tables = this.databaseMetaData.getTables(this.connection.getCatalog(), null, "%", types);
        ArrayList<TableMetaData> result = new ArrayList<>();
        while (tables.next()) {
            TableMetaData table = new TableMetaData(
                    tables.getString("TABLE_CAT"),
                    tables.getString("TABLE_NAME"),
                    tables.getString("TABLE_TYPE")
            );
            if (table.type != TableType.VIEW) {
                ResultSet primaryKeys = this.databaseMetaData.getPrimaryKeys(table.catalog, table.type.toString(), table.name);

                while (primaryKeys.next()) {
                    table.addPrimaryKey(primaryKeys.getString("COLUMN_NAME"));
                }

                ResultSet columns = this.databaseMetaData.getColumns(table.catalog, null, table.name, "%");

                while (columns.next()) {
                    table.addColumn(
                            columns.getString("COLUMN_NAME"),
                            columns.getString("TYPE_NAME"),
                            columns.getInt("COLUMN_SIZE")
                    );
                }
            }

            table.selfPrint();
            result.add(table);
        }

        return result;
    }

    public void dispose() throws SQLException {
        this.connection.close();
    }
}
