import javax.swing.*;
import java.awt.*;

public class PizzaPanel {
    // Customization Panel for Pizza
    public static JPanel createPizzaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        panel.add(AccountLabelPanel.createAccountLabelPanel(), BorderLayout.NORTH);
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
            StringBuilder customizations = new StringBuilder(RestaurantKiosk.currentItemName);
            if (pepperoniCheckBox.isSelected()) customizations.append(" + Pepperoni");
            if (mushroomsCheckBox.isSelected()) customizations.append(" + Mushrooms");
            if (olivesCheckBox.isSelected()) customizations.append(" + Olives");
            if (onionsCheckBox.isSelected()) customizations.append(" + Onions");

            RestaurantKiosk.currentOrder.addItem(customizations.toString(), RestaurantKiosk.currentItemPrice);
            pepperoniCheckBox.setSelected(false);
            mushroomsCheckBox.setSelected(false);
            olivesCheckBox.setSelected(false);
            onionsCheckBox.setSelected(false);
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Menu");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        buttonPanel.add(addButton);

        // Add some space between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            RestaurantKiosk.cardLayout.show(RestaurantKiosk.mainPanel, "Menu");
            RestaurantKiosk.mainPanel.revalidate();
            RestaurantKiosk.mainPanel.repaint();
        });
        buttonPanel.add(backButton);

        panel.add(checkBoxPanel, BorderLayout.CENTER);
        panel.add(nutritionPanel, BorderLayout.EAST); // Add the nutrition panel to the right
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        return panel;
    }
    // Create Pizza Nutritional Information Panel
    public static JPanel createPizzaInfoPanel() {
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
}
