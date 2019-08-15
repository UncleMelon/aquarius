package sort;

/**
 * @author matthew_wu
 * @since 2019-08-15 15:38
 */
public class ArraySort {
    private long[] a;
    private int nElems;

    public ArraySort(int max) {
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    public void display() {
        for (int i = 0; i <nElems ; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArraySort array = new ArraySort(10);
        array.insert(8);
        array.insert(1);
        array.insert(6);
        array.insert(5);
        array.insert(2);
        array.insert(4);
        array.insert(7);
        array.insert(3);
        array.display();
        array.selectSort();
        array.display();
    }

    public void bubbleSort() {
        for (int out = nElems - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (a[in] > a[in + 1]) {
                    swap(in, in+1);
                }
            }
        }
    }

    /**
     * 用变量储存最小值
     **/
    public void selectSort() {
        int min;
        for (int out = 0; out < nElems - 1; out++) {
            min = out;
            for (int in = out + 1; in < nElems; in++) {
                if (a[in] < a[min]) {
                    min = in;
                }
            }
            swap(out, min);
        }
    }

    public void insertSort() {
        for (int out = 1; out < nElems ; out++) {
           long temp = a[out];
           int in = out;
           while (in > 0 && a[in-1] > temp) {
               a[in] = a[in-1];
               in--;
           }
           a[in] = temp;
        }
    }

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }

}
