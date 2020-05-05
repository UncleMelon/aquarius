package basic;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 1, 3, 10, 8, 6};
        print(arr);
        sort(arr);
        print(arr);

    }

    public static void sort(int[] arr) {
        for (int o = 0; o < arr.length - 1; o++) {
            int min = o;
            for (int i = o + 1; i < arr.length; i++) {
                if (arr[min] > arr[i]) {
                    min = i;
                }
            }
            if (min != o) {
                int tmp = arr[o];
                arr[o] = arr[min];
                arr[min] = tmp;
            }
        }
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
