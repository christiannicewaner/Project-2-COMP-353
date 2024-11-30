import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RestaurantKiosk {
    private JPanel mainPanel;

    public RestaurantKiosk() {
        // Setup Main Panel with CardLayout
        mainPanel.setLayout(new CardLayout());

        // Initialize Panels
        JPanel homePanel = createHomePanel();
        JPanel menuPanel = createMenuPanel();
        JPanel orderSummaryPanel = createOrderSummaryPanel();

        // Add Panels to Main Panel
        mainPanel.add(homePanel, "Home");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(orderSummaryPanel, "Order Summary");

        // Show Home Panel Initially
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Home");
    }


    public static class Order {
        // final does not make it immutable
        private final List<MenuItem> items;

        public Order() {
            items = new ArrayList<>();
        }

        public void addItem(String name, double price) {
            items.add(new MenuItem(name, price));
        }

        public void printOrder() {
            for (MenuItem item : items) {
                System.out.println(item);
            }
        }
    }

    private JPanel createHomePanel() {
        JPanel buttonPanel = new JPanel();
        JPanel panel = new JPanel(new BorderLayout());
        JButton toMenuButton = new JButton("Continue as Guest");
        JButton emailLoginButton = new JButton("Login with Email");
        JButton phoneLoginButton = new JButton(("Login with Phone"));

        buttonPanel.add(toMenuButton);
        buttonPanel.add(emailLoginButton);
        buttonPanel.add(phoneLoginButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        toMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Menu");
            }
        });

        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Example menu items
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Salad"};
        String[] imagePaths = {"Burger.jpg", "Pizza.jpg", "Pasta.jpg", "Salad.jpg"};

        for (int i = 0; i < itemNames.length; i++) {
            JPanel itemPanel = createMenuItemPanel(itemNames[i], imagePaths[i]);
            gbc.gridx = i % 2;  // Adjust for two columns
            gbc.gridy = i / 2;
            panel.add(itemPanel, gbc);
        }

        JButton toOrderSummaryButton = new JButton("Order Summary");
        gbc.gridx = 0;
        gbc.gridy = itemNames.length / 2 + 1;
        gbc.gridwidth = 2;
        panel.add(toOrderSummaryButton, gbc);

        toOrderSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Order Summary");
            }
        });

        return panel;
    }

    private JPanel createMenuItemPanel(String itemName, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Load and resize image
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);  // Resize image to fit panel
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel imageLabel = new JLabel(resizedIcon);
        panel.add(imageLabel);

        // Add button
        JButton button = new JButton("Order " + itemName);
        panel.add(button);

        return panel;
    }


    private JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel summaryLabel = new JLabel("Order Summary", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Menu");
            }
        });

        panel.add(summaryLabel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant Kiosk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new RestaurantKiosk().mainPanel);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }
}
