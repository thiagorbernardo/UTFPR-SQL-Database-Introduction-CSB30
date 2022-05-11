package com.mycompany.javasql;

import java.sql.*;
import java.util.*;

import com.mycompany.javasql.Components.LogLine;
import com.mycompany.javasql.Components.Status;
import com.mycompany.javasql.Managers.*;
import com.mycompany.javasql.Save.*;

public class Start {
    public void begin() {
        ConnectionManager connectionManager = new ConnectionManager();

        ArrayList<ConnectionData> connections = connectionManager.getConnections();

//        if (connections.isEmpty()) {
//            connectionManager.newConnection(new ConnectionData(
//                    "jdbc:mysql://localhost/university",
//                    "root",
//                    "jjJJ@12345"));
//        }

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

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
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

//        LogLine teste = new LogLine(1, Status.SUCCESS, "Select * From Tobias", "Vai tomar no cu");
//        logData.add(teste);
//        String[][] data = new String[logData.size()][5];
//        for (int i = 0; i < logData.size(); i++) {
//            data[i] = logData.get(i).getLine();
// DefaultTableModel logModel = new DefaultTableModel(data, new String[]{
//     "#", "Time", "Status", "Query", "Message"
// }) {
// public boolean isCellEditable(int rowIndex, int columnIndex) {
//     return false;
// }
// };
// JTable logTable = new JTable(logModel);
// logTable.getColumnModel().getColumn(0).setPreferredWidth(50);
// logTable.getColumnModel().getColumn(1).setPreferredWidth(120);
// logTable.getColumnModel().getColumn(2).setPreferredWidth(120);
// logTable.getColumnModel().getColumn(3).setPreferredWidth(325);
// logTable.getColumnModel().getColumn(4).setPreferredWidth(325);
// log.add(logTable);
// log.setViewportView(logTable);
     }
}
