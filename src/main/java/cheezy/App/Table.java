package cheezy.App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import cheezy.Cheese;

public class Table extends JPanel {

    private JTable cheeseTable;
    private DefaultTableModel tableModel;

    public Table() {
        setLayout(new BorderLayout());

        // Table columns
        String[] columnNames = {"Cheese ID", "Name", "Province", "Category", "Milk Type", "Moisture %", "Organic"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cheeseTable = new JTable(tableModel);

        add(new JScrollPane(cheeseTable), BorderLayout.CENTER);
    }

    // Method to update the table with a list of cheeses
    public void updateTable(List<Cheese> cheeses) {
        tableModel.setRowCount(0); // Clear existing rows

        for (Cheese cheese : cheeses) {
            Object[] rowData = {
                    cheese.getCheeseId(),
                    cheese.getCheeseName(),
                    cheese.getManufacturerProvCode(),
                    cheese.getCategoryTypeEn(),
                    cheese.getMilkTypeEn(),
                    cheese.getMoisturePercent(),
                    cheese.isOrganic() ? "Yes" : "No"
            };
            tableModel.addRow(rowData);
        }
    }
}
