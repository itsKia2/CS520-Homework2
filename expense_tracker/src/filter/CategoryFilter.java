package filter;

import model.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Filters a list of transactions by category.
 */
public class CategoryFilter implements TransactionFilter {

    private final String target;

    public CategoryFilter(String category) {
        this.target = category.toLowerCase();
    }

    @Override
    public List<Transaction> apply(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getCategory().equalsIgnoreCase(target)) {
                result.add(t);
            }
        }
        return result;
    }
}
