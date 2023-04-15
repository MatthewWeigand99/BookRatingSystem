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
    private final  int WINDOW_WIDTH = 800;
    private final  int WINDOW_HEIGHT = 600;

    private JPanel panel;
    private JButton exitButton;
    private JButton showTableButton;
    private JLabel messageLabel;
    private JTextField tableNameField;
    private InsertIntoTable iit;
    private RemoveFromTable rft;

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
        iit = new InsertIntoTable(DB_URL, USER, PASSWORD);
        rft = new RemoveFromTable(DB_URL, USER, PASSWORD);
        showTableButton = new JButton("Show table");
        exitButton = new JButton("Exit");

        exitButton.addActionListener(new ExitButtonListener());
        showTableButton.addActionListener(new ShowTableButtonListener());

        panel = new JPanel();
        panel.add(messageLabel);
        panel.add(tableNameField);
        panel.add(showTableButton);
        panel.add(iit);
        panel.add(rft);
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
}
