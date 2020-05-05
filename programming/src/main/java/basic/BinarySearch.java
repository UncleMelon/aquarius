package basic;

/**
 * 二分法查找
 * 临界条件：起始位置大于结束位置
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 6, 8, 9, 10, 12, 18, 20, 34};
        int search = search(arr, 12);
        System.out.println(search);
    }

    public static int search(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }

        int startPos = 0;
        int endPos  = arr.length - 1;
        int middle = (startPos + endPos) / 2;
        // startPos == endPos 情况是剩下最后一个元素和target去比较
        while (startPos <= endPos) {
            if (arr[middle] == target) {
                return middle;
            }
            if (arr[middle] > target) {
                endPos = middle + 1;
            }
            if (arr[middle] < target) {
                startPos = middle - 1;
            }
            middle = (startPos + endPos) / 2;
        }
        return -1;
    }
}
