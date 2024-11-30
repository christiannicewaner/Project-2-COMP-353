import javax.swing.*;
import java.awt.*;

public class OrderSummaryPanel {
    // Order Summary Panel
    protected static JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea summaryTextArea = new JTextArea(10, 30);
        summaryTextArea.setEditable(false);
        panel.add(new JScrollPane(summaryTextArea), BorderLayout.CENTER);

        panel.add(AccountLabelPanel.createAccountLabelPanel(), BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        // Cancel Order button
        JButton cancelButton = new JButton("Cancel Order");
        cancelButton.addActionListener(e -> {
            // Reset order and go to home screen
            RestaurantKiosk.currentOrder.clear();
            RestaurantKiosk.accountLabel.setText("");
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Home");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        buttonsPanel.add(cancelButton);

        // Add some space between buttons
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.addActionListener(e -> {
            // Go to menu screen
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Menu");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        buttonsPanel.add(backToMenuButton);

        // Add some space between buttons
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Proceed to Payment button
        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.addActionListener(e -> {
            // Go to payment screen
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Payment");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        buttonsPanel.add(proceedButton);

        // Add buttons panel to the main panel
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }
    // Update the order summary
    public static void updateOrderSummary() {
        // Ensure that orderSummaryPanel refers to the correct panel
        JPanel orderSummaryPanel = (JPanel) RestaurantKiosk.mainPanel.getComponent(6); // Adjust the index to match the position of the order summary panel
        JScrollPane scrollPane = (JScrollPane) orderSummaryPanel.getComponent(0);
        JTextArea summaryTextArea = (JTextArea) scrollPane.getViewport().getView();
        StringBuilder summary = new StringBuilder();
        for (String item : RestaurantKiosk.currentOrder.getItems()) {
            summary.append(item).append(": $").append(RestaurantKiosk.currentItemPrice).append("\n");
        }
        summary.append("Total: $").append(RestaurantKiosk.currentOrder.getTotalPrice());
        summaryTextArea.setText(summary.toString());
    }
}
