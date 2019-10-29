package debug;

import java.util.HashMap;
import java.util.Map;

/**
 * debug的breakpoints设置为线程级别
 * @author matthew_wu
 * @since 2019-09-24 11:55
 */
public class HashMapInfiniteLoop {
    private static Map<Integer, Integer> map = new HashMap<>(2, 0.75f);

    public static void main(String[] args) throws InterruptedException {
        map.put(5, 55);

        new Thread("Thread1-Name") {
            @Override
            public void run() {
                try {
                    System.out.println("Thread1-Name Start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //断点位置 1
                map.put(7, 77);
                System.out.println(map);
            }
        }.start();


        new Thread("Thread2-Name") {
            @Override
            public void run() {
                try {
                    System.out.println("Thread2-Name Start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //断点位置 2
                map.put(3, 33);
                System.out.println(map);
            }
        }.start();

        // 断点位置 3
        System.out.println("Thread-Main-Name Start");
        System.out.println("Thread-Main-Name Start");
        System.out.println("Thread-Main-Name Start");


        Thread.sleep(500000);
    }
}
