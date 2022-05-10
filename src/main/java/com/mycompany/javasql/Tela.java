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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

/**
 * @author João Lucas
 */
public class Tela extends javax.swing.JFrame {
    /**
     * Creates new form Tela
     */
    private ResultMap resultMapJSON;
    private ResultMap resultMapCSV;
    private final ConnectionManager connectionManager;

    public Tela(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        initComponents();

        /* ------------------------------- JTree ---------------------------------- */
        /* Populating JTree */
        TreeMapper tree = new TreeMapper(this.connectionManager);
        JTree jTree1 = new JTree(tree.getRootNode());
        jTree1.setRootVisible(false);

        /* Add to scroll pane */
        this.treePane.add(jTree1);
        this.treePane.setViewportView(jTree1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        tablePane = new JScrollPane();
        jTable1 = new JTable();
        sqlField = new JTextField();
        jLabel1 = new JLabel();
        executeButton = new JButton();
        saveJSONButton = new JButton();
        saveCSVButton = new JButton();
        treePane = new JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sqlField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        /* ------------------------------- JLabel ---------------------------------- */
        jLabel1.setText("Select Query");

        executeButton.setText("Execute");
        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        saveJSONButton.setText("Save JSON");
        saveJSONButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        saveCSVButton.setText("Save CSV");
        saveCSVButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(treePane, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(sqlField, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(executeButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(saveJSONButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(saveCSVButton)))
                                .addContainerGap(204, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(treePane)
                                        .addComponent(tablePane, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(treePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(sqlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(executeButton)
                                                        .addComponent(saveJSONButton)
                                                        .addComponent(saveCSVButton))))
                                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Connection connection = connectionManager.getConnection();
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sqlField.getText());
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnQuantity = rsmd.getColumnCount();
            ArrayList<ArrayList<Object>> teste = new ArrayList<>();
            ArrayList<Object> header = new ArrayList<>();

            for (int i = 1; i <= columnQuantity; i++) {

                header.add(rsmd.getColumnName(i));

            }
            resultMapJSON = new ResultMap();
            resultMapCSV = new ResultMap();
            while (rs.next()) {
                Map<String, Object> itemJSON = new HashMap<>();
                ArrayList<Object> itemCSV = new ArrayList<>();
                ArrayList<Object> linha = new ArrayList<>();
                for (int i = 1; i <= columnQuantity; i++) {

                    linha.add(rs.getString(i));
                    itemJSON.put(rsmd.getColumnName(i), rs.getObject(i));
                    itemCSV.add(rs.getObject(i));

                }
                resultMapJSON.addJSONItem(itemJSON);
                resultMapCSV.addCSVItem(itemCSV);
                teste.add(linha);
            }
            String[] column = new String[header.size()];
            String[][] data = new String[teste.size()][header.size()];

            for (int i = 0; i < teste.size(); i++) {
                for (int j = 0; j < header.size(); j++) {
                    column[j] = header.get(j).toString();
                    Object item = teste.get(i).get(j);
                    if (item == null) {
                        data[i][j] = "null";
                    } else {
                        data[i][j] = item.toString();
                    }
                }
            }

            DefaultTableModel model = new DefaultTableModel(data, column);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            tablePane.add(table);
            tablePane.setViewportView(table);
        } catch (SQLException e) {
            System.out.println("Error " + e.toString());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Export test = new Export(System.currentTimeMillis() + "");
        try {
            test.saveJSON(resultMapJSON);
        } catch (NullPointerException e) {
            System.out.println("Execute uma query");
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        Export test = new Export(System.currentTimeMillis() + "");
        try {
            test.saveCSV(resultMapCSV);
        } catch (NullPointerException e) {
            System.out.println("Execute uma query");
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
    private javax.swing.JButton executeButton;
    private javax.swing.JButton saveJSONButton;
    private javax.swing.JButton saveCSVButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane tablePane;
    private javax.swing.JScrollPane treePane;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField sqlField;
    // End of variables declaration//GEN-END:variables
}
