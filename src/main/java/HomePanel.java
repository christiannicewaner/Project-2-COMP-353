import javax.swing.*;
import java.awt.*;

public class HomePanel {
    // Home Panel
    public static JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Center-align buttons
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create title label
        JLabel titleLabel = new JLabel("Welcome to McBurger!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36)); // Set font size and style

        // Add title label to the top of the panel
        panel.add(titleLabel, BorderLayout.NORTH);

        JButton guestButton = new JButton("Continue as Guest");
        JButton emailLoginButton = new JButton("Login with Email");
        JButton phoneLoginButton = new JButton("Login with Phone");
        JButton helpButton = new JButton("Help");

        // Center-align each button
        guestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        phoneLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listener to the "Continue as Guest" button
        guestButton.addActionListener(e -> {
            RestaurantKiosk.currentOrder.clear();
            RestaurantKiosk.accountLabel.setText("");
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Menu");
        });

        // Add action listener to the "Login with Email" button
        emailLoginButton.addActionListener(e -> RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Email Login"));

        // Add action listener to the "Login with Phone" button
        phoneLoginButton.addActionListener(e -> RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Phone Login"));

        // Add action listener to the "Help" button
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Help is on the way."));

        // Add buttons to the button panel
        buttonPanel.add(guestButton);
        buttonPanel.add(emailLoginButton);
        buttonPanel.add(phoneLoginButton);

        // Add the button panel to the center of the main panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Image label
        ImageIcon mcburgerIcon = new ImageIcon(new ImageIcon("images/McBurger.jpg").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
        JLabel mcburgerLabel = new JLabel(mcburgerIcon);
        mcburgerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the image label to the bottom center of the panel
        JPanel imagePanel = new JPanel();
        imagePanel.add(mcburgerLabel);
        imagePanel.setAlignmentY(-1.5F);
        panel.add(imagePanel, BorderLayout.PAGE_START);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(helpButton, BorderLayout.EAST); // Add the "Help" button to the bottom right
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
}
