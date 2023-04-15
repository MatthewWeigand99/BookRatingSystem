package BookRater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class TableViewer {
    String dbURL;
    String user;
    String password;

    public TableViewer(String dbURL, String user, String password) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
    }

    public void viewTable(String tName) {
        String query = "select * from " + tName;

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            StringBuffer sb = new StringBuffer();
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("book_title");
                String author = rs.getString("book_author");
                String genre = rs.getString("book_genre");
                String length = rs.getString("book_length");
                float rating = rs.getFloat("book_rating");
                    
                System.out.println(id + " " + title + " " + author + " " + genre + " " + length + " " + rating);
                sb.append(title + ", " + author + ", " + genre + ", " + length + ", " + Float.toString(rating) + "\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            System.out.println("Finished printing.");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}