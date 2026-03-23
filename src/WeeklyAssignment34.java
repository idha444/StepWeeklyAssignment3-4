public class WeeklyAssignment34 {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        public String toString() {
            return name + ":" + riskScore;
        }
    }

    // 🔵 Bubble Sort (Ascending by riskScore)
    public static int bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }
        return swaps;
    }

    // 🟢 Insertion Sort (Descending by riskScore, then balance)
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && (
                    arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance)
            )) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // 🔴 Print Top 10 Highest Risk Clients
    public static void printTopClients(Client[] arr) {
        int limit = Math.min(10, arr.length);
        System.out.println("\nTop High Risk Clients:");
        for (int i = 0; i < limit; i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    // Utility to print array
    public static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // 🔵 Bubble Sort ASC
        System.out.println("Before Bubble Sort:");
        printArray(clients);

        int swaps = bubbleSort(clients);

        System.out.println("\nAfter Bubble Sort (Ascending):");
        printArray(clients);
        System.out.println("Swaps: " + swaps);

        // 🟢 Insertion Sort DESC
        insertionSort(clients);

        System.out.println("\nAfter Insertion Sort (Descending):");
        printArray(clients);

        // 🔴 Top Clients
        printTopClients(clients);
    }
}