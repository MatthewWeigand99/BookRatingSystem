package BookRater;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddBookPanel extends JPanel{

    String dbURL;
    String user;
    String password;
    String table;
    
    JLabel bookTitleLabel;
    JTextField bookTitleTF;
    JLabel bookAuthorLabel;
    JTextField bookAuthorTF;
    JLabel bookGenreLabel;
    JTextField bookGenreTF;
    private BookLengthPanel blp;

    JLabel bookRatingLabel;
    JTextField bookRatingTF;

    JLabel emptyLabel;
    JButton addButton;

    public AddBookPanel(String dbURL, String user, String password, String table) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
        this.table = table;

        bookTitleLabel = new JLabel("Insert book title:");
        bookTitleTF = new JTextField(40);

        bookAuthorLabel = new JLabel("Insert book author:");
        bookAuthorTF = new JTextField(20);

        bookGenreLabel = new JLabel("Insert book genre:");
        bookGenreTF = new JTextField(20);

        blp = new BookLengthPanel();

        bookRatingLabel = new JLabel("Insert book rating:");
        bookRatingTF = new JTextField(10);

        emptyLabel = new JLabel();

        addButton = new JButton("Add Book");
        addButton.addActionListener(new AddButtonListener());

        setBorder(BorderFactory.createTitledBorder("Add Book"));
        setLayout(new GridLayout(6, 2, 20, 20));

        add(bookTitleLabel);
        add(bookTitleTF);

        add(bookAuthorLabel);
        add(bookAuthorTF);

        add(bookGenreLabel);
        add(bookGenreTF);

        add(bookRatingLabel);
        add(bookRatingTF);

        //add(emptyLabel);
        add(blp);
        add(addButton);
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            addBook(bookTitleTF.getText(), 
                    bookAuthorTF.getText(), 
                    bookGenreTF.getText(),
                    blp.getLength(),
                    Float.parseFloat(bookRatingTF.getText()));

            bookTitleTF.setText("");
            bookAuthorTF.setText("");
            bookGenreTF.setText("");
            bookRatingTF.setText("");
        }
    }

    public void addBook(String title, String author, String genre, String length, Float rating) {
        String query = "INSERT INTO " + table + " (book_title, book_author, book_genre, book_length, book_rating)" +
                       " VALUES (\'" + 
                       title + "\', \'" +
                       author + "\', \'" +
                       genre + "\', \'" +
                       length + "\', " +
                       rating + ")";

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(query);
            System.out.println("Book added");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
