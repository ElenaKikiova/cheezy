package cheezy.App;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import cheezy.Cheese;

public class CheeseApp extends JFrame {

    private Filters filterPanel;
    private Table tablePanel;

    public CheeseApp() {
        setTitle("Cheese Filter Application");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize and add components
        filterPanel = new Filters();
        tablePanel = new Table();

        add(filterPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        // Add action listener for the filter button
        filterPanel.addFilterListener(e -> applyFilterAndUpdateTable());
    }

    private void applyFilterAndUpdateTable() {
        // Get selected filter values
        String selectedProvince = filterPanel.getSelectedProvince();
        String selectedCategory = filterPanel.getSelectedCategory();
        String selectedMilkType = filterPanel.getSelectedMilkType();
        String selectedCalculation = filterPanel.getSelectedCalculation();

        // Fetch filtered data (placeholder logic)
        List<Cheese> filteredCheeses = getFilteredCheeses(selectedProvince, selectedCategory, selectedMilkType);

        // Update the table with filtered data
        tablePanel.updateTable(filteredCheeses);

        // Perform and display calculation if needed
        String result;
        if ("Average Moisture Percent".equals(selectedCalculation)) {
            double avgMoisture = calculateAverageMoisture(filteredCheeses);
            result = "Average moisture: " + avgMoisture + "%";
        } else {
            double organicPercentage = calculateOrganicPercentage(filteredCheeses);
            result = "Organic cheese percentage: " + organicPercentage + "%";
        }
        JOptionPane.showMessageDialog(this, result, "Calculation Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private List<Cheese> getFilteredCheeses(String province, String category, String milkType) {
        // Placeholder data for all fields
        List<Cheese> cheeses = new ArrayList<>();
        cheeses.add(new Cheese("001", province, "Handmade", 38.0, "Mild", "Creamy", true,
                category, milkType, "Pasteurized", "Natural", "Cheddar", "High"));
        cheeses.add(new Cheese("002", province, "Industrial", 45.0, "Sharp", "Aged", false,
                category, milkType, "Unpasteurized", "Waxed", "Gouda", "Medium"));
        // Add more data or connect to a real data source

        return cheeses;
    }


    private double calculateAverageMoisture(List<Cheese> cheeses) {
        return cheeses.stream().mapToDouble(Cheese::getMoisturePercent).average().orElse(0);
    }

    private double calculateOrganicPercentage(List<Cheese> cheeses) {
        long organicCount = cheeses.stream().filter(Cheese::isOrganic).count();
        return (double) organicCount / cheeses.size() * 100;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheeseApp app = new CheeseApp();
            app.setVisible(true);
        });
    }
}
