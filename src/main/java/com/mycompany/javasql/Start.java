package com.mycompany.javasql;

import java.sql.*;
import java.util.*;

import com.mycompany.javasql.Managers.*;
import com.mycompany.javasql.Save.*;

import javax.xml.transform.Result;

public class Start {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();

        ArrayList<ConnectionData> connections = connectionManager.getConnections();

        if (connections.isEmpty()) {
            connectionManager.newConnection(new ConnectionData(
                    "jdbc:mysql://localhost/university",
                    "root",
                    "thi109032"));
        }

        ConnectionData conData = connectionManager.getConnections().get(0);

        System.out.println(conData.getId());

        try {
            connectionManager.useConnection(conData);
            Connection connection = connectionManager.getConnection();
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM student");

            ExportJSON test = new ExportJSON("result.json");
            ResultMap resultMap = new ResultMap();

            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();

                ResultSetMetaData rsmd = rs.getMetaData();

                for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
                    item.put(rsmd.getColumnName(i), rs.getObject(i));
                }

                resultMap.addItem(item);
            }
            test.save(resultMap);

            connectionManager.getTables();
            connectionManager.dispose();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // for (ConnectionData con : connectionManager.getConnections()) {
        // System.out.println(con.getUrl());
        // }
    }
}
