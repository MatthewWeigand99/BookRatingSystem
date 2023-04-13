import javax.swing.*;   //Needed for swing class
import java.awt.event.*;    //Needed for ActionListener Interface

public class KiloConverter extends JFrame {

    //Variables
    private JPanel panel;
    private JLabel messageLabel;
    private JTextField kiloTextField;
    private JButton calcButton;
    private final int W_WIDTH = 700;
    private final int W_HEIGHT = 500;

    //Constructor
    public KiloConverter() {
        //Sets window variables
        setTitle("Kilo Converter");
        setSize(W_WIDTH, W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel(); //Builds panel and adds to the frame 
        add(panel); //Adds panel to frame's content pane

        setVisible(true); //Displays window
    }

    private void buildPanel() {
        //Create display
        messageLabel = new JLabel("Enter a distance in kilometers");
        kiloTextField = new JTextField(10);
        calcButton = new JButton("Calculate");

        //Adds action listener to the button
        calcButton.addActionListener(new CalcButtonListener());

        //Adds components to panel
        panel = new JPanel();

        panel.add(messageLabel);
        panel.add(kiloTextField);
        panel.add(calcButton);
    }

    private class CalcButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            final double CONVERSION = 0.6214;
            String input;
            double miles;

            input = kiloTextField.getText();

            //DEBUGGING
            System.out.println("Reading " + input + " from the text field.");
            System.out.println("Converted value: " + Double.parseDouble(input));

            miles = CONVERSION * Double.parseDouble(input);

            JOptionPane.showMessageDialog(null, input + " kilometers is " + miles + " miles.");
            //DEBUGGING
            System.out.println("Ready for next input.");
        }
    }

    public static void main(String[] args) {
        new KiloConverter();
    }
}
