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
        JPanel languageSelectionPanel = createLanguageSelectionPanel();

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
        mainPanel.add(languageSelectionPanel, "Language Selection");

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
                    accountLabel.setText("Account: " + emailField.getText());
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
                    accountLabel.setText("Account: " + phoneField.getText());
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
        JButton changeLanguageButton = new JButton("Change Language");

        // Center-align each button
        guestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        phoneLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listener to the "Continue as Guest" button
        guestButton.addActionListener(new ActionListener() {
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

        // Add action listener to the "Change Language" button
        changeLanguageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Language Selection");
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(guestButton);
        buttonPanel.add(emailLoginButton);
        buttonPanel.add(phoneLoginButton);

        // Add the button panel to the center of the main panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Image label
        ImageIcon mcburgerIcon = new ImageIcon(new ImageIcon("src/McBurger.jpg").getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH));
        JLabel mcburgerLabel = new JLabel(mcburgerIcon);
        mcburgerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the image label to the bottom center of the panel
        JPanel imagePanel = new JPanel();
        imagePanel.add(mcburgerLabel);
        imagePanel.setAlignmentY(-1.5F);
        panel.add(imagePanel, BorderLayout.PAGE_START);

        // Add the "Change Language" button to the bottom left
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(changeLanguageButton, BorderLayout.WEST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Language Selection Panel
    private JPanel createLanguageSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create title label
        JLabel titleLabel = new JLabel("Choose a Language", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36)); // Set font size and style
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create language buttons
        JButton frenchButton = new JButton("French");
        JButton germanButton = new JButton("German");
        JButton spanishButton = new JButton("Spanish");
        JButton backButton = new JButton("Back");

        // Center-align each button
        frenchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        germanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        spanishButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listeners to language buttons
        frenchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Home");
            }
        });
        germanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Home");
            }
        });
        spanishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Home");
            }
        });

        // Add action listener to back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Home");
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(frenchButton);
        buttonPanel.add(germanButton);
        buttonPanel.add(spanishButton);

        // Add the button panel to the center of the main panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around buttons
        centerPanel.add(buttonPanel, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Add the back button to the bottom left
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(backButton, BorderLayout.WEST);
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
        topPanel.add(createAccountLabelPanel(), BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);

        // Menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Salad"};
        double[] itemPrices = {9.99, 11.99, 12.99, 7.99};
        String[] itemImages = {"src//Burger.jpg", "src//Pizza.jpg", "src//Pasta.jpg", "src//Salad.jpg"}; // Image file paths

        for (int i = 0; i < itemNames.length; i++) {
            JButton itemButton = createMenuItemButtonWithImage(itemNames[i], itemPrices[i], itemImages[i]);
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gridPanel.add(itemButton, gbc);
        }

        JButton toOrderSummaryButton = new JButton("Order Summary");
        gbc.gridx = 0;
        gbc.gridy = itemNames.length / 2 + 1;
        gbc.gridwidth = 2;
        gridPanel.add(toOrderSummaryButton, gbc);

        toOrderSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrderSummary();
                cardLayout.show(mainPanel, "Order Summary");
                mainPanel.revalidate();
                mainPanel.repaint();
            }
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

        panel.add(createAccountLabelPanel(), BorderLayout.NORTH);
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
