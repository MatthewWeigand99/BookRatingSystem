import java.util.Scanner;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookManager {
    //Database Admin Variables
    static final String DB_URL = "jdbc:mysql://localhost:3306/testDB";
    static final String USER = "root";
    static final String PASSWORD = "Mia!CompSci99";

    //Scanner inputs
    static Scanner scan = new Scanner(System.in);
    static String tableName;
    static int userInput;

    //Window variables
    final static String TITLE = "Book Manager";
    final static int WINDOW_WIDTH = 700;
    final static int WINDOW_HEIGHT = 500;

    public static void main(String[] args) {
        //createWindow();
        System.out.println("Connection test. Enter a table name: ");
        tableName = scan.nextLine();
        viewTable(tableName);
    }

    public static void createWindow() {
        JFrame window = new JFrame();

        window.setTitle(TITLE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void viewTable(String tName) {
        String query = "select id, firstName, lastName from " + tName;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                
                System.out.println(id + ", " + fName + ", " + lName);
            }
            System.out.println("Finished printing.");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
