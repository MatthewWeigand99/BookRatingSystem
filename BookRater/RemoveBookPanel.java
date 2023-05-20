package BookRater;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class RemoveBookPanel extends JPanel{
    String dbURL;
    String user;
    String password;
    String table;
    
    JButton removeButton;
    JTextField removeBookTF;
    JLabel removeBookLabel;
    JLabel emptyLabel;

    public RemoveBookPanel(String dbURL, String user, String password, String table) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
        this.table = table;

        removeBookLabel = new JLabel("Remove a book:");
        removeBookTF = new JTextField(40);
        removeButton = new JButton("Remove book");
        emptyLabel = new JLabel();

        removeButton.addActionListener(new RemoveBookButton());

        setLayout(new GridLayout(2, 2));
        setBorder(BorderFactory.createTitledBorder("Remove Book"));

        add(removeBookLabel);
        add(removeBookTF);
        add(emptyLabel);
        add(removeButton);
    }

    private class RemoveBookButton implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            removeBook(removeBookTF.getText());
            removeBookTF.setText("");
        }
    }

    public void removeBook(String title) {
        String query = "DELETE FROM " + table + " WHERE book_title LIKE \'%" + title + "%\';";

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(query);
            System.out.println("Book removed.");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
