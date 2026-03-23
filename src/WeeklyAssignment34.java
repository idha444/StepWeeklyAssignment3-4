import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // HH:MM

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class WeeklyAssignment34 {

    // Bubble Sort (by fee)
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        boolean swapped;
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swapped = true;
                    swaps++;
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.println("Bubble Sort → Passes: " + passes + ", Swaps: " + swaps);
    }

    // 🟢 Insertion Sort (fee + timestamp)
    public static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j)); // shift
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Comparator: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // High-fee outliers (>50)
    public static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }
        return outliers;
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        int size = transactions.size();

        // Choose sorting strategy
        if (size <= 100) {
            bubbleSortByFee(transactions);
            System.out.println("Bubble Sorted (fee): " + transactions);
        } else if (size <= 1000) {
            insertionSort(transactions);
            System.out.println("Insertion Sorted (fee+time): " + transactions);
        }

        // Outlier detection
        List<Transaction> outliers = findOutliers(transactions);

        if (outliers.isEmpty()) {
            System.out.println("High-fee outliers: none");
        } else {
            System.out.println("High-fee outliers: " + outliers);
        }
    }
}