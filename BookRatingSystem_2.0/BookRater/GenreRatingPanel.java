package BookRater;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenreRatingPanel extends JPanel {
    
    String dbURL;
    String user;
    String password;
    String table;

    private JButton genreRatingButton;
    private JLabel genreRatingLabel;
    private JTextField genreRatingTF;

    public GenreRatingPanel(String dbURL, String user, String password, String table) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
        this.table = table;

        genreRatingLabel = new JLabel("Enter a genre: ");
        genreRatingTF = new JTextField(25);
        genreRatingButton = new JButton("Genre Rating");

        genreRatingButton.addActionListener(new GenreRatingButtonListener());
        setLayout(new GridLayout(1, 3));

        add(genreRatingLabel);
        add(genreRatingTF);
        add(genreRatingButton);
    }

    private class GenreRatingButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Average rating for the genre " + genreRatingTF.getText() + ": " + String.format("%.2f",getGenreRating(table, genreRatingTF.getText())) + "%");
            genreRatingTF.setText("");
        }
    }

    public float getGenreRating(String table, String genre) {
        String query = "SELECT AVG(book_rating) FROM " + table + " WHERE book_genre = \'" + genre + "\';";

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
