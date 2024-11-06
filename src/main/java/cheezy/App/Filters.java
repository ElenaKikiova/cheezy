package cheezy.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Filters extends JPanel {

    private JComboBox<String> manufacturerProvCodeDropdown;
    private JComboBox<String> categoryTypeDropdown;
    private JComboBox<String> milkTypeDropdown;
    private JComboBox<String> calculationTypeDropdown;
    private JButton filterButton;

    public Filters() {
        setLayout(new GridLayout(5, 2, 10, 10));

        // ManufacturerProvCode filter
        add(new JLabel("Select Manufacturer Province Code:"));
        manufacturerProvCodeDropdown = new JComboBox<>(new String[]{"AB", "NS", "BC", "ON"});
        add(manufacturerProvCodeDropdown);

        // CategoryTypeEn filter
        add(new JLabel("Select Cheese Category:"));
        categoryTypeDropdown = new JComboBox<>(new String[]{"Fresh Cheese", "Semi-soft Cheese", "Hard Cheese"});
        add(categoryTypeDropdown);

        // MilkTypeEn filter
        add(new JLabel("Select Milk Type:"));
        milkTypeDropdown = new JComboBox<>(new String[]{"Cow", "Ewe", "Goat"});
        add(milkTypeDropdown);

        // Calculation Type filter
        add(new JLabel("Select Calculation Type:"));
        calculationTypeDropdown = new JComboBox<>(new String[]{"Average Moisture Percent", "Organic Cheese Percentage"});
        add(calculationTypeDropdown);

        // Filter button
        filterButton = new JButton("Filter");
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
