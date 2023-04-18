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
    private final  int WINDOW_WIDTH = 1200;
    private final  int WINDOW_HEIGHT = 800;

    private JPanel panel;
    private JPanel centerPanel;
    private GreetingPanel gp;
    private AddBookPanel abp;
    private RatingsPanel rp;
    private GenreRatingPanel grp;
    private RemoveBookPanel rbp;
    private JButton exitButton;
    private JButton showTableButton;

    private JRadioButton showAddPanel;
    private JRadioButton showRemovePanel;

    public BookRater() {
        setTitle(TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        gp = new GreetingPanel();
        showAddPanel = new JRadioButton("Add book");
        showRemovePanel = new JRadioButton("Remove book");
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        abp = new AddBookPanel(DB_URL, USER, PASSWORD, TABLE);
        rbp = new RemoveBookPanel(DB_URL, USER, PASSWORD, TABLE);
        rp = new RatingsPanel(DB_URL, USER, PASSWORD, TABLE);
        grp = new GenreRatingPanel(DB_URL, USER, PASSWORD, TABLE);
        
        buildCenterPanel();
        buildPanel();

        add(centerPanel, BorderLayout.CENTER);
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

    private void buildCenterPanel() {
        abp.setVisible(false);
        rbp.setVisible(false);
        showAddPanel.addActionListener(new ShowAddButtonListener());
        showRemovePanel.addActionListener(new ShowRemoveButtonListener());

        centerPanel.add(showAddPanel);
        centerPanel.add(showRemovePanel);
        centerPanel.add(abp);
        centerPanel.add(rbp);
        centerPanel.add(rp);
        centerPanel.add(grp);
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

    private class ShowAddButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if (showAddPanel.isSelected()) {
                abp.setVisible(true);
            }

            else {
                abp.setVisible(false);
            }
        }
    }

    private class ShowRemoveButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if (showRemovePanel.isSelected()) {
                rbp.setVisible(true);
            }

            else {
                rbp.setVisible(false);
            }
        }
    }

    public static void main(String[] args) {
        new BookRater();
    }
}
