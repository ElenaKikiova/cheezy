package cheezy.App;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import cheezy.Cheese;
import cheezy.CheeseData;
import cheezy.CheeseJob;
import org.apache.log4j.BasicConfigurator;

public class CheeseApp extends JFrame {

    private final Filters filterPanel;
    private final Table tablePanel;
    private final JTextArea resultArea;

    private String csvFilePath = "/user/elena.kikiova/cheezy/resources/18.csv";

    public CheeseApp() {
        setTitle("Cheezy");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize and add components
        filterPanel = new Filters();
        tablePanel = new Table();

        // Panel for calculation result area
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

        // Load the cheese data from HDFS
        loadCheeseDataFromHDFS();

        // Add action listener for filter button
        filterPanel.addFilterListener(e -> applyFilterAndUpdateTable());
    }

    private void loadCheeseDataFromHDFS() {
        CheeseData.loadCheeseData(csvFilePath);
        List<Cheese> allCheeses = CheeseData.getAllCheeses();
        tablePanel.updateTable(allCheeses);
    }

    private void applyFilterAndUpdateTable() {
        String selectedProvince = filterPanel.getSelectedProvince();
        String selectedCategory = filterPanel.getSelectedCategory();
        String selectedMilkType = filterPanel.getSelectedMilkType();
        String selectedCalculation = filterPanel.getSelectedCalculation();

        try {
            List<Cheese> cheeseResults = CheeseJob.runCheeseJob(csvFilePath, selectedProvince, selectedCategory, selectedMilkType, selectedCalculation);

            if (cheeseResults != null && !cheeseResults.isEmpty()) {
                System.out.println("Cheese job finished. Results size: " + cheeseResults.size());
                tablePanel.updateTable(cheeseResults);  // Update table with results
            } else {
                JOptionPane.showMessageDialog(this, "No results found for the selected filters.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error running the job: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        BasicConfigurator.configure();
        SwingUtilities.invokeLater(() -> {
            CheeseApp app = new CheeseApp();
            app.setVisible(true);
        });
    }
}
