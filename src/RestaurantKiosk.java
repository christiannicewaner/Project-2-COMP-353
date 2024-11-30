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
    private String currentItemName;
    private double currentItemPrice;

    public RestaurantKiosk() {
        // Setup Main Panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize Order
        currentOrder = new Order();

        // Initialize Panels
        JPanel homePanel = createHomePanel();
        JPanel menuPanel = createMenuPanel();
        JPanel customizeBurgerPanel = createCustomizeBurgerPanel();
        JPanel customizePizzaPanel = createCustomizePizzaPanel();
        JPanel customizePastaPanel = createCustomizePastaPanel();
        JPanel customizeSaladPanel = createCustomizeSaladPanel();
        JPanel orderSummaryPanel = createOrderSummaryPanel();
        JPanel emailLoginPanel = createEmailLoginPanel();
        JPanel phoneLoginPanel = createPhoneLoginPanel();

        // Add Panels to Main Panel
        mainPanel.add(homePanel, "Home");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(customizeBurgerPanel, "Customize Burger");
        mainPanel.add(customizePizzaPanel, "Customize Pizza");
        mainPanel.add(customizePastaPanel, "Customize Pasta");
        mainPanel.add(customizeSaladPanel, "Customize Salad");
        mainPanel.add(orderSummaryPanel, "Order Summary");
        mainPanel.add(emailLoginPanel, "Email Login");
        mainPanel.add(phoneLoginPanel, "Phone Login");

        // Show Home Panel Initially
        cardLayout.show(mainPanel, "Home");
    }


    // Email Login Panel
    private JPanel createEmailLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Enter your Email Address", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextField emailField = new JTextField();
        panel.add(emailField, BorderLayout.CENTER);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
                if (emailField.getText().matches(emailPattern)) {
                    cardLayout.show(mainPanel, "Menu");
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton, BorderLayout.SOUTH);

        return panel;
    }

    // Phone Login Panel
    private JPanel createPhoneLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Enter your Phone Number (XXX-XXX-XXXX)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextField phoneField = new JTextField();
        panel.add(phoneField, BorderLayout.CENTER);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phonePattern = "^\\d{3}-\\d{3}-\\d{4}$";
                if (phoneField.getText().matches(phonePattern)) {
                    cardLayout.show(mainPanel, "Menu");
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton, BorderLayout.SOUTH);

        return panel;
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

        // Add action listener to the "Login with Email" button
        emailLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Email Login");
            }
        });

        // Add action listener to the "Login with Phone" button
        phoneLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Phone Login");
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
                currentItemName = itemName;
                currentItemPrice = itemPrice;
                switch (itemName) {
                    case "Burger":
                        cardLayout.show(mainPanel, "Customize Burger");
                        break;
                    case "Pizza":
                        cardLayout.show(mainPanel, "Customize Pizza");
                        break;
                    case "Pasta":
                        cardLayout.show(mainPanel, "Customize Pasta");
                        break;
                    case "Salad":
                        cardLayout.show(mainPanel, "Customize Salad");
                        break;
                }
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        return button;
    }

    // Customization Panel for Burger
    private JPanel createCustomizeBurgerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Customize your Burger", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add checkboxes for customization
        JCheckBox lettuceCheckBox = new JCheckBox("Lettuce");
        JCheckBox ketchupCheckBox = new JCheckBox("Ketchup");
        JCheckBox cheeseCheckBox = new JCheckBox("Cheese");
        JCheckBox onionsCheckBox = new JCheckBox("Onions");
        checkBoxPanel.add(lettuceCheckBox);
        checkBoxPanel.add(ketchupCheckBox);
        checkBoxPanel.add(cheeseCheckBox);
        checkBoxPanel.add(onionsCheckBox);

        // Done Customizing button
        JButton doneButton = new JButton("Done Customizing");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder customizations = new StringBuilder(currentItemName);
                if (lettuceCheckBox.isSelected()) customizations.append(" + Lettuce");
                if (ketchupCheckBox.isSelected()) customizations.append(" + Ketchup");
                if (cheeseCheckBox.isSelected()) customizations.append(" + Cheese");
                if (onionsCheckBox.isSelected()) customizations.append(" + Onions");

                currentOrder.addItem(customizations.toString(), currentItemPrice);
                lettuceCheckBox.setSelected(false);
                ketchupCheckBox.setSelected(false);
                cheeseCheckBox.setSelected(false);
                onionsCheckBox.setSelected(false);
                cardLayout.show(mainPanel, "Menu");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(doneButton, BorderLayout.SOUTH);


        return panel;
    }


    // Customization Panel for Pizza
    private JPanel createCustomizePizzaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Customize your Pizza", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add checkboxes for customization
        JCheckBox pepperoniCheckBox = new JCheckBox("Pepperoni");
        JCheckBox mushroomsCheckBox = new JCheckBox("Mushrooms");
        JCheckBox olivesCheckBox = new JCheckBox("Olives");
        JCheckBox onionsCheckBox = new JCheckBox("Onions");
        checkBoxPanel.add(pepperoniCheckBox);
        checkBoxPanel.add(mushroomsCheckBox);
        checkBoxPanel.add(olivesCheckBox);
        checkBoxPanel.add(onionsCheckBox);

        // Done Customizing button
        JButton doneButton = new JButton("Done Customizing");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder customizations = new StringBuilder(currentItemName);
                if (pepperoniCheckBox.isSelected()) customizations.append(" + Pepperoni");
                if (mushroomsCheckBox.isSelected()) customizations.append(" + Mushrooms");
                if (olivesCheckBox.isSelected()) customizations.append(" + Olives");
                if (onionsCheckBox.isSelected()) customizations.append(" + Onions");

                currentOrder.addItem(customizations.toString(), currentItemPrice);
                pepperoniCheckBox.setSelected(false);
                mushroomsCheckBox.setSelected(false);
                olivesCheckBox.setSelected(false);
                onionsCheckBox.setSelected(false);
                cardLayout.show(mainPanel, "Menu");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(doneButton, BorderLayout.SOUTH);

        return panel;
    }

    // Customization Panel for Pasta
    private JPanel createCustomizePastaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Customize your Pasta", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add checkboxes for customization
        JCheckBox sauceCheckBox = new JCheckBox("Extra Sauce");
        JCheckBox cheeseCheckBox = new JCheckBox("Extra Cheese");
        JCheckBox garlicCheckBox = new JCheckBox("Garlic");
        JCheckBox mushroomsCheckBox = new JCheckBox("Mushrooms");
        checkBoxPanel.add(sauceCheckBox);
        checkBoxPanel.add(cheeseCheckBox);
        checkBoxPanel.add(garlicCheckBox);
        checkBoxPanel.add(mushroomsCheckBox);

        // Done Customizing button
        JButton doneButton = new JButton("Done Customizing");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder customizations = new StringBuilder(currentItemName);
                if (sauceCheckBox.isSelected()) customizations.append(" + Extra Sauce");
                if (cheeseCheckBox.isSelected()) customizations.append(" + Extra Cheese");
                if (garlicCheckBox.isSelected()) customizations.append(" + Garlic");
                if (mushroomsCheckBox.isSelected()) customizations.append(" + Mushrooms");

                currentOrder.addItem(customizations.toString(), currentItemPrice);
                sauceCheckBox.setSelected(false);
                cheeseCheckBox.setSelected(false);
                garlicCheckBox.setSelected(false);
                mushroomsCheckBox.setSelected(false);
                cardLayout.show(mainPanel, "Menu");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(doneButton, BorderLayout.SOUTH);

        return panel;
    }

    // Customization Panel for Salad
    private JPanel createCustomizeSaladPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Customize your Salad", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add checkboxes for customization
        JCheckBox croutonsCheckBox = new JCheckBox("Croutons");
        JCheckBox dressingCheckBox = new JCheckBox("Dressing");
        JCheckBox cheeseCheckBox = new JCheckBox("Cheese");
        JCheckBox baconBitsCheckBox = new JCheckBox("Bacon Bits");
        checkBoxPanel.add(croutonsCheckBox);
        checkBoxPanel.add(dressingCheckBox);
        checkBoxPanel.add(cheeseCheckBox);
        checkBoxPanel.add(baconBitsCheckBox);

        // Done Customizing button
        JButton doneButton = new JButton("Done Customizing");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder customizations = new StringBuilder(currentItemName);
                if (croutonsCheckBox.isSelected()) customizations.append(" + Croutons");
                if (dressingCheckBox.isSelected()) customizations.append(" + Dressing");
                if (cheeseCheckBox.isSelected()) customizations.append(" + Cheese");
                if (baconBitsCheckBox.isSelected()) customizations.append(" + Bacon Bits");

                currentOrder.addItem(customizations.toString(), currentItemPrice);
                croutonsCheckBox.setSelected(false);
                dressingCheckBox.setSelected(false);
                cheeseCheckBox.setSelected(false);
                baconBitsCheckBox.setSelected(false);
                cardLayout.show(mainPanel, "Menu");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(doneButton, BorderLayout.SOUTH);

        return panel;
    }

    // Order Summary Panel
    private JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea summaryTextArea = new JTextArea(10, 30);
        summaryTextArea.setEditable(false);
        panel.add(new JScrollPane(summaryTextArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset order and go to home screen
                currentOrder.clear();
                cardLayout.show(mainPanel, "Home");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }


    // Update the order summary
    private void updateOrderSummary() {
        // Ensure that orderSummaryPanel refers to the correct panel
        JPanel orderSummaryPanel = (JPanel) mainPanel.getComponent(6); // Adjust the index to match the position of the order summary panel
        JScrollPane scrollPane = (JScrollPane) orderSummaryPanel.getComponent(0);
        JTextArea summaryTextArea = (JTextArea) scrollPane.getViewport().getView();
        StringBuilder summary = new StringBuilder();
        for (String item : currentOrder.getItems()) {
            summary.append(item).append(": $").append(currentItemPrice).append("\n");
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
