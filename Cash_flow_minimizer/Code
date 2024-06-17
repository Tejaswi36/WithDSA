import java.util.*;

public class CashFlowMinimizer {

    public static int minimizeTransactions(Map<String, Map<String, Integer>> transactions) {
        // 1. Graph Construction
        Map<String, Integer> balances = new HashMap<>();
        for (String borrower : transactions.keySet()) {
            balances.put(borrower, 0);
            for (Map.Entry<String, Integer> entry : transactions.get(borrower).entrySet()) {
                balances.put(borrower, balances.get(borrower) - entry.getValue());
                balances.putIfAbsent(entry.getKey(), 0);
                balances.put(entry.getKey(), balances.get(entry.getKey()) + entry.getValue());
            }
        }

        // 2. Multiset Initialization (using HashMaps and ArrayLists)
        Map<String, List<Transaction>> multisets = new HashMap<>();
        for (String person : transactions.keySet()) {
            multisets.put(person, new ArrayList<>());
            // Add your logic to populate the multisets with transactions and due dates
        }

        // 3. Min-Heap Population
        PriorityQueue<Transaction> heap = new PriorityQueue<>((t1, t2) -> t1.dueDate - t2.dueDate);
        for (String person : multisets.keySet()) {
            for (Transaction transaction : multisets.get(person)) {
                heap.offer(transaction);
            }
        }

        // 4. Iterative Processing
        int totalTransactions = 0;
        while (!heap.isEmpty()) {
            Transaction transaction = heap.poll();
            if (transaction.amount > 0) { // Income
                balances.put(transaction.person, balances.get(transaction.person) + transaction.amount);
            } else { // Expense
                if (balances.get(transaction.person) >= -transaction.amount) {
                    balances.put(transaction.person, balances.get(transaction.person) + transaction.amount);
                } else {
                    for (String creditor : balances.keySet()) {
                        if (balances.get(creditor) > 0) {
                            int settlement = Math.min(-transaction.amount, balances.get(creditor));
                            balances.put(transaction.person, balances.get(transaction.person) + settlement);
                            balances.put(creditor, balances.get(creditor) - settlement);
                            heap.offer(new Transaction(creditor, -settlement, transaction.dueDate));
                            totalTransactions++;
                            break;
                        }
                    }
                }
            }
        }

        // 5. Result Analysis (optional)
        // totalTransactions already calculated in processing
        return totalTransactions;
    }

    public static class Transaction {
        String person;
        int amount;
        int dueDate;

        public Transaction(String person, int amount, int dueDate) {
            this.person = person;
            this.amount = amount;
            this.dueDate = dueDate;
        }
    }

    public static void main(String[] args) {
        // Example usage: Replace with your actual transaction data
        Map<String, Map<String, Integer>> transactions = new HashMap<>();
        transactions.put("Alice", new HashMap<>() {{ put("Bob", 100); put("Charlie", 50); }});
        transactions.put("Bob", new HashMap<>() {{ put("Charlie", 70); }});
        transactions.put("Charlie", new HashMap<>());

        int minTransactions = minimizeTransactions(transactions);
        System.out.println("Minimum transactions required: " + minTransactions);
    }
}
