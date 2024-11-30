import javax.swing.*;
import java.awt.*;

public class MenuPanel {
    // Menu Panel
    protected static JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add spacing around each button
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the account label panel at the top
        JPanel topPanel = new JPanel(new BorderLayout());

        // Create a label to display "Account: " and the accountLabel text
        JLabel accountInfoLabel = new JLabel("Account: " + RestaurantKiosk.accountLabel.getText());
        accountInfoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(accountInfoLabel, BorderLayout.EAST);

        // Add the account label panel to the topPanel
        topPanel.add(AccountLabelPanel.createAccountLabelPanel(), BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);

        // Menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Salad"};
        double[] itemPrices = {9.99, 11.99, 12.99, 7.99};
        String[] itemImages = {"images/Burger.jpg", "images/Pizza.jpg", "images/Pasta.jpg", "images/Salad.jpg"}; // Image file paths

        for (int i = 0; i < itemNames.length; i++) {
            JButton itemButton = createMenuItemButtonWithImage(itemNames[i], itemPrices[i], itemImages[i]);
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gridPanel.add(itemButton, gbc);
        }

        // Order History Button
        JButton orderHistoryButton = new JButton("Order History");
        gbc.gridx = 0;
        gbc.gridy = itemNames.length / 2;
        gbc.gridwidth = 2;
        gridPanel.add(orderHistoryButton, gbc);

        orderHistoryButton.addActionListener(e -> {
            if (!RestaurantKiosk.accountLabel.getText().isEmpty()) {
                String history = """
                10/25/2024
                18:34
                2 x Pizza: $23.98
                1 x Burger: $9.99
                Total: $33.97""";
                JOptionPane.showMessageDialog(panel, history, "History for " + RestaurantKiosk.accountLabel.getText(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Please log in to view your order history.", "Order History", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Order Summary Button
        JButton toOrderSummaryButton = new JButton("Order Summary");
        gbc.gridx = 0;
        gbc.gridy = itemNames.length / 2 + 1;
        gbc.gridwidth = 2;
        gridPanel.add(toOrderSummaryButton, gbc);

        toOrderSummaryButton.addActionListener(e -> {
            OrderSummaryPanel.updateOrderSummary();
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Order Summary");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });

        // Back to Home Button
        JButton backToHomeButton = new JButton("Back to Home");
        gbc.gridx = 0;
        gbc.gridy = itemNames.length / 2 + 2;
        gbc.gridwidth = 2;
        gridPanel.add(backToHomeButton, gbc);

        backToHomeButton.addActionListener(e -> {
            // Navigate back to the home screen
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Home");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });

        // Add the grid panel to the center of the main panel
        panel.add(gridPanel, BorderLayout.CENTER);

        return panel;
    }
    // Create Menu Item Button with Resized Image
    protected static JButton createMenuItemButtonWithImage(String itemName, double itemPrice, String imagePath) {
        JButton button = new JButton("<html><center>" + itemName + "<br>$" + itemPrice + "</center></html>");

        // Resize the image to a uniform size
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        button.addActionListener(e -> {
            RestaurantKiosk.currentItemName = itemName;
            RestaurantKiosk.currentItemPrice = itemPrice;
            switch (itemName) {
                case "Burger":
                    RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Customize Burger");
                    break;
                case "Pizza":
                    RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Customize Pizza");
                    break;
                case "Pasta":
                    RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Customize Pasta");
                    break;
                case "Salad":
                    RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Customize Salad");
                    break;
            }
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        return button;
    }
}
