package cheezy.App;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

import cheezy.Cheese;
import cheezy.CheeseData;
import cheezy.CheeseJob;

public class CheeseApp extends JFrame {

    private final Filters filterPanel;
    private final Table tablePanel;
    private final JTextArea resultArea;
    private static final Logger logger = Logger.getLogger(CheeseApp.class.getName()); // Initialize the logger

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
        String csvFilePath = "/user/elena.kikiova/cheezy/resources/18.csv";
        CheeseData.loadCheeseData(csvFilePath);
        List<Cheese> allCheeses = CheeseData.getAllCheeses();
        tablePanel.updateTable(allCheeses);
    }

    private void applyFilterAndUpdateTable() {
        // Get selected filter values
        String selectedProvince = filterPanel.getSelectedProvince();
        String selectedCategory = filterPanel.getSelectedCategory();
        String selectedMilkType = filterPanel.getSelectedMilkType();

        // Log the selected filters to ensure they are being passed correctly
        logger.info("Selected Province: " + selectedProvince);
        logger.info("Selected Category: " + selectedCategory);
        logger.info("Selected Milk Type: " + selectedMilkType);

        // Run the Hadoop job with the selected filters
        try {
            String inputPath = "/user/elena.kikiova/cheezy/resources/18.csv"; // HDFS path
            logger.info("Running Cheese Job with inputPath: " + inputPath);

            List<Cheese> cheeseResults = CheeseJob.runCheeseJob(inputPath, selectedProvince, selectedCategory, selectedMilkType);

            // Log the size of the result list to ensure the job returned results
            if (cheeseResults != null) {
                logger.info("Cheese job finished. Results size: " + cheeseResults.size());
            } else {
                logger.warning("No results returned from the cheese job.");
            }

            // Update the table with the results from the job
            tablePanel.updateTable(cheeseResults);

        } catch (Exception e) {
            // Log the exception to understand what went wrong
            logger.severe("Error while running the cheese job: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheeseApp app = new CheeseApp();
            app.setVisible(true);
        });
    }
}
