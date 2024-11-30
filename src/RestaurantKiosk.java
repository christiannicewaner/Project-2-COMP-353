import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RestaurantKiosk {
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private final Order currentOrder;

    public RestaurantKiosk() {
        // Setup Main Panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize Order
        currentOrder = new Order();

        // Initialize Panels
        JPanel homePanel = createHomePanel();
        JPanel menuPanel = createMenuPanel();
        JPanel orderSummaryPanel = createOrderSummaryPanel();

        // Add Panels to Main Panel
        mainPanel.add(homePanel, "Home");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(orderSummaryPanel, "Order Summary");

        // Show Home Panel Initially
        cardLayout.show(mainPanel, "Home");
    }

    // Order class to keep track of items and total price
    public static class Order {
        private final List<String> items;
        private double totalPrice;

        public Order() {
            items = new ArrayList<>();
            totalPrice = 0.0;
        }

        public void addItem(String name, double price) {
            items.add(name);
            totalPrice += price;
        }

        public List<String> getItems() {
            return items;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void clear() {
            items.clear();
            totalPrice = 0.0;
        }
    }

    // Home Panel
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create title label
        JLabel titleLabel = new JLabel("Welcome to McBurger!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36)); // Set font size and style

        // Add title label to the top of the panel
        panel.add(titleLabel, BorderLayout.NORTH);

        JButton toMenuButton = new JButton("Continue as Guest");
        JButton emailLoginButton = new JButton("Login with Email");
        JButton phoneLoginButton = new JButton("Login with Phone");

        // Add action listener to the "Continue as Guest" button
        toMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu");
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(toMenuButton);
        buttonPanel.add(emailLoginButton);
        buttonPanel.add(phoneLoginButton);

        // Center the buttons in the main panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    // Menu Panel
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Salad"};
        double[] itemPrices = {9.99, 11.99, 12.99, 7.99};

        for (int i = 0; i < itemNames.length; i++) {
            JButton itemButton = createMenuItemButton(itemNames[i], itemPrices[i]);
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            panel.add(itemButton, gbc);
        }

        JButton toOrderSummaryButton = new JButton("Order Summary");
        gbc.gridx = 0;
        gbc.gridy = itemNames.length / 2 + 1;
        gbc.gridwidth = 2;
        panel.add(toOrderSummaryButton, gbc);

        toOrderSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrderSummary();
                cardLayout.show(mainPanel, "Order Summary");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        return panel;
    }

    // Create Menu Item Button
    private JButton createMenuItemButton(String itemName, double itemPrice) {
        JButton button = new JButton(itemName + " - $" + itemPrice);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentOrder.addItem(itemName, itemPrice);
            }
        });
        return button;
    }

    // Order Summary Panel
    private JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea summaryTextArea = new JTextArea(10, 30);
        summaryTextArea.setEditable(false);
        panel.add(new JScrollPane(summaryTextArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    // Update the order summary
    private void updateOrderSummary() {
        JPanel orderSummaryPanel = (JPanel) mainPanel.getComponent(2);
        JScrollPane scrollPane = (JScrollPane) orderSummaryPanel.getComponent(0);
        JTextArea summaryTextArea = (JTextArea) scrollPane.getViewport().getView();
        StringBuilder summary = new StringBuilder();
        for (String item : currentOrder.getItems()) {
            summary.append(item).append("\n");
        }
        summary.append("Total: $").append(currentOrder.getTotalPrice());
        summaryTextArea.setText(summary.toString());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant Kiosk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RestaurantKiosk kiosk = new RestaurantKiosk();
        frame.setContentPane(kiosk.mainPanel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
