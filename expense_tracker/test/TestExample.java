// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;
import filter.AmountFilter;
import filter.CategoryFilter;
import filter.TransactionFilter;

import controller.InputValidation;

public class TestExample {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private ExpenseTrackerController controller;

  @Before
  public void setup() {
    model = new ExpenseTrackerModel();
    view = new ExpenseTrackerView();
    controller = new ExpenseTrackerController(model, view);
  }

    public double getTotalCost() {
        double totalCost = 0.0;
        List<Transaction> allTransactions = model.getTransactions(); // Using the model's getTransactions method
        for (Transaction transaction : allTransactions) {
            totalCost += transaction.getAmount();
        }
        return totalCost;
    }
    


    @Test
    public void testAddTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(50.00, "food"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(50.00, getTotalCost(), 0.01);
    }


    @Test
    public void testRemoveTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "Groceries");
        model.addTransaction(addedTransaction);
    
        // Pre-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Perform the action: Remove the transaction
        model.removeTransaction(addedTransaction);
    
        // Post-condition: List of transactions is empty
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());
    
        // Check the total cost after removing the transaction
        double totalCost = getTotalCost();
        assertEquals(0.00, totalCost, 0.01);
    }

    @Test
    public void testAddTransactionCustom() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "food");
        model.addTransaction(addedTransaction);

        // Pre-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());

        // Check the total cost after removing the transaction
        double totalCost = getTotalCost();
        assertEquals(50.00, totalCost, 0.01);
        model.removeTransaction(addedTransaction);
    }

    @Test
    public void testInvalidInputHandling() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "omer");
        assertEquals(false, InputValidation.isValidCategory("omer"));
        // model.addTransaction(addedTransaction);

        // Pre-condition: List of transactions contains one transaction
        assertEquals(0, model.getTransactions().size());
        // model.removeTransaction(addedTransaction);
    }

    @Test
    public void testFilterAmount() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "food");
        model.addTransaction(addedTransaction);
        Transaction added2 = new Transaction(1.00, "food");
        model.addTransaction(added2);

        TransactionFilter myFilter = new AmountFilter(1.00);
        List<Transaction> res = myFilter.apply(model.getTransactions());

        assertEquals(1, res.size());
        assertEquals(2, model.getTransactions().size());
        // double totalCost = getTotalCost();
        // assertEquals(1.00, totalCost, 0.01);
        model.removeTransaction(addedTransaction);
        model.removeTransaction(added2);
    }

    @Test
    public void testFilterCategory() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "food");
        model.addTransaction(addedTransaction);
        Transaction added2 = new Transaction(1.00, "food");
        model.addTransaction(added2);
        Transaction added3 = new Transaction(42.00, "travel");
        model.addTransaction(added3);

        TransactionFilter myFilter = new CategoryFilter("travel");
        List<Transaction> res = myFilter.apply(model.getTransactions());

        assertEquals(1, res.size());
        assertEquals(3, model.getTransactions().size());
        model.removeTransaction(addedTransaction);
        model.removeTransaction(added2);
        model.removeTransaction(added3);
    }
}
