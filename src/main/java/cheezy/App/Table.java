package cheezy.App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import cheezy.Cheese;
import cheezy.CheeseData;
import cheezy.Constants;

public class Table extends JPanel {

    private JTable cheeseTable;
    private DefaultTableModel tableModel;

    public Table() {
        setLayout(new BorderLayout());

        String[] columnNames = Constants.TABLE_COLUMNS;
        tableModel = new DefaultTableModel(columnNames, 0);
        cheeseTable = new JTable(tableModel);
        cheeseTable.setDefaultEditor(Object.class, null);

        cheeseTable.getTableHeader().setBackground(Color.WHITE);
        cheeseTable.getTableHeader().setForeground(Color.BLACK);
        cheeseTable.setForeground(Color.BLACK);

        add(new JScrollPane(cheeseTable), BorderLayout.CENTER);
    }

    // Method to update the table with a list of cheeses
    public void updateTable(List<Cheese> cheeses) {
        tableModel.setRowCount(0);

        for (Cheese cheese : cheeses) {
            Object[] rowData = CheeseData.getCheeseTableData(cheese);
            tableModel.addRow(rowData);
        }
    }
}
