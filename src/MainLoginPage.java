import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainLoginPage extends JFrame {
    private JButton login_button;
    private JPanel login_panel;
    private JPasswordField password_field;
    private JTextField username_field;
    private JLabel password_label;
    private JLabel username_label;
    private JLabel railway_logo_img;

    MainLoginPage() {
        login_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(login_panel);
        setTitle("Welcome to Pluto Railways!");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCredentials();
            }
        });
    }

    private void checkCredentials() {
        String username, password;
        username = username_field.getText();
        password = password_field.getText();
        try {
            Connection mango_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/plutodb", "root", "root1234");
            Statement mango_statement = mango_connection.createStatement();
            ResultSet mango_resultset = mango_statement.executeQuery("select * from users");
            while (mango_resultset.next()) {
                if (username.equals(mango_resultset.getString(1)) && password.equals(mango_resultset.getString(2))) {
                    JOptionPane.showMessageDialog(login_panel, "Login Successful");
                    this.setVisible(false);
                    TicketBooking mango = new TicketBooking();
                    mango.setVisible(true);
                    return;
                }
            }
            JOptionPane.showMessageDialog(login_panel, "Please check your credentials", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}