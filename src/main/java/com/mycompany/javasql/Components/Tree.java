package com.mycompany.javasql.Components;

import com.mycompany.javasql.Managers.ConnectionManager;
import com.mycompany.javasql.Managers.TableMetaData;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tree extends JTree {

    String catalog;
    DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");
    ArrayList<TableMetaData> tables;
    ConnectionManager connectionManager;
    DefaultMutableTreeNode[] nodes;

    public Tree(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        try {
            DefaultMutableTreeNode top = new DefaultMutableTreeNode("university");
            tables = this.connectionManager.getTables();
            nodes = new DefaultMutableTreeNode[connectionManager.getTablenumber()];
            nodes = createNodes();
            System.out.println("Construtora");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private DefaultMutableTreeNode[] createNodes() {
        int x = 0;
        for(TableMetaData s : tables){
            nodes[x] = new DefaultMutableTreeNode(s.getName());
            x = x+1;
        }


        nodes[1].add(top);

//        tables.forEach((n) -> DefaultMutableTreeNode tables.get(1).getCatalog() = );
//        DefaultMutableTreeNode category = null;
//        DefaultMutableTreeNode book = null;
//        System.out.println("funçao");
//        category = new DefaultMutableTreeNode("categoria 1");
//        top.add(category);
//
//        //original Tutorial
//        book = new DefaultMutableTreeNode("teste");
//        category.add(book);
//
//        category = new DefaultMutableTreeNode("categoria 2");
//        top.add(category);
//
//        //VM
//        book = new DefaultMutableTreeNode("teste2");
//        category.add(book);
        return nodes;
    }

    public DefaultMutableTreeNode[] getNodes() {
        return nodes;
    }
}
