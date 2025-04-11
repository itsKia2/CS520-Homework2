package filter;

import model.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Filters transactions below a certain amount.
 */
public class AmountFilter implements TransactionFilter {

    private final double threshold;

    public AmountFilter(double maxAmount) {
        this.threshold = maxAmount;
    }

    @Override
    public List<Transaction> apply(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() <= threshold) {
                result.add(t);
            }
        }
        return result;
    }
}
