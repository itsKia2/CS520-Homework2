package filter;

import model.Transaction;
import java.util.List;

/**
 * A strategy interface for applying filters on transactions.
 */
public interface TransactionFilter {
    List<Transaction> apply(List<Transaction> transactions);
}
