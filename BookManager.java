import java.util.Scanner;
import javax.swing.*;

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
        createWindow();
    }

    public static void createWindow() {
        JFrame window = new JFrame();

        window.setTitle(TITLE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
