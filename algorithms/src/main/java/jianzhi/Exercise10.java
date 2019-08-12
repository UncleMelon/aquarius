package jianzhi;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 *
 * @author matthew_wu
 * @since 2019-08-02 11:24
 */
public class Exercise10 {

    public int JumpFloor(int target) {
        if (target < 0) {
            return 0;
        }
        int[] fib = {0, 1, 2};
        if (target < 3) {
            return fib[target];
        }
        int[] steps = new int[target+1];
        steps[1] = 1;
        steps[2] = 2;
        for (int i = 3; i <= target; i++) {
            steps[i] = steps[i - 1] + steps[i - 2];
        }
        return steps[target];
    }

    public static void main(String[] args) {
        System.out.println(new Exercise10().JumpFloor(4));
    }

}

