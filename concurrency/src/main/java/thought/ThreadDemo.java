package thought;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

import static java.time.temporal.ChronoField.*;
import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author matthew_wu
 * @since 2019-11-01 11:54
 */
public class ThreadDemo {

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        MyRunnable myRunnable = new MyRunnable();
        MyRunnable myRunnable1 = new MyRunnable();
        es.execute(myRunnable);
        es.execute(myRunnable1);
        es.shutdown();

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.with((temporal) -> temporal.with(DAY_OF_MONTH, 1).with(HOUR_OF_DAY, 0)).with(MILLI_OF_SECOND, 0).format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(now.with((temporal) -> temporal.with(DAY_OF_MONTH, temporal.range(DAY_OF_MONTH).getMaximum()).with(MILLI_OF_SECOND, temporal.range(MILLI_OF_SECOND).getMaximum())).format(DateTimeFormatter.ISO_DATE_TIME));
    }

}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for(int i=0; i< 10; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run-----"+ Thread.currentThread().getName() + "    " + i);
        }
    }
}