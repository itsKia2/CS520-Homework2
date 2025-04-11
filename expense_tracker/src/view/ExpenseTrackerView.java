package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.Transaction;

public class ExpenseTrackerView extends JFrame {

    private DefaultTableModel tableModel;
    private JTable transactionTable;

    // Fields for filtering
    private JTextField categoryFilterField;
    private JTextField amountFilterField;
    private JButton filterButton;

    // Fields for adding transactions
    private JTextField amountInputField;
    private JTextField categoryInputField;
    private JButton addTransactionBtn;

    public ExpenseTrackerView() {
        setTitle("Expense Tracker");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Amount", "Category", "Timestamp"}, 0);
        transactionTable = new JTable(tableModel);
        add(new JScrollPane(transactionTable), BorderLayout.CENTER);

        // Input panel for adding transactions
        JPanel inputPanel = new JPanel(new FlowLayout());
        amountInputField = new JTextField(8);
        categoryInputField = new JTextField(8);
        addTransactionBtn = new JButton("Add Transaction");

        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountInputField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryInputField);
        inputPanel.add(addTransactionBtn);
        add(inputPanel, BorderLayout.NORTH);

        // Filter panel
        JPanel filterPanel = new JPanel(new FlowLayout());
        categoryFilterField = new JTextField(10);
        amountFilterField = new JTextField(10);
        filterButton = new JButton("Apply Filters");

        filterPanel.add(new JLabel("Filter Category:"));
        filterPanel.add(categoryFilterField);
        filterPanel.add(new JLabel("Max Amount:"));
        filterPanel.add(amountFilterField);
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.SOUTH);
    }

    public void refreshTable(List<Transaction> transactions) {
        tableModel.setRowCount(0);
        for (Transaction t : transactions) {
            tableModel.addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
        }
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public String getCategoryFilter() {
        return categoryFilterField.getText();
    }

    public String getAmountFilter() {
        return amountFilterField.getText();
    }

    public void setFilterListener(ActionListener listener) {
        filterButton.addActionListener(listener);
    }

    // === 🆕 Add transaction methods ===
    public JButton getAddTransactionBtn() {
        return addTransactionBtn;
    }

    public double getAmountField() {
        try {
            return Double.parseDouble(amountInputField.getText());
        } catch (NumberFormatException e) {
            return -1; // Invalid number
        }
    }

    public String getCategoryField() {
        return categoryInputField.getText();
    }
}