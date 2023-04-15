package BookRater;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RatingsPanel extends JPanel {

    String dbURL;
    String user;
    String password;
    String table;

    private JButton ratingsButton; 

    public RatingsPanel (String dbURL, String user, String password, String table) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
        this.table = table;

        ratingsButton = new JButton("Average Rating");

        ratingsButton.addActionListener(new RatingsButtonListener());

        setLayout(new GridLayout(1, 1));
        add(ratingsButton);
    }

    private class RatingsButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Average rating: " + String.format("%.2f",getAvgRating(table)) + "%");
        }
    }

    //Method to get average rating of all books in the list
    public float getAvgRating(String table) {
        String query = "SELECT AVG(book_rating) FROM " + table;

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return rs.getFloat(1);
            }
            
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }
}
