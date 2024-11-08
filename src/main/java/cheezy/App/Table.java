package cheezy.App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import cheezy.Cheese;
import cheezy.Constants;

public class Table extends JPanel {

    private JTable cheeseTable;
    private DefaultTableModel tableModel;

    public Table() {
        setLayout(new BorderLayout());

        String[] columnNames = Constants.TABLE_COLUMNS;
        tableModel = new DefaultTableModel(columnNames, 0);
        cheeseTable = new JTable(tableModel);

        cheeseTable.getTableHeader().setBackground(Color.WHITE);
        cheeseTable.getTableHeader().setForeground(Color.BLACK);
        cheeseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        cheeseTable.setFont(new Font("Arial", Font.PLAIN, 12));
        cheeseTable.setForeground(Color.BLACK);

        add(new JScrollPane(cheeseTable), BorderLayout.CENTER);
    }

    // Method to update the table with a list of cheeses
    public void updateTable(List<Cheese> cheeses) {
        tableModel.setRowCount(0);

        for (Cheese cheese : cheeses) {
            Object[] rowData = {
                    cheese.getCheeseId(),
                    cheese.getCheeseName(),
                    cheese.getManufacturerProvCode(),
                    cheese.getCategoryTypeEn(),
                    cheese.getMilkTypeEn(),
                    cheese.getMoisturePercent(),
                    cheese.isOrganic() ? "Yes" : "No",
                    cheese.getFlavourEn(),
                    cheese.getFatLevel()
            };
            tableModel.addRow(rowData);
        }
    }
}
