package java8.charpter7;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Author matthew_wu
 * @Description 高分解性的流数据源: ArrayList, IntStream.range, HashSet, TreeSet
 **/
public class StreamPractice {
    public static void main(String[] args) {
        final String SENTENCE = "";
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                                            .mapToObj(SENTENCE::charAt);

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
    }

    private int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                                        WordCounter::accumulate,
                                        WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static long forJoinSum(long n) {
        long[] number = LongStream.range(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(number);
        return new ForkJoinPool().invoke(task);
    }

    //有数据装箱操作，慢
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
