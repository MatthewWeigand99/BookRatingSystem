package BookRater;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GreetingPanel extends JPanel {

    JLabel greeting;

    public GreetingPanel(){
        greeting = new JLabel("Welcome!");
        add(greeting);
    }
}
