package jianzhi;

import exercises.Fibonacci;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 * @author matthew_wu
 * @since 2019-08-02 11:24
 */
public class Exercise9 {

    public int Fibonacci(int n) {
       if (n == 1 || n  == 2) {
           return  1;
       }
       return f(0, 1, n);
    }

    public int f (int acc1, int acc2, int n) {
        if (n == 0) {
            return acc1;
        }
        else {
            return f(acc2, acc1 + acc2, n-1);
        }
    }

    public int Fibonacci2(int n) {
        int result=0;
        int preOne=0;
        int preTwo=1;
        if (n == 0) {
            return preOne;
        }
        if (n == 1) {
            return preTwo;
        }

        for (int i = 2; i <= n; i++) {
            result = preOne + preTwo;
            preOne = preTwo;
            preTwo = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Exercise9().Fibonacci(5));
    }
}