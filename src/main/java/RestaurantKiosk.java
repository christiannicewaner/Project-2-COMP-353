import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RestaurantKiosk {
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private final Order currentOrder;
    private String currentItemName;
    private double currentItemPrice;

    // Create the account label at the class level
    private final JLabel accountLabel = new JLabel("");

    // Method to create the label panel
    private JPanel createAccountLabelPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(accountLabel, BorderLayout.EAST);
        return panel;
    }

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
        mainPanel.add(createPaymentPanel(), "Payment");
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
        emailField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setFont(new Font("Serif", Font.BOLD,  48));
        panel.add(emailField, BorderLayout.CENTER);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
            if (emailField.getText().matches(emailPattern)) {
                accountLabel.setText("Account: " + emailField.getText());
                emailField.setText("");
                cardLayout.show(mainPanel, "Menu");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
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
        phoneField.setHorizontalAlignment(JTextField.CENTER);
        phoneField.setFont(new Font("Serif", Font.BOLD,48));
        panel.add(phoneField, BorderLayout.CENTER);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String phonePattern = "^\\d{3}-\\d{3}-\\d{4}$";
            if (phoneField.getText().matches(phonePattern)) {
                accountLabel.setText("Account: " + phoneField.getText());
                phoneField.setText("");
                cardLayout.show(mainPanel, "Menu");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
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
            currentOrder.clear();
            accountLabel.setText("");
            cardLayout.show(mainPanel, "Menu");
        });

        // Add action listener to the "Login with Email" button
        emailLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "Email Login"));

        // Add action listener to the "Login with Phone" button
        phoneLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "Phone Login"));

        // Add action listener to the "Help" button
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Help is on the way."));

        // Add buttons to the button panel
        buttonPanel.add(guestButton);
        buttonPanel.add(emailLoginButton);
        buttonPanel.add(phoneLoginButton);

        // Add the button panel to the center of the main panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Image label
        ImageIcon mcburgerIcon = new ImageIcon(new ImageIcon("src/main/java/McBurger.jpg").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
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

    // Menu Panel
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add spacing around each button
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the account label panel at the top
        JPanel topPanel = new JPanel(new BorderLayout());

        // Create a label to display "Account: " and the accountLabel text
        JLabel accountInfoLabel = new JLabel("Account: " + accountLabel.getText());
        accountInfoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(accountInfoLabel, BorderLayout.EAST);

        // Add the account label panel to the topPanel
        topPanel.add(createAccountLabelPanel(), BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);

        // Menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Salad"};
        double[] itemPrices = {9.99, 11.99, 12.99, 7.99};
        String[] itemImages = {"src/main/java/Burger.jpg", "src/main/java/Pizza.jpg", "src/main/java/Pasta.jpg", "src/main/java/Salad.jpg"}; // Image file paths

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
            if (!accountLabel.getText().isEmpty()) {
                String history = """
                10/25/2024
                18:34
                2 x Pizza: $23.98
                1 x Burger: $9.99
                Total: $33.97""";
                JOptionPane.showMessageDialog(panel, history, "Order History for " + accountLabel.getText(), JOptionPane.INFORMATION_MESSAGE);
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
            updateOrderSummary();
            cardLayout.show(mainPanel, "Order Summary");
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Back to Home Button
        JButton backToHomeButton = new JButton("Back to Home");
        gbc.gridx = 0;
        gbc.gridy = itemNames.length / 2 + 2;
        gbc.gridwidth = 2;
        gridPanel.add(backToHomeButton, gbc);

        backToHomeButton.addActionListener(e -> {
            // Navigate back to the home screen
            cardLayout.show(mainPanel, "Home");
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Add the grid panel to the center of the main panel
        panel.add(gridPanel, BorderLayout.CENTER);

        return panel;
    }

    // Create Menu Item Button with Resized Image
    private JButton createMenuItemButtonWithImage(String itemName, double itemPrice, String imagePath) {
        JButton button = new JButton("<html><center>" + itemName + "<br>$" + itemPrice + "</center></html>");

        // Resize the image to a uniform size
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        button.addActionListener(e -> {
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
        });
        return button;
    }

    // Customization Panel for Burger
    private JPanel createCustomizeBurgerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(createAccountLabelPanel(), BorderLayout.NORTH);
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

        // Nutritional Information Panel
        JPanel nutritionPanel = createBurgerInfoPanel();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add to Order button
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
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
        });
        buttonPanel.add(addButton);

        // Add some space between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Menu");
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        buttonPanel.add(backButton);

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(nutritionPanel, BorderLayout.EAST); // Add the nutrition panel to the right
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        return panel;
    }


    // Create Burger Nutritional Information Panel
    private JPanel createBurgerInfoPanel() {
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS));
        nutritionPanel.setBorder(BorderFactory.createTitledBorder("Nutrition Info"));

        JLabel caloriesLabel = new JLabel("Calories: 390");
        JLabel fatLabel = new JLabel("Fat: 20g");
        JLabel carbsLabel = new JLabel("Carbohydrates: 32g");
        JLabel proteinLabel = new JLabel("Protein: 22g");

        nutritionPanel.add(caloriesLabel);
        nutritionPanel.add(fatLabel);
        nutritionPanel.add(carbsLabel);
        nutritionPanel.add(proteinLabel);

        return nutritionPanel;
    }



    // Customization Panel for Pizza
    private JPanel createCustomizePizzaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(createAccountLabelPanel(), BorderLayout.NORTH);
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

        // Nutritional Information Panel
        JPanel nutritionPanel = createPizzaInfoPanel();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add to Order button
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
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
        });
        buttonPanel.add(addButton);

        // Add some space between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Menu");
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        buttonPanel.add(backButton);

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(nutritionPanel, BorderLayout.EAST); // Add the nutrition panel to the right
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        return panel;
    }


    // Create Pizza Nutritional Information Panel
    private JPanel createPizzaInfoPanel() {
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS));
        nutritionPanel.setBorder(BorderFactory.createTitledBorder("Nutrition Info"));

        JLabel caloriesLabel = new JLabel("Calories: 285");
        JLabel fatLabel = new JLabel("Fat: 10g");
        JLabel carbsLabel = new JLabel("Carbohydrates: 36g");
        JLabel proteinLabel = new JLabel("Protein: 12g");

        nutritionPanel.add(caloriesLabel);
        nutritionPanel.add(fatLabel);
        nutritionPanel.add(carbsLabel);
        nutritionPanel.add(proteinLabel);

        return nutritionPanel;
    }

    // Customization Panel for Pasta
    private JPanel createCustomizePastaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(createAccountLabelPanel(), BorderLayout.NORTH);
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

        // Nutritional Information Panel
        JPanel nutritionPanel = createPastaInfoPanel();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add to Order button
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
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
        });
        buttonPanel.add(addButton);

        // Add some space between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Menu");
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        buttonPanel.add(backButton);

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(nutritionPanel, BorderLayout.EAST); // Add the nutrition panel to the right
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        return panel;
    }


    // Create Pasta Nutritional Information Panel
    private JPanel createPastaInfoPanel() {
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS));
        nutritionPanel.setBorder(BorderFactory.createTitledBorder("Nutrition Info"));

        JLabel caloriesLabel = new JLabel("Calories: 434");
        JLabel fatLabel = new JLabel("Fat: 12g");
        JLabel carbsLabel = new JLabel("Carbohydrates: 47g");
        JLabel proteinLabel = new JLabel("Protein: 31g");

        nutritionPanel.add(caloriesLabel);
        nutritionPanel.add(fatLabel);
        nutritionPanel.add(carbsLabel);
        nutritionPanel.add(proteinLabel);

        return nutritionPanel;
    }

    // Customization Panel for Salad
    private JPanel createCustomizeSaladPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(createAccountLabelPanel(), BorderLayout.NORTH);
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

        // Nutritional Information Panel
        JPanel nutritionPanel = createSaladInfoPanel();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add to Order button
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
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
        });
        buttonPanel.add(addButton);

        // Add some space between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Menu");
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        buttonPanel.add(backButton);

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(nutritionPanel, BorderLayout.EAST); // Add the nutrition panel to the right
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        return panel;
    }


    // Create Salad Nutritional Information Panel
    private JPanel createSaladInfoPanel() {
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BoxLayout(nutritionPanel, BoxLayout.Y_AXIS));
        nutritionPanel.setBorder(BorderFactory.createTitledBorder("Nutrition Info"));

        JLabel caloriesLabel = new JLabel("Calories: 491");
        JLabel fatLabel = new JLabel("Fat: 19g");
        JLabel carbsLabel = new JLabel("Carbohydrates: 42g");
        JLabel proteinLabel = new JLabel("Protein: 39g");

        nutritionPanel.add(caloriesLabel);
        nutritionPanel.add(fatLabel);
        nutritionPanel.add(carbsLabel);
        nutritionPanel.add(proteinLabel);

        return nutritionPanel;
    }

    // Order Summary Panel
    private JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea summaryTextArea = new JTextArea(10, 30);
        summaryTextArea.setEditable(false);
        panel.add(new JScrollPane(summaryTextArea), BorderLayout.CENTER);

        panel.add(createAccountLabelPanel(), BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        // Cancel Order button
        JButton cancelButton = new JButton("Cancel Order");
        cancelButton.addActionListener(e -> {
            // Reset order and go to home screen
            currentOrder.clear();
            accountLabel.setText("");
            cardLayout.show(mainPanel, "Home");
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        buttonsPanel.add(cancelButton);

        // Add some space between buttons
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.addActionListener(e -> {
            // Go to menu screen
            cardLayout.show(mainPanel, "Menu");
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        buttonsPanel.add(backToMenuButton);

        // Add some space between buttons
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Proceed to Payment button
        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.addActionListener(e -> {
            // Go to payment screen
            cardLayout.show(mainPanel, "Payment");
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        buttonsPanel.add(proceedButton);

        // Add buttons panel to the main panel
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }


    // Payment Panel
    private JPanel createPaymentPanel() {
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
                currentOrder.clear();
                accountLabel.setText("");
                cardNumberField.setText("");
                mainPanel.add(createProgressScreenPanel(), "Progress Screen");
                cardLayout.show(mainPanel, "Progress Screen");
                mainPanel.revalidate();
                mainPanel.repaint();

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
            cardLayout.show(mainPanel, "Order Summary");
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridx = 0; // Keep alignment
        gbc.anchor = GridBagConstraints.PAGE_END;
        inputPanel.add(backButton, gbc);

        panel.add(inputPanel, BorderLayout.CENTER);

        return panel;
    }

    private boolean isValidCardNumber(String cardNumber) {
        String regex = "^(\\d{4}-){3}\\d{4}$|^\\d{16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.matches();
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

    // Progress Screen Panel
    private JPanel createProgressScreenPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel progressLabel = new JLabel("Processing your order, please wait...", SwingConstants.CENTER);
        progressLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(progressLabel, BorderLayout.NORTH);

        // Create Progress Bar
        JProgressBar progressBar = new JProgressBar(0, 3);
        progressBar.setStringPainted(true);
        progressBar.setString("Sending Order");
        panel.add(progressBar, BorderLayout.CENTER);

        // Countdown label
        JLabel countdownLabel = new JLabel("", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(countdownLabel, BorderLayout.SOUTH);

        // SwingWorker to update progress
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(6666);
                    publish(i + 1);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int progress = chunks.getLast();
                progressBar.setValue(progress);
                switch (progress) {
                    case 1:
                        progressBar.setString("Order Received");

                        break;
                    case 2:
                        progressBar.setString("Preparing Order");
                        progressBar.setFont(new Font("Serif", Font.BOLD, 24));
                        break;
                    case 3:
                        progressBar.setString("Ready for Pickup!");
                        progressBar.setFont(new Font("Serif", Font.BOLD, 36));
                        startCountdown(countdownLabel);
                        panel.remove(progressLabel);
                        break;
                }
            }

            private void startCountdown(JLabel label) {
                new Thread(() -> {
                    for (int i = 3; i > 0; i--) {
                        try {
                            int finalI = i;
                            SwingUtilities.invokeLater(() -> label.setText("Returning to home in " + finalI + "..."));
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            //noinspection CallToPrintStackTrace
                            e.printStackTrace();
                        }
                    }
                    SwingUtilities.invokeLater(() -> cardLayout.show(mainPanel, "Home"));
                }).start();
            }
        };
        worker.execute();
        return panel;
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
