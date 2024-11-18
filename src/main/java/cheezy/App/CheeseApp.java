package cheezy.App;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import cheezy.Cheese;
import cheezy.CheeseData;
import cheezy.hadoop.CheeseJob;
import cheezy.CheeseResult;
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

        filterPanel = new Filters();
        tablePanel = new Table();

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(filterPanel, BorderLayout.NORTH);
        topPanel.add(resultPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        loadCheeseDataFromHDFS();

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
            CheeseResult cheeseResults = CheeseJob.runCheeseJob(csvFilePath, selectedProvince, selectedCategory, selectedMilkType, selectedCalculation);
            String calculationResults = cheeseResults.getCalculationResult();
            List<Cheese> filteredCheese = cheeseResults.getFilteredCheese();

            if (!calculationResults.isEmpty()) {
                resultArea.setText("Calculation: " + calculationResults);

                System.out.println(filteredCheese.size());
                tablePanel.updateTable(filteredCheese);
            } else {
                JOptionPane.showMessageDialog(this, "No results found for the selected filters.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println(e);
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
