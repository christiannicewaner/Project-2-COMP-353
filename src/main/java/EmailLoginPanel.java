import javax.swing.*;
import java.awt.*;

public class EmailLoginPanel {

    // Email Login Panel
    protected static JPanel createEmailLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Enter your Email Address", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextField emailField = new JTextField();
        emailField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setFont(new Font("Serif", Font.BOLD, 48));
        panel.add(emailField, BorderLayout.CENTER);

        // Creating a panel for the buttons using GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ensuring buttons are centered and have space between them
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
            if (emailField.getText().matches(emailPattern)) {
                RestaurantKiosk.accountLabel.setText("Account: " + emailField.getText());
                emailField.setText("");
                RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Menu");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(loginButton, gbc);

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> {
            // Implement the action to go back to the home screen
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Home");
        });
        gbc.gridx = 1;
        buttonPanel.add(backButton, gbc);

        // Adding the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
}
