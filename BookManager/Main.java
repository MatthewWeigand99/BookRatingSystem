import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    // Main frame variables
    private String appName = "Book Rater";
    private int appHeight = 600;
    private int appWidth = 800;

    private JPanel panel;
    private JButton exitButton;

    public Main() {
        setTitle(appName);
        setSize(appWidth, appHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        buildPanel();

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void buildPanel() {
        exitButton = new JButton("Exit");

        exitButton.addActionListener(new ExitButtonListener());

        panel = new JPanel();
        panel.add(exitButton);
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