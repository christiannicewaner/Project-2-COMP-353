import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantKiosk {
    private JPanel mainPanel;
    private JPanel homePanel;
    private JPanel menuPanel;
    private JPanel orderSummaryPanel;
    private JButton toMenuButton;
    private JButton emailLoginButton;
    private JButton phoneLoginButton;
    private JButton toOrderSummaryButton;
    private JButton backButton;

    public RestaurantKiosk() {
        // Setup Main Panel with CardLayout
        mainPanel.setLayout(new CardLayout());

        // Initialize Panels
        homePanel = createHomePanel();
        menuPanel = createMenuPanel();
        orderSummaryPanel = createOrderSummaryPanel();

        // Add Panels to Main Panel
        mainPanel.add(homePanel, "Home");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(orderSummaryPanel, "Order Summary");

        // Show Home Panel Initially
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Home");
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel emailPanel = new JPanel(new BorderLayout());
        JPanel phonePanel = new JPanel(new BorderLayout());
        toMenuButton = new JButton("Go to Menu");
        emailLoginButton = new JButton("Login with Email");
        phoneLoginButton = new JButton(("Login with Phone"));

        toMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Menu");
            }
        });

        panel.add(toMenuButton, BorderLayout.SOUTH);
        panel.add(emailPanel, BorderLayout.CENTER);
        emailPanel.add(emailLoginButton, BorderLayout.SOUTH);
        emailPanel.add(phonePanel, BorderLayout.CENTER);
        phonePanel.add(phoneLoginButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Example menu items
        String[] items = {"Burger", "Pizza", "Pasta", "Salad"};
        int row = 0;

        for (String item : items) {
            gbc.gridx = 0;
            gbc.gridy = row;
            panel.add(new JLabel(item), gbc);
            row++;
        }

        toOrderSummaryButton = new JButton("View Order Summary");
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(toOrderSummaryButton, gbc);

        toOrderSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Order Summary");
            }
        });

        return panel;
    }

    private JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel summaryLabel = new JLabel("Order Summary", SwingConstants.CENTER);
        backButton = new JButton("Back to Menu");

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
        frame.pack();
        frame.setSize(1280,720);
        frame.setVisible(true);
    }
}
