import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    // MySQL Database Variables
    private final String DB_URL = "jdbc:mysql://localhost:3306/book_manager";
    private final String USER = "root";
    private final String PASSWORD = "Mia!CompSci99";
    private final String TABLE = "book_list_user";

    // Main frame variables
    private final String APP_TITLE = "Book Rater";
    private int appHeight = 800;
    private int appWidth = 1000;

    private JPanel panel;
    private JPanel centerPanel;

    private JButton exitButton;
    private JButton showTableButton;

    private AddBookPanel abp;
    private RemovalPanel rp;
    private RatingsPanel rating;

    public Main() {
        setTitle(APP_TITLE);
        setSize(appWidth, appHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        abp = new AddBookPanel(DB_URL, USER, PASSWORD, TABLE);
        rp = new RemovalPanel(DB_URL, USER, PASSWORD, TABLE);
        rating = new RatingsPanel(DB_URL, USER, PASSWORD, TABLE);

        centerPanel.add(abp);
        centerPanel.add(rp);
        centerPanel.add(rating);

        buildPanel();

        add(centerPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void buildPanel() {
        exitButton = new JButton("Exit");
        showTableButton = new JButton("Show Table");

        exitButton.addActionListener(new ExitButtonListener());
        showTableButton.addActionListener(new ShowTableButtonListener());

        panel = new JPanel();
        panel.add(showTableButton);
        panel.add(exitButton);
    }

    private class ShowTableButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Show table");
            TableViewer tv = new TableViewer(DB_URL, USER, PASSWORD);
            tv.viewTable(TABLE);
        }
    }

    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}