import javax.swing.*;
import java.awt.event.*;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/

public class BookManager extends JFrame{

    //Database Variables
    private final String DB_URL = "jdbc:mysql://localhost:3306/testDB";
    private final String USER = "root";
    private final String PASSWORD = "Mia!CompSci99";
    private String tableName;

    //Window variables
    private final String TITLE = "Book Rating System";
    private final  int WINDOW_WIDTH = 700;
    private final  int WINDOW_HEIGHT = 500;

    private JPanel panel;
    private JButton exitButton;
    private JButton showTableButton;
    private JLabel messageLabel;
    private JTextField tableNameField;

    public BookManager() {
        setTitle(TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();
        add(panel);

        setVisible(true);
    }

    private void buildPanel() {
        messageLabel = new JLabel("Please enter a table name:");
        tableNameField = new JTextField(10);
        showTableButton = new JButton("Show table");
        exitButton = new JButton("Exit");

        exitButton.addActionListener(new ExitButtonListener());
        showTableButton.addActionListener(new ShowTableButtonListener());

        panel = new JPanel();
        panel.add(messageLabel);
        panel.add(tableNameField);
        panel.add(showTableButton);
        panel.add(exitButton);
    }

    public static void main(String[] args) {
        new BookManager();
    }

    private class ExitButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
                System.exit(0);
                System.out.println("Exit.");
        }
    }

    private class ShowTableButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            tableName = tableNameField.getText();
            ViewTable vt = new ViewTable(DB_URL, USER, PASSWORD);
 
            vt.viewTable(tableName);
        }
    }
    /*
    private void viewTable (String tName) {
        String query = "select id, firstName, lastName from " + tName;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            StringBuffer sb = new StringBuffer();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                
                System.out.println(id + ", " + fName + ", " + lName);
                sb.append(Integer.toString(id) + "," + fName + ", " + lName + "\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            System.out.println("Finished printing.");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid table name.");
            System.out.println(e);
        }
    }
    */
}
