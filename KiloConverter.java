import javax.swing.*;

public class KiloConverter extends JFrame {
    private JPanel panel;
    private JLabel messageLabel;
    private JTextField kiloTextField;
    private JButton calcButton;
    private final int W_WIDTH = 700;
    private final int W_HEIGHT = 500;

    public KiloConverter() {
        setTitle("Kilo Converter");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();
        add(panel);

        setVisible(true);
    }

    private void buildPanel() {
        messageLabel = new JLabel("Enter a distance in kilometers");
        kiloTextField = new JTextField(10);
        calcButton = new JButton("Calculate");

        panel = new JPanel();

        panel.add(messageLabel);
        panel.add(kiloTextField);
        panel.add(calcButton);
    }

    public static void main(String[] args) {
        new KiloConverter();
    }
}
