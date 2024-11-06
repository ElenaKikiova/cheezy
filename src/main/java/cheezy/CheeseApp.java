package cheezy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheeseApp extends JFrame {

    private JComboBox<String> manufacturerProvCodeDropdown;
    private JComboBox<String> categoryTypeDropdown;
    private JComboBox<String> milkTypeDropdown;
    private JComboBox<String> calculationTypeDropdown;
    private JTextArea resultArea;

    public CheeseApp() {
        setTitle("Cheese Filter Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize filters panel
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // ManufacturerProvCode filter
        filterPanel.add(new JLabel("Select Manufacturer Province Code:"));
        manufacturerProvCodeDropdown = new JComboBox<>(new String[] {"AB", "NS", "BC", "ON"}); // example values
        filterPanel.add(manufacturerProvCodeDropdown);

        // CategoryTypeEn filter
        filterPanel.add(new JLabel("Select Cheese Category:"));
        categoryTypeDropdown = new JComboBox<>(new String[] {"Fresh Cheese", "Semi-soft Cheese", "Hard Cheese"});
        filterPanel.add(categoryTypeDropdown);

        // MilkTypeEn filter
        filterPanel.add(new JLabel("Select Milk Type:"));
        milkTypeDropdown = new JComboBox<>(new String[] {"Cow", "Ewe", "Goat"});
        filterPanel.add(milkTypeDropdown);

        // Calculation Type filter
        filterPanel.add(new JLabel("Select Calculation Type:"));
        calculationTypeDropdown = new JComboBox<>(new String[] {
                "Average Moisture Percent", "Organic Cheese Percentage"
        });
        filterPanel.add(calculationTypeDropdown);

        add(filterPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        buttonPanel.add(calculateButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get selected values from dropdowns
            String selectedProvince = (String) manufacturerProvCodeDropdown.getSelectedItem();
            String selectedCategory = (String) categoryTypeDropdown.getSelectedItem();
            String selectedMilkType = (String) milkTypeDropdown.getSelectedItem();
            String selectedCalculation = (String) calculationTypeDropdown.getSelectedItem();

            // Perform calculation based on selection (placeholder logic)
            String result;
            if (selectedCalculation.equals("Average Moisture Percent")) {
                result = "Average moisture for " + selectedCategory + " (" + selectedMilkType + ") in " +
                        selectedProvince + ": 42%"; // Placeholder result
            } else {
                result = "Organic cheese percentage for " + selectedCategory + " in " +
                        selectedProvince + ": 15%"; // Placeholder result
            }

            // Display result
            resultArea.setText(result);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheeseApp app = new CheeseApp();
            app.setVisible(true);
        });
    }
}
