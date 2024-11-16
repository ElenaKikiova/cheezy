package cheezy.App;

import cheezy.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Filters extends JPanel {

    private final JComboBox<String> manufacturerProvCodeDropdown;
    private final JComboBox<String> categoryTypeDropdown;
    private final JComboBox<String> milkTypeDropdown;
    private final JComboBox<String> calculationTypeDropdown;
    private final JButton filterButton;

    public Filters() {
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Select Manufacturer Province Code:"));
        manufacturerProvCodeDropdown = new JComboBox<>(Constants.MANUFACTURER_PROV_CODES);
        add(manufacturerProvCodeDropdown);

        add(new JLabel("Select Cheese Category:"));
        categoryTypeDropdown = new JComboBox<>(Constants.CATEGORY_TYPES);
        add(categoryTypeDropdown);

        add(new JLabel("Select Milk Type:"));
        milkTypeDropdown = new JComboBox<>(Constants.MILK_TYPES);
        add(milkTypeDropdown);

        add(new JLabel("Select Calculation Type:"));
        calculationTypeDropdown = new JComboBox<>(Constants.CALCULATION_TYPES);
        add(calculationTypeDropdown);

        filterButton = new JButton("Apply filters");
        add(filterButton);
    }

    // Add an action listener for the filter button
    public void addFilterListener(ActionListener listener) {
        filterButton.addActionListener(listener);
    }

    // Methods to retrieve selected filter values
    public String getSelectedProvince() {
        return (String) manufacturerProvCodeDropdown.getSelectedItem();
    }

    public String getSelectedCategory() {
        return (String) categoryTypeDropdown.getSelectedItem();
    }

    public String getSelectedMilkType() {
        return (String) milkTypeDropdown.getSelectedItem();
    }

    public String getSelectedCalculation() {
        return (String) calculationTypeDropdown.getSelectedItem();
    }
}
