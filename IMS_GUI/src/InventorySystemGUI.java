import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventorySystemGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel contentLabel1;
    private JLabel contentLabel2;
    private JButton signUpButton;
    private JButton loginButton;
    private String signUpUsername;
    private String signUpPassword;
    private User[] user;
    private User[] newUser;
    private int newUserCount;

    public InventorySystemGUI() {
        user = new User[1];
        user[0] = new Admin("demo", "demo123");

        setTitle("SamLam's Inventory Management System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Welcome to SamLam's Inventory Management System");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel1 = new JLabel("Hello!");
        contentLabel1.setHorizontalAlignment(JLabel.CENTER);
        contentLabel2 = new JLabel("Please choose a feature");
        contentLabel2.setHorizontalAlignment(JLabel.CENTER);
        signUpButton = new JButton("Sign Up");
        loginButton = new JButton("Log In");

        JPanel titlePanel = new JPanel(new GridLayout(4, 2));
        titlePanel.add(titleLabel);
        titlePanel.add(contentLabel1);
        titlePanel.add(contentLabel2);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(signUpButton);
        buttonPanel.add(loginButton);
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validInput = false;
                do {
                    signUpUsername = JOptionPane.showInputDialog(null, "Enter username:");
                    signUpPassword = JOptionPane.showInputDialog(null, "Enter password:");

                    if (signUpUsername == null || signUpPassword == null) {
                        return; // User cancelled sign-up.
                    }
                    if (signUpUsername.isEmpty() || signUpPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Username and password cannot be null or empty. Please try again.");
                    } else {
                        validInput = true;
                    }
                } while (!validInput);

                if (newUser == null) {
                    newUser = new User[1];
                } else {
                    User[] tempArray = new User[newUser.length + 1];
                    System.arraycopy(newUser, 0, tempArray, 0, newUser.length);
                    newUser = tempArray;
                }
                newUser[newUserCount] = new Admin(signUpUsername, signUpPassword);
                newUserCount++;
                JOptionPane.showMessageDialog(null, "Sign Up successful for username: " + signUpUsername);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(null, "Enter username:");
                String password = JOptionPane.showInputDialog(null, "Enter password:");

                if (username == null || password == null) {
                    return; // User cancelled login.
                }
                boolean validLogin = false;
                for (int i = 0; i < user.length; i++) {
                    if (username.equals(user[i].getUserID()) && password.equals(user[i].getUserPassword())) {
                        validLogin = true;
                        break;
                    }
                }
                if (!validLogin && newUser != null) {
                    for (int i = 0; i < newUser.length; i++) {
                        if (username.equals(newUser[i].getUserID()) && password.equals(newUser[i].getUserPassword())) {
                            validLogin = true;
                            break;
                        }
                    }
                }
                if (validLogin) {
                    JOptionPane.showMessageDialog(null, "Log In successful for username: " + username);
                    SwingUtilities.invokeLater(MenuGUI::new);
                } else {
                    JOptionPane.showMessageDialog(null, "Log In failed. Invalid username or password.");
                }
            }
        });
        setSize(400, 200);
        setVisible(true);
    }
}
