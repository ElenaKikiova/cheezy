package cheezy.App;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import cheezy.Cheese;
import cheezy.CheeseData;

public class CheeseApp extends JFrame {

    private final Filters filterPanel;
    private final Table tablePanel;
    private final JTextArea resultArea;  // New area for displaying calculation result

    public CheeseApp() {
        setTitle("Cheezy");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize and add components
        filterPanel = new Filters();
        tablePanel = new Table();

        // Panel for the calculation result area
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        // Panel to hold filter and result area
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(filterPanel, BorderLayout.NORTH); // Filters at the top
        topPanel.add(resultPanel, BorderLayout.SOUTH);  // Result box at the bottom

        // Add components to the layout
        add(topPanel, BorderLayout.NORTH);  // Add filter and result panel at the top
        add(tablePanel, BorderLayout.CENTER); // Table goes in the center

        // Load the cheese data from the csv
        loadCheeseDataFromCSV();

        // Add action listener for filter button
        filterPanel.addFilterListener(e -> applyFilterAndUpdateTable());
    }

    private void loadCheeseDataFromCSV() {
        String csvFilePath = System.getProperty("user.dir") + "/src/main/java/cheezy/resources/18.csv"; // Change the path to where your 18.csv is located
        CheeseData.loadCheeseData(csvFilePath);
        List<Cheese> allCheeses = CheeseData.getAllCheeses();
        tablePanel.updateTable(allCheeses);
    }

    private void applyFilterAndUpdateTable() {
        // Get selected filter values
        String selectedProvince = filterPanel.getSelectedProvince();
        String selectedCategory = filterPanel.getSelectedCategory();
        String selectedMilkType = filterPanel.getSelectedMilkType();
        String selectedCalculation = filterPanel.getSelectedCalculation();

        // Fetch filtered data from CheeseData (using the filter values)
        List<Cheese> filteredCheeses = filterCheeses(selectedProvince, selectedCategory, selectedMilkType);

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

        // Display the result in the text area (not a popup)
        resultArea.setText(result);
    }

    private List<Cheese> filterCheeses(String province, String category, String milkType) {
        List<Cheese> allCheeses = CheeseData.getAllCheeses();

        // Apply filtering logic based on the selected values
        return allCheeses.stream()
                .filter(cheese ->
                        (province.equals("All") || cheese.getManufacturerProvCode().equals(province)) &&
                                (category.equals("All") || cheese.getCategoryTypeEn().equals(category)) &&
                                (milkType.equals("All") || cheese.getMilkTypeEn().equals(milkType))
                )
                .collect(Collectors.toList());
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
