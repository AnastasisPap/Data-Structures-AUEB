public class QuickSort {
    public static void quicksort(WordFreq[] items, int left, int right) {
        if (left < right) {
            int idx = partition(items, left, right);

            quicksort(items, left, idx - 1);
            quicksort(items, idx + 1, right);
        }
    }

    private static int partition(WordFreq[] items, int left, int right) {
        int pivot = items[right].getOccurrences();

        int i = left - 1;

        for (int j = left; j <= right; j++){
            if (items[j].getOccurrences() < pivot){
                i++;
                swap(items, i, j);
            }
        }

        swap(items, i + 1, right);
        return i + 1;
    }

    private static void swap(WordFreq[] items, int i, int j) {
        WordFreq temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}
