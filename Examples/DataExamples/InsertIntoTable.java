import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.awt.event.*;

public class InsertIntoTable extends JPanel {
    //Database variabels
    String dbURL;
    String user;
    String password;

    JLabel messageLabel;
    JTextField idText;
    JTextField firstNameText;
    JTextField lastNameText;
    JButton insertButton;

    public InsertIntoTable(String dbURL, String user, String password) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;

        messageLabel = new JLabel("Insert information into a table:");
        idText = new JTextField("Enter ID", 10);
        firstNameText = new JTextField("First Name", 10);
        lastNameText = new JTextField("Last Name", 10);
        insertButton = new JButton("Insert values");

        insertButton.addActionListener(new InsertButtonListener());

        add(messageLabel);
        add(idText);
        add(firstNameText);
        add(lastNameText);
        add(insertButton);
    }

    private class InsertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            insertIntoTable(Integer.parseInt(idText.getText()), firstNameText.getText(), lastNameText.getText());
            System.out.println("Values inserted.");
        }
    }

    public void insertIntoTable(int id, String firstName, String lastName) {
        String query = "INSERT INTO TEST2 VALUES (" + id + ", \'" + firstName + "\', \'" + lastName + "\')";

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
