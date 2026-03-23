import java.util.Arrays;

public class WeeklyAssignment34 {

    // 🔵 LINEAR SEARCH (unsorted)
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("Found: " + found);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)\n");
    }

    // 🟢 BINARY SEARCH INSERTION POINT (lower_bound)
    public static int lowerBound(int[] arr, int target, Counter c) {
        int low = 0, high = arr.length;

        while (low < high) {
            c.count++;
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // 🔴 FLOOR (largest ≤ target)
    public static int floor(int[] arr, int target, Counter c) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            c.count++;
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔴 CEILING (smallest ≥ target)
    public static int ceiling(int[] arr, int target, Counter c) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            c.count++;
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    static class Counter {
        int count = 0;
    }

    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        // 🔵 Linear Search (unsorted)
        linearSearch(unsorted, target);

        // 🟢 Sort for Binary Search
        Arrays.sort(unsorted);
        System.out.println("Sorted Bands: " + Arrays.toString(unsorted));

        Counter c1 = new Counter();
        int insertPos = lowerBound(unsorted, target, c1);

        Counter c2 = new Counter();
        int floor = floor(unsorted, target, c2);

        Counter c3 = new Counter();
        int ceil = ceiling(unsorted, target, c3);

        System.out.println("\nBinary Search:");
        System.out.println("Insertion Index: " + insertPos);
        System.out.println("Floor (<= target): " + floor);
        System.out.println("Ceiling (>= target): " + ceil);
        System.out.println("Comparisons: " + (c1.count + c2.count + c3.count));
        System.out.println("Time Complexity: O(log n)");
    }
}