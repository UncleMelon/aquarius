package jianzhi;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 思路：从右上角或左下角开始找，逐行删除，或者用二分法查找
 * @author matthew_wu
 * @since 2019-08-02 10:25
 */
public class Exercise3 {

    public boolean Find(int target, int[][] array) {
        if (array == null) {
            return false;
        }

        int row = 0;
        int col = array[0].length - 1;
        while (row < array.length && col >= 0) {
            if (array[row][col] == target) {
                return true;
            }
            if (array[row][col] < target) {
                row++ ;
            } else {
                col--;
            }
        }
        return false;
    }

}
