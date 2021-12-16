public class Sort {
    // Quicksort in decreasing order, based on the total time of each task
    private static void swap(Task[] arr, int i, int j) {
        Task temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int partition(Task[] arr, int low, int high) {
        int pivot = arr[high].getTime();

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j].getTime() > pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, high);
        return i + 1;
    }

    public static void quickSort(Task[] arr, int low, int high) {
        if (low < high) {
            int part = partition(arr, low, high);

            quickSort(arr, low, part - 1);
            quickSort(arr, part+1, high);
        }
    }
}
