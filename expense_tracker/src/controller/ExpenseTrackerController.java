package controller;

import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;
import filter.TransactionFilter;

import java.util.List;

public class ExpenseTrackerController {

    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;

    public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
        this.model = model;
        this.view = view;

        // Set up view event handlers
    }

    public void refresh() {
        // Get transactions from model
        List<Transaction> transactions = model.getTransactions();

        // Pass to view
        view.refreshTable(transactions);
    }

    public boolean addTransaction(double amount, String category) {
        if (!InputValidation.isValidAmount(amount)) {
            return false;
        }
        if (!InputValidation.isValidCategory(category)) {
            return false;
        }

        Transaction t = new Transaction(amount, category);
        model.addTransaction(t);
        view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
        refresh();
        return true;
    }

    /**
     * Filters transactions using a provided strategy and refreshes the view with the filtered results.
     *
     * @param filter The filter strategy to apply
     */
    public void applyTransactionFilter(TransactionFilter filter) {
        List<Transaction> allTransactions = model.getTransactions();
        List<Transaction> filteredTransactions = filter.apply(allTransactions);
        view.refreshTable(filteredTransactions);
    }

    // Optional: print filtered transactions (good for console/debugging)
    public void printFilteredTransactions(TransactionFilter filter) {
        List<Transaction> filtered = filter.apply(model.getTransactions());
        for (Transaction t : filtered) {
            System.out.println(t);
        }
    }

    // Other controller methods...
}
