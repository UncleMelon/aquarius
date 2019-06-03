package metrics;

import io.prometheus.client.Collector;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CollectorExample extends Collector {
    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfs = new ArrayList<>();

        String metricName = "my_guage_1";

        MetricFamilySamples.Sample sample = new MetricFamilySamples.Sample(metricName, Arrays.asList("l1"), Arrays.asList("v1"), 4);
        MetricFamilySamples.Sample sample2 = new MetricFamilySamples.Sample(metricName, Arrays.asList("l1", "l2"), Arrays.asList("v1", "v2"), new Random().nextInt(100));

        MetricFamilySamples samples = new MetricFamilySamples(metricName, Type.GAUGE, "help", Arrays.asList(sample, sample2));
        mfs.add(samples);
        return mfs;
    }


    public static void main(String[] args) throws IOException {
        HTTPServer httpServer = new HTTPServer(18000);
        Collector register = new CollectorExample().register();
    }
}
