import java.util.Random;

public class WeeklyAssignment34 {

    static class Asset {
        String name;
        double returnRate;
        double volatility;

        Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        public String toString() {
            return name + ":" + returnRate + "%";
        }
    }

    // 🔵 MERGE SORT (Stable, Ascending by returnRate)
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) { // stable
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 🟢 QUICK SORT (DESC returnRate, ASC volatility)
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {

            // Hybrid optimization
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partition logic
    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot)) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Comparison: DESC return, ASC volatility
    public static boolean compare(Asset a, Asset b) {
        if (a.returnRate > b.returnRate) return true;
        if (a.returnRate == b.returnRate)
            return a.volatility < b.volatility;
        return false;
    }

    // 🔸 Median-of-3 Pivot
    public static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate > arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate > arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate > arr[high].returnRate) swap(arr, mid, high);

        return mid;
    }

    // 🔸 Random Pivot (optional alternative)
    public static int randomPivot(int low, int high) {
        Random rand = new Random();
        return low + rand.nextInt(high - low + 1);
    }

    // 🔸 Insertion Sort (for small partitions)
    public static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && !compare(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Utility swap
    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Print array
    public static void print(Asset[] arr) {
        for (Asset a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        // 🔵 Merge Sort
        mergeSort(assets, 0, assets.length - 1);
        System.out.println("Merge Sort (Ascending):");
        print(assets);

        // 🟢 Quick Sort
        quickSort(assets, 0, assets.length - 1);
        System.out.println("Quick Sort (Descending):");
        print(assets);
    }
}