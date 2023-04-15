package BookRater;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BookRater extends JFrame {
    //Database Variables
    private final String DB_URL = "jdbc:mysql://localhost:3306/book_manager";
    private final String USER = "root";
    private final String PASSWORD = "Mia!CompSci99";
    private final String TABLE = "book_list_user";

    private final String TITLE = "Book Rating System";
    private final  int WINDOW_WIDTH = 600;
    private final  int WINDOW_HEIGHT = 400;

    private JPanel panel;
    private GreetingPanel gp;
    private AddBookPanel abp;
    private JButton exitButton;
    private JButton showTableButton;

    public BookRater() {
        setTitle(TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        gp = new GreetingPanel();
        abp = new AddBookPanel();

        buildPanel();

        add(abp, BorderLayout.CENTER);
        add(gp, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void buildPanel() {
        showTableButton = new JButton("Show table");
        exitButton = new JButton("Exit");

        showTableButton.addActionListener(new ShowTableButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        panel = new JPanel();
        panel.add(showTableButton);
        panel.add(exitButton);
    }

    private class ExitButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
                System.exit(0);
        }
    }

    private class ShowTableButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            TableViewer tv = new TableViewer(DB_URL, USER, PASSWORD);
            tv.viewTable(TABLE);
        }
    }

    public static void main(String[] args) {
        new BookRater();
    }
}
