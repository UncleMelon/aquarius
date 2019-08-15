package sort;

/**
 * @author matthew_wu
 * @since 2019-08-15 16:46
 */
public class SortApp {
    public static void main(String[] args) {
        ArraySort bubbleSortData = getData();
        System.out.println("Before bubble sorting:");
        bubbleSortData.display();
        bubbleSortData.bubbleSort();
        bubbleSortData.display();
        System.out.println("====================");

        ArraySort selectSortData = getData();
        System.out.println("Before select sorting:");
        selectSortData.display();
        selectSortData.selectSort();
        selectSortData.display();
        System.out.println("====================");

        ArraySort insertSortData = getData();
        System.out.println("Before insert sorting:");
        insertSortData.display();
        insertSortData.insertSort();
        insertSortData.display();
        System.out.println("====================");
    }


    public static ArraySort getData() {
        int maxSize = 100;
        ArraySort array = new ArraySort(maxSize);
        array.insert(77);
        array.insert(99);
        array.insert(44);
        array.insert(55);
        array.insert(22);
        array.insert(88);
        array.insert(11);
        array.insert(00);
        array.insert(66);
        array.insert(33);
        return array;
    }
}
