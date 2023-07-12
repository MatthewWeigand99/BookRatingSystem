import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.awt.event.*;

public class RemoveFromTable extends JPanel {
    //Database variabels
    String dbURL;
    String user;
    String password;

    JLabel messageLabel;
    JTextField firstNameText;
    JButton removeButton;

    public RemoveFromTable(String dbURL, String user, String password) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;

        messageLabel = new JLabel("Remove name from table:");
        firstNameText = new JTextField("First Name", 10);
        removeButton = new JButton("Remove row");

        removeButton.addActionListener(new RemoveButtonListener());

        add(messageLabel);
        add(firstNameText);
        add(removeButton);
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            removeFromTable(firstNameText.getText());
            System.out.println("Values removed.");
        }
    }

    public void removeFromTable(String firstName) {
        String query = "DELETE FROM TEST2 WHERE firstName = \'" + firstName + "\';";

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
