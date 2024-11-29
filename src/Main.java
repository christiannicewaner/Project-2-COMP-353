import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// couple of to-do's:
// menu options:
    // customizable like no lettuce, etc.
    // nutritional information
    // special offers and discounts
    // call for help button
    // order history after user has logged in
// secure payment options: faux encryption with random encryption function for card numbers
// order update bar that counts down in real time to different stages
    // started, in-progress, ready for pickup!
// support for multiple languages
    // ask for language and route menus to a translation api or something
// print receipt before beginning order status screen
    // there is a make receipt website that could be pretty convincing.
    // maybe an api that can create it based on values given?
// Loyalty program login should be an option on first screen, but guest also available
    // should log in with email or phone number
    // should also have a favorites tab if logged in
// a bunch of error handling, null checks, input checks, etc.
// easy order cancellation process, always available
// add to card, view cart options at any time
// card: number, expiration date
// transaction status in a string
    // processPayment()
    // validatePayment()
    // generateReceipt()
// order system
    // orderID int
    // addItem(
    // removeItem()
    // confirmOrder(
    // orderStatus()
// check part II for more if needed
public class Main extends JFrame {
    private JLabel account;
    private JTextField emailTextField;
    private JButton languageButton;
    private JPanel mainPanel;
    private JButton emailLoginButton;
    private JButton phoneLoginButton;
    private JTextField phoneTextField;

    public Main() {
        setContentPane(mainPanel);
        setTitle("Restaurant Kiosk");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        // could replace this with lambda, won't for now
        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                //cardLayout.show(mainPanel, "Language Panel");
                // make this or other class needed to show dialog box with language options we can pick
            }
        });
        emailLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                if (Objects.equals(email, "burgerLover72@gmail.com")) {
                    account.setText("Profile: Bob Burger");
                    JOptionPane.showMessageDialog(Main.this, "Welcome: Bob!");
                }
                else {
                    JOptionPane.showMessageDialog(Main.this, "Account under '" + email + "' not found!");
                }
            }
        });
        phoneLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = phoneTextField.getText();
                if(Objects.equals(number, "937-999-9999")) {
                    account.setText("Profile: Bob Burger");
                    JOptionPane.showMessageDialog(Main.this, "Welcome: Bob!");
                }
                else {
                    JOptionPane.showMessageDialog(Main.this, "Account under '" + number + "' not found!");
                }
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
