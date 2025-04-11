import javax.swing.JOptionPane;

import controller.ExpenseTrackerController;
import filter.AmountFilter;
import filter.CategoryFilter;
import filter.TransactionFilter;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;

public class ExpenseTrackerApp {

    public static void main(String[] args) {

        // MVC setup
        ExpenseTrackerModel model = new ExpenseTrackerModel();
        ExpenseTrackerView view = new ExpenseTrackerView();
        ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

        view.setVisible(true);

        // Add transaction handler
        view.getAddTransactionBtn().addActionListener(e -> {
            double amount = view.getAmountField();
            String category = view.getCategoryField();

            boolean added = controller.addTransaction(amount, category);
            if (!added) {
                JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
                view.toFront();
            }
        });

        // Filter handler
        view.setFilterListener(e -> {
            String category = view.getCategoryFilter();
            String amountStr = view.getAmountFilter();

            TransactionFilter filter = null;

            try {
                // Combine filters if both provided
                if (!category.isEmpty() && !amountStr.isEmpty()) {
                    double maxAmount = Double.parseDouble(amountStr);
                    filter = transactions -> {
                        return new AmountFilter(maxAmount)
                                .apply(new CategoryFilter(category).apply(transactions));
                    };
                } else if (!category.isEmpty()) {
                    filter = new CategoryFilter(category);
                } else if (!amountStr.isEmpty()) {
                    double maxAmount = Double.parseDouble(amountStr);
                    filter = new AmountFilter(maxAmount);
                }

                if (filter != null) {
                    controller.applyTransactionFilter(filter);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter a valid number for amount");
                view.toFront();
            }
        });
    }
}