/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.javasql;

import com.mycompany.javasql.Components.*;
import com.mycompany.javasql.Managers.*;
import com.mycompany.javasql.Save.Export;
import com.mycompany.javasql.Save.ResultMap;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

/**
 * @author João Lucas
 */
public class Tela extends javax.swing.JFrame {
    /**
     * Creates new form Tela
     */
    private ResultMap resultMap = new ResultMap();
    private final ConnectionManager connectionManager;
    private final ArrayList<LogLine> logData = new ArrayList<>();

    public Tela(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        initComponents();

        /* ------------------------------- JTree ---------------------------------- */
        this.updateTree();

    }

    private void updateTree() {
        /* Populating JTree */
        TreeMapper tree = new TreeMapper(this.connectionManager);
        JTree jTree1 = new JTree(tree.getRootNode());
        jTree1.setRootVisible(false);

        /* Add to scroll pane */
        this.treePane.add(jTree1);
        this.treePane.setViewportView(jTree1);
    }

    private void updateLogsTable() {
        String[][] data = new String[logData.size()][5];
        for (int i = 0; i < logData.size(); i++) {
            data[i] = logData.get(i).getLine();
            DefaultTableModel logModel = new DefaultTableModel(data, new String[]{
                    "#", "Time", "Status", "Query", "Message"
            }) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            // Create new table with model
            JTable logTable = new JTable(logModel);

            // Setting sizes
            logTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            logTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            logTable.getColumnModel().getColumn(2).setPreferredWidth(120);
            logTable.getColumnModel().getColumn(3).setPreferredWidth(325);
            logTable.getColumnModel().getColumn(4).setPreferredWidth(325);

            // Add to scroll pane
            this.log.add(logTable);
            this.log.setViewportView(logTable);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sqlField = new javax.swing.JTextField();
        executeButton = new javax.swing.JButton();
        jsonButton = new javax.swing.JButton();
        csvButton = new javax.swing.JButton();
        treePane = new javax.swing.JScrollPane();
        log = new javax.swing.JScrollPane();
        tableLog = new javax.swing.JTable();
        tablePane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sqlField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        sqlField.setToolTipText("Query");

        executeButton.setText("Execute");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });

        jsonButton.setText("Save JSON");
        jsonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsonButtonActionPerformed(evt);
            }
        });

        csvButton.setText("Save CSV");
        csvButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvButtonActionPerformed(evt);
            }
        });

        log.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tableLog.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "#", "Time", "Status", "Query", "Message"
                }
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tableLog.setToolTipText("");
        log.setViewportView(tableLog);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(treePane, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(sqlField, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(tablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jsonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(executeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(csvButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(treePane)
                                        .addComponent(sqlField, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(executeButton)
                                                .addGap(33, 33, 33)
                                                .addComponent(jsonButton)
                                                .addGap(41, 41, 41)
                                                .addComponent(csvButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(log, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        String sqlText = this.sqlField.getText();
        try {
            Connection connection = connectionManager.getConnection();
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            Pattern selectPattern = Pattern.compile("SELECT", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Pattern updatePattern = Pattern.compile("UPDATE", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Pattern createPattern = Pattern.compile("(CREATE|ALTER|DROP)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

            long startedTime = System.currentTimeMillis();
            long endedTime;
            if (updatePattern.matcher(sqlText).find()) {
                int rowsAffected = st.executeUpdate(sqlText);
                endedTime = System.currentTimeMillis();
                // Update log table with executed query
                this.logData.add(
                        new LogLine(
                                this.logData.size() + 1,
                                Status.SUCCESS,
                                sqlText,
                                "Updated " + rowsAffected + " in " + (endedTime - startedTime) + " ms"));
                this.updateLogsTable();
                return;
            }

            if (!selectPattern.matcher(sqlText).find()) {
                st.execute(sqlText);
                endedTime = System.currentTimeMillis();
                // Update log table with executed query
                this.logData.add(
                        new LogLine(
                                this.logData.size() + 1,
                                Status.SUCCESS,
                                sqlText,
                                "Executed in " + (endedTime - startedTime) + " ms"));
                this.updateLogsTable();

                if (createPattern.matcher(sqlText).find()) {
                    this.updateTree();
                }
                return;
            }

            // Executing query
            ResultSet rs = st.executeQuery(sqlText);
            endedTime = System.currentTimeMillis();

            // Getting result set metadata
            ResultSetMetaData rsMetadata = rs.getMetaData();
            int columnQuantity = rsMetadata.getColumnCount();
            // Creating header and data from result set
            ArrayList<ArrayList<Object>> result = new ArrayList<>();
            ArrayList<Object> header = new ArrayList<>();

            // Populating header
            for (int i = 1; i <= columnQuantity; i++) {
                header.add(rsMetadata.getColumnName(i));
            }

            // Create result map to future save
            this.resultMap = new ResultMap();
            this.resultMap.addCSVHeader(header);

            while (rs.next()) {
                Map<String, Object> itemJSON = new HashMap<>();
                ArrayList<Object> itemCSV = new ArrayList<>();
                ArrayList<Object> line = new ArrayList<>();
                for (int i = 1; i <= columnQuantity; i++) {
                    line.add(rs.getString(i));
                    itemJSON.put(rsMetadata.getColumnName(i), rs.getObject(i));
                    itemCSV.add(rs.getObject(i));
                }
                this.resultMap.addJSONItem(itemJSON);
                this.resultMap.addCSVItem(itemCSV);
                result.add(line);
            }

            // Add to logs
            this.logData.add(
                    new LogLine(
                            this.logData.size() + 1,
                            Status.SUCCESS,
                            sqlText,
                            "Returned " + result.size() + " rows in " + (endedTime - startedTime) + " ms"));
            this.updateLogsTable();

            // Vectors to add on table
            String[] column = new String[header.size()];
            String[][] data = new String[result.size()][header.size()];

            // Populating vectors
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < header.size(); j++) {
                    column[j] = header.get(j).toString();
                    Object item = result.get(i).get(j);
                    if (item == null) {
                        data[i][j] = "null";
                    } else {
                        data[i][j] = item.toString();
                    }
                }
            }

            // Creating Table
            DefaultTableModel model = new DefaultTableModel(data, column);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            tablePane.add(table);
            tablePane.setViewportView(table);
        } catch (SQLException e) {
            this.logData.add(new LogLine(this.logData.size()+1, Status.ERROR, sqlText, e.toString()));
            this.updateLogsTable();

            JOptionPane.showMessageDialog(this, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }

    }//GEN-LAST:event_executeButtonActionPerformed

    private void csvButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvButtonActionPerformed
        try {
            if (this.resultMap.isEmpty()) {
                throw new Exception("Última query executada não foi um SELECT");
            }

            String fileName = String.valueOf(System.currentTimeMillis());
            Export export = new Export(fileName);
            export.saveCSV(this.resultMap);
            JOptionPane.showMessageDialog(
                    this,
                    "Arquivo Salvo em src/main/resources/export/" + fileName + ".csv",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_csvButtonActionPerformed

    private void jsonButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (this.resultMap.isEmpty()) {
                throw new Exception("Última query executada não foi um SELECT");
            }

            String fileName = String.valueOf(System.currentTimeMillis());
            Export export = new Export(fileName);
            export.saveJSON(this.resultMap);
            JOptionPane.showMessageDialog(
                    this,
                    "Arquivo Salvo em src/main/resources/export/" + fileName + ".json",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }

    }


    public void run() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela(connectionManager).setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton csvButton;
    private javax.swing.JButton executeButton;
    private javax.swing.JButton jsonButton;
    private javax.swing.JScrollPane log;
    private javax.swing.JTextField sqlField;
    private javax.swing.JTable tableLog;
    private javax.swing.JScrollPane tablePane;
    private javax.swing.JScrollPane treePane;
    // End of variables declaration//GEN-END:variables
}
