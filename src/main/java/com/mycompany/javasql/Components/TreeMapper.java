package com.mycompany.javasql.Components;

import com.mycompany.javasql.Managers.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.*;
import java.util.*;

public class TreeMapper {
    private ConnectionManager connectionManager;
    private final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("ROOT NODE");

    public TreeMapper() {
        this.connectionManager = new ConnectionManager();
        try {
            ArrayList<ConnectionData> connections = connectionManager.getConnections();

            if (connections.isEmpty()) {
                connectionManager.newConnection(new ConnectionData(
                        "jdbc:mysql://localhost/university",
                        "root",
                        "jjJJ@12345")
                );
            }

            ConnectionData conData = connectionManager.getConnections().get(0);
            connectionManager.useConnection(conData);

            ArrayList<CatalogMetaData> catalogs = this.connectionManager.getCatalogs();
            for (CatalogMetaData catalog : catalogs) {
                // ex: university, livrosdb, employees
                DefaultMutableTreeNode schemaNode = new DefaultMutableTreeNode(catalog.getName());
                DefaultMutableTreeNode tablesNode = new DefaultMutableTreeNode("Tables");
                DefaultMutableTreeNode viewsNode = new DefaultMutableTreeNode("Views");
                for (TableMetaData table : catalog.getTables()) {
                     // ex: student, instructor
                     DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(table.getName());
                     for (ColumnMetaData column : table.getColumns()) {
                         // id, name
                         DefaultMutableTreeNode columnNode = new DefaultMutableTreeNode(column.getFormattedName());
                         tableNode.add(new DefaultMutableTreeNode(columnNode));
                     }
                     if(table.isTable()){
                         tablesNode.add(tableNode);
                     } else {
                         viewsNode.add(tableNode);
                     }
                    schemaNode.add(tablesNode);
                    schemaNode.add(viewsNode);
                }
                rootNode.add(schemaNode);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DefaultMutableTreeNode getRootNode(){
        return this.rootNode;
    }
}
