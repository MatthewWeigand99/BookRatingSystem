import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class RemovalPanel extends JPanel {
    String dbURL;
    String user;
    String password;
    String table;

    JButton removeButton;
    JTextField removeTF;
    JLabel removeLabel;
    JLabel empty;

    public RemovalPanel(String dbURL, String user, String password, String table) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
        this.table = table;

        removeLabel = new JLabel("Remove book: ");
        removeTF = new JTextField(40);
        removeButton = new JButton("Remove");
        empty = new JLabel();

        removeButton.addActionListener(new RemoveButton());

        setLayout(new GridLayout(2, 2));
        setBorder(BorderFactory.createTitledBorder("Remove book"));

        add(removeLabel);
        add(removeTF);
        add(empty);
        add(removeButton);
    }

    private class RemoveButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            removeBook(removeTF.getText());
            removeTF.setText("");
        }
    }

    public void removeBook(String title) {
        String query = "DELETE FROM " + table + " WHERE book_title LIKE \'%" + title + "%\';";

        try(Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {
            
                stmt.executeUpdate(query);
                System.out.println("Removed book");
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}
