package metrics;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

/**
 * @Author matthew_wu
 **/
public class CounterExample {
    static final Counter functionCounter = Counter.build()
            .name("my_function_calls_total")
            .help("Number of times my_function was called").register();

    static void myFunction() {
        functionCounter.inc();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultExports.initialize();
        HTTPServer httpServer = new HTTPServer(18000);
        while (true) {
            myFunction();
            Thread.sleep(1000);
        }
    }

}
