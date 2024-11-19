package cheezy.App;

import cheezy.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Filters extends JPanel {

    private final JTextField manufacturerProvCodeInput;
    private final JTextField categoryTypeInpput;
    private final JTextField milkTypeInput;
    private final JComboBox<String> calculationTypeDropdown;
    private final JButton filterButton;

    public Filters() {
        setLayout(new GridLayout(5, 2, 10, 10));

        // ManufacturerProvCode filter
        add(new JLabel("Select Manufacturer Province Code:"));
        manufacturerProvCodeInput = new JTextField();
        add(manufacturerProvCodeInput);

        // CategoryTypeEn filter
        add(new JLabel("Select Cheese Category:"));
        categoryTypeInpput = new JTextField();
        // Add more categories
        add(categoryTypeInpput);

        // MilkTypeEn filter
        add(new JLabel("Select Milk Type:"));
        milkTypeInput = new JTextField();
        // Add more milk types
        add(milkTypeInput);

        // Calculation Type filter
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
        return manufacturerProvCodeInput.getText();
    }

    public String getSelectedCategory() {
        return categoryTypeInpput.getText();
    }

    public String getSelectedMilkType() {
        return milkTypeInput.getText();
    }

    public String getSelectedCalculation() {
        return (String) calculationTypeDropdown.getSelectedItem();
    }
}
