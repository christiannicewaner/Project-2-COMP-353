import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel jsFirstName;
    private JTextField jsTextField;
    private JButton jsButton1;
    private JPanel MainPanel;

    public Main() {
        setContentPane(MainPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        // could replace this with lambda, won't for now
        jsButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = jsTextField.getText();
                jsFirstName.setText(firstName);
                JOptionPane.showMessageDialog(Main.this, "Welcome: " + firstName + "!");
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
