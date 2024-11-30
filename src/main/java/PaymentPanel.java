import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentPanel {
    // Payment Panel
    public static JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Enter Card Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(titleLabel, BorderLayout.CENTER);

        panel.add(northPanel, BorderLayout.NORTH);

        // Using GridBagLayout to center components
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10); // padding
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField(20);

        inputPanel.add(cardNumberLabel, gbc);
        inputPanel.add(cardNumberField, gbc);

        // Pay Button
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(e -> {
            String cardNumber = cardNumberField.getText();
            if (isValidCardNumber(cardNumber)) {
                String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
                // Handle payment process
                JOptionPane.showMessageDialog(panel, "Payment processed for card ending in: " + lastFourDigits);
                RestaurantKiosk.currentOrder.clear();
                RestaurantKiosk.accountLabel.setText("");
                cardNumberField.setText("");
                RestaurantKiosk.mainPanel.add(ProgressPanel.createProgressScreenPanel(), "Progress Screen");
                RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Progress Screen");
                RestaurantKiosk.mainPanel.revalidate();
                RestaurantKiosk.mainPanel.repaint();

            } else {
                JOptionPane.showMessageDialog(panel, "Invalid card number. Please enter a valid credit card number.");
            }
        });

        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        inputPanel.add(payButton, gbc);

        // Back to Order Summary Button
        JButton backButton = new JButton("Back to Order Summary");
        backButton.addActionListener(e -> {
            // Show the order summary screen
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Order Summary");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });

        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridx = 0; // Keep alignment
        gbc.anchor = GridBagConstraints.PAGE_END;
        inputPanel.add(backButton, gbc);

        panel.add(inputPanel, BorderLayout.CENTER);

        return panel;
    }
    public static boolean isValidCardNumber(String cardNumber) {
        String regex = "^(\\d{4}-){3}\\d{4}$|^\\d{16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.matches();
    }
}
