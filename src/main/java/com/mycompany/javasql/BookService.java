package com.mycompany.javasql;

import java.io.*;
import java.sql.*;

public class BookService {
    private final Connection connection;
    private final Statement statement;
    private final PreparedStatement updateStatement;

    public BookService() throws SQLException {
        String url = "jdbc:mysql://localhost/livrosdb";
        String user = "root";
        String pass = "thi109032";

        this.connection = DriverManager.getConnection(url, user, pass);
        this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.updateStatement = this.connection.prepareStatement("UPDATE livros SET capa = ? WHERE livro_id = ?");
    }

    public static void main(String args[]) {
        try {
            BookService service = new BookService();
            ResultSet books = service.getBooks();
            while (books.next()) {
                String id = books.getString("livro_id");
                InputStream img = new FileInputStream("images/" + id + ".jpg");
                service.updateBook(id, img);
                img.close();
            }
            service.closeConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getBooks() throws SQLException {
        return this.statement.executeQuery("SELECT * FROM livros");
    }

    public void updateBook(String ID, InputStream image) throws SQLException {
        this.updateStatement.setBlob(1, image);
        this.updateStatement.setString(2, ID);
        this.updateStatement.execute();
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
