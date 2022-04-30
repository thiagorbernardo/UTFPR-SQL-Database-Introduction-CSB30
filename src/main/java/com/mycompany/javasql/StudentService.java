package com.mycompany.javasql;

import java.sql.*;
import java.util.ArrayList;

public class StudentService {
    private final Connection connection;
    private final Statement statement;
    private final PreparedStatement createStatement;
    private final PreparedStatement updateStatement;

    public StudentService() throws SQLException {
        String url = "jdbc:mysql://localhost/university";
        String user = "root";
        String pass = "thi109032";

        this.connection = DriverManager.getConnection(url, user, pass);
        this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        this.createStatement = this.connection.prepareStatement("INSERT INTO student (ID, name, dept_name, tot_cred) VALUES (?, ?, ?, ?)");
        this.updateStatement = this.connection.prepareStatement("UPDATE student SET ID = ?, name = ?, dept_name = ?, tot_cred = ? WHERE ID = ?");
    }

    public ResultSet getStudents() throws SQLException {
        return this.statement.executeQuery("SELECT * FROM student");
    }

    public ArrayList<String> getDepartments() throws SQLException {
        ResultSet rs = this.statement.executeQuery("SELECT * FROM department");
        ArrayList<String> departments = new ArrayList<>();

        while(rs.next()){
            departments.add(rs.getString("dept_name"));
        }

        return departments;
    }

    public void createStudent(String ID, String name, String dept_name, int tot_cred) throws SQLException {
        this.createStatement.setString(1, ID);
        this.createStatement.setString(2, name);
        this.createStatement.setString(3, dept_name);
        this.createStatement.setInt(4, tot_cred);
        this.createStatement.execute();
    }

    public void updateStudent(String ID, String name, String dept_name, int tot_cred) throws SQLException {
        this.updateStatement.setString(1, ID); // set clause -> será q faz sentido? melhor bloquear via interface
        this.updateStatement.setString(5, ID); // where clause
        this.updateStatement.setString(2, name);
        this.updateStatement.setString(3, dept_name);
        this.updateStatement.setInt(4, tot_cred);
        this.updateStatement.execute();
    }

    public void deleteStudent(String ID) throws SQLException {
        this.statement.execute("DELETE FROM student WHERE ID = " + ID);
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
