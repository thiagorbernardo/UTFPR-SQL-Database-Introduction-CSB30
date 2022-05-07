package com.mycompany.javasql;

import java.sql.*;
import java.util.*;

import com.mycompany.javasql.Managers.*;
import com.mycompany.javasql.Save.*;

public class Start {
    public void begin() {
        ConnectionManager connectionManager = new ConnectionManager();

        ArrayList<ConnectionData> connections = connectionManager.getConnections();

        if (connections.isEmpty()) {
            connectionManager.newConnection(new ConnectionData(
                    "jdbc:mysql://localhost/university",
                    "root",
                    "jjJJ@12345"));
        }

        ConnectionData conData = connectionManager.getConnections().get(0);

        System.out.println(conData.getId());

        try {
            connectionManager.useConnection(conData);
            Connection connection = connectionManager.getConnection();
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM student");

            Export test = new Export(System.currentTimeMillis() + "");
            ResultMap resultMap = new ResultMap();
            ResultSetMetaData rsmd = rs.getMetaData();

            ArrayList<Object> header = new ArrayList<>();

            for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
             header.add(rsmd.getColumnName(i));
            }

            resultMap.addCSVItem(header);

            while (rs.next()) {
                Map<String, Object> itemJSON = new HashMap<>();
                ArrayList<Object> itemCSV = new ArrayList<>();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    itemJSON.put(rsmd.getColumnName(i), rs.getObject(i));
                    itemCSV.add(rs.getObject(i));
                }

                resultMap.addJSONItem(itemJSON);
                resultMap.addCSVItem(itemCSV);
            }
//            test.saveJSON(resultMap);
//            test.saveCSV(resultMap);
            //            connectionManager.dispose();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela(connectionManager).setVisible(true);
            }
        });

        // for (ConnectionData con : connectionManager.getConnections()) {
        // System.out.println(con.getUrl());
        // }
    }
    
    // public static void main(String[] args) {
    //     ConnectionManager connectionManager = new ConnectionManager();

    //     ArrayList<ConnectionData> connections = connectionManager.getConnections();

    //     if (connections.isEmpty()) {
    //         connectionManager.newConnection(new ConnectionData(
    //                 "jdbc:mysql://localhost/university",
    //                 "root",
    //                 "thi109032"));
    //     }

    //     ConnectionData conData = connectionManager.getConnections().get(0);

    //     System.out.println(conData.getId());

    //     try {
    //         connectionManager.useConnection(conData);
    //         Connection connection = connectionManager.getConnection();
    //         Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    //         ResultSet rs = st.executeQuery("SELECT * FROM student");

    //         Export test = new Export(System.currentTimeMillis() + "");
    //         ResultMap rsMap = new ResultMap();
    //         ResultSetMetaData rsmd = rs.getMetaData();
    //         ArrayList<Object> header = new ArrayList<>();

    //         for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
    //             header.add(rsmd.getColumnName(i));
    //         }

    //         rsMap.addCSVItem(header);

    //         while (rs.next()) {
    //             Map<String, Object> itemJSON = new HashMap<>();
    //             ArrayList<Object> itemCSV = new ArrayList<>();

    //             for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
    //                 itemJSON.put(rsmd.getColumnName(i), rs.getObject(i));
    //                 itemCSV.add(rs.getObject(i));
    //             }

    //             rsMap.addJSONItem(itemJSON);
    //             rsMap.addCSVItem(itemCSV);
    //         }
    //         test.saveJSON(rsMap);
    //         test.saveCSV(rsMap);

    //         connectionManager.getTables();
    //         connectionManager.dispose();
    //     } catch (SQLException e) {
    //         throw new RuntimeException(e);
    //     }
    // }
}
