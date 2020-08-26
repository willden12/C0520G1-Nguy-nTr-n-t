package sort_algotithm.baitap;

public class InsertionSort {
    public static void main(String[] args) {
        int size = 15;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        insertSort(arr, size);
        displayArray(arr, size);
    }

    private static void insertSort(int[] arr, int size) {
        int temp;
        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else
                    break;
            }
        }
    }

    private static void displayArray(int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
