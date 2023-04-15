package BookRater;

import javax.swing.*;

public class AddBookPanel extends JPanel{
    
    JLabel addBookLabel;

    public AddBookPanel() {
        addBookLabel = new JLabel("Insert finished book:");
        add(addBookLabel);
    }
}
