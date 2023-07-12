import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class ViewTable {
    String dbURL;
    String user;
    String password;

    public ViewTable(String dbURL, String user, String password) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
    }

    public void viewTable(String tName) {
        String query = "select id, firstName, lastName from " + tName;

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            StringBuffer sb = new StringBuffer();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                    
                System.out.println(id + ", " + fName + ", " + lName);
                sb.append(Integer.toString(id) + "," + fName + ", " + lName + "\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            System.out.println("Finished printing.");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid table name.");
            System.out.println(e);
        }
    }
}
