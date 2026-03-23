import java.util.Arrays;

public class WeeklyAssignment34 {

    // 🔵 LINEAR SEARCH (First & Last Occurrence)
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("First Index: " + first);
        System.out.println("Last Index: " + last);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)\n");
    }

    // 🟢 BINARY SEARCH (Find one occurrence)
    public static int binarySearch(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 🔸 Find First Occurrence (Binary)
    public static int firstOccurrence(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔸 Find Last Occurrence (Binary)
    public static int lastOccurrence(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔴 Count Occurrences
    public static void binarySearchWithCount(String[] arr, String target) {
        Counter counter = new Counter();

        int first = firstOccurrence(arr, target, counter);
        int last = lastOccurrence(arr, target, counter);

        int count = (first == -1) ? 0 : (last - first + 1);

        System.out.println("Binary Search:");
        System.out.println("First Index: " + first);
        System.out.println("Last Index: " + last);
        System.out.println("Count: " + count);
        System.out.println("Comparisons: " + counter.count);
        System.out.println("Time Complexity: O(log n)");
    }

    // Helper class to track comparisons
    static class Counter {
        int count = 0;
    }

    public static void main(String[] args) {

        // INPUT (unsorted)
        String[] logs = {"accB", "accA", "accB", "accC"};

        // 🔵 Linear Search (unsorted allowed)
        linearSearch(logs, "accB");

        // 🟢 Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        // 🔴 Binary Search with count
        binarySearchWithCount(logs, "accB");
    }
}