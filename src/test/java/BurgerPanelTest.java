import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

public class BurgerPanelTest {

    @Test
    public void testCreateBurgerPanel() {
        JPanel panel = BurgerPanel.createBurgerPanel();

        // Check if the panel is not null
        assertNotNull(panel, "Panel should not be null");

        // Check if the panel uses BorderLayout
        assertInstanceOf(BorderLayout.class, panel.getLayout(), "Panel should use BorderLayout");

        // Check if the account label panel is added to the NORTH position
        assertNotNull(panel.getComponent(0), "Account label panel should be added to the NORTH position");
        assertInstanceOf(JPanel.class, panel.getComponent(0), "Account label panel should be an instance of JPanel");

        // Check if the title label is added
        JLabel titleLabel = (JLabel) panel.getComponent(1);
        assertEquals("Customize your Burger", titleLabel.getText(), "Title label text should be 'Customize your Burger'");
        assertEquals(SwingConstants.CENTER, titleLabel.getHorizontalAlignment(), "Title label should be centered");

        // Check if the checkboxes panel is added to the CENTER position
        JPanel checkBoxPanel = (JPanel) panel.getComponent(2);
        assertNotNull(checkBoxPanel, "Checkbox panel should be added to the CENTER position");
        assertInstanceOf(BoxLayout.class, checkBoxPanel.getLayout(), "Checkbox panel should use BoxLayout");

        // Verify checkboxes in the panel
        JCheckBox lettuceCheckBox = (JCheckBox) checkBoxPanel.getComponent(0);
        JCheckBox ketchupCheckBox = (JCheckBox) checkBoxPanel.getComponent(1);
        JCheckBox cheeseCheckBox = (JCheckBox) checkBoxPanel.getComponent(2);
        JCheckBox onionsCheckBox = (JCheckBox) checkBoxPanel.getComponent(3);

        assertEquals("Lettuce", lettuceCheckBox.getText(), "First checkbox should be for Lettuce");
        assertEquals("Ketchup", ketchupCheckBox.getText(), "Second checkbox should be for Ketchup");
        assertEquals("Cheese", cheeseCheckBox.getText(), "Third checkbox should be for Cheese");
        assertEquals("Onions", onionsCheckBox.getText(), "Fourth checkbox should be for Onions");

        // Check if the nutrition panel is added to the EAST position
        JPanel nutritionPanel = (JPanel) panel.getComponent(3);
        assertNotNull(nutritionPanel, "Nutrition panel should be added to the EAST position");

        // Check if the button panel is added to the SOUTH position
        JPanel buttonPanel = (JPanel) panel.getComponent(4);
        assertNotNull(buttonPanel, "Button panel should be added to the SOUTH position");
        assertInstanceOf(BoxLayout.class, buttonPanel.getLayout(), "Button panel should use BoxLayout");

        // Verify buttons in the panel
        JButton addButton = (JButton) buttonPanel.getComponent(0);
        JButton backButton = (JButton) buttonPanel.getComponent(2);

        assertEquals("Add to Order", addButton.getText(), "First button should be 'Add to Order'");
        assertEquals("Back to Menu", backButton.getText(), "Second button should be 'Back to Menu'");
    }

    @Test
    public void testCreateBurgerInfoPanel() {
        JPanel nutritionPanel = BurgerPanel.createBurgerInfoPanel();

        // Check if the nutrition panel is not null
        assertNotNull(nutritionPanel, "Nutrition panel should not be null");

        // Check if the nutrition panel uses BoxLayout
        assertInstanceOf(BoxLayout.class, nutritionPanel.getLayout(), "Nutrition panel should use BoxLayout");

        // Verify labels in the panel
        JLabel caloriesLabel = (JLabel) nutritionPanel.getComponent(0);
        JLabel fatLabel = (JLabel) nutritionPanel.getComponent(1);
        JLabel carbsLabel = (JLabel) nutritionPanel.getComponent(2);
        JLabel proteinLabel = (JLabel) nutritionPanel.getComponent(3);

        assertEquals("Calories: 390", caloriesLabel.getText(), "First label should be 'Calories: 390'");
        assertEquals("Fat: 20g", fatLabel.getText(), "Second label should be 'Fat: 20g'");
        assertEquals("Carbohydrates: 32g", carbsLabel.getText(), "Third label should be 'Carbohydrates: 32g'");
        assertEquals("Protein: 22g", proteinLabel.getText(), "Fourth label should be 'Protein: 22g'");
    }
}
