package BookRater;

import java.awt.GridLayout;

import javax.swing.*;

public class BookLengthPanel extends JPanel {

    //Three lengths - Short (<300), Medium(300 - 600), and Long (>600).
    private JRadioButton shortButton;
    private JRadioButton mediumButton;
    private JRadioButton longButton;
    private ButtonGroup bg;

    public BookLengthPanel () {
        setLayout(new GridLayout(3, 1));

        shortButton = new JRadioButton("Short", true);
        mediumButton = new JRadioButton("Medium");
        longButton = new JRadioButton("Long");

        bg = new ButtonGroup();
        bg.add(shortButton);
        bg.add(mediumButton);
        bg.add(longButton);

        setBorder(BorderFactory.createTitledBorder("Book Length"));

        add(shortButton);
        add(mediumButton);
        add(longButton);
    }

    public String getLength() {
        if (mediumButton.isSelected())
            return "Medium";
        if (longButton.isSelected())
            return "Long";
        else
            return "Short";
    }
    
}
