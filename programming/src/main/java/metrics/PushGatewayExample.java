package metrics;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Collector.MetricFamilySamples.Sample;
import io.prometheus.client.Gauge;
import io.prometheus.client.Gauge.Builder;
import io.prometheus.client.exporter.PushGateway;

import java.util.*;

public class PushGatewayExample {

    private static Gauge gauge;

    public static void executeBatchJob(List<Sample> list) throws Exception {
        CollectorRegistry registry = new CollectorRegistry();
        for (int i = 0; i < list.size(); i++) {
            Sample m = list.get(i);
            String[] labelNames = m.labelNames.toArray(new String[] {});
            String[] labelValues =   m.labelValues.toArray(new String[] {});
            Builder gaugeBuilder = Gauge.build(m.name, "percent of cpu").labelNames((String[]) labelNames);
            if (i == 0) {
                gauge = gaugeBuilder.register(registry);
            }
            gauge.labels(labelValues).set(m.value);

        }
        PushGateway pg = new PushGateway("127.0.0.1:9091");
        pg.pushAdd(registry, "my_job");
    }


    public List<Collector.MetricFamilySamples.Sample> getSample() {
        List<Collector.MetricFamilySamples.Sample> mfs = new ArrayList<>();

        String metricName = "kafka_offset";

//        Collector.MetricFamilySamples.Sample sample = new Collector.MetricFamilySamples.Sample(metricName, Arrays.asList("l1"), Arrays.asList("v1"), 10);
        Collector.MetricFamilySamples.Sample sample2 = new Collector.MetricFamilySamples.Sample(metricName, Arrays.asList("connector", "topic"),
                Arrays.asList("hive", "tasks"), new Random().nextInt(10000));

//        mfs.add(sample);
        mfs.add(sample2);
        return mfs;
    }

    public static void main(String[] args) throws Exception {
        executeBatchJob(new PushGatewayExample().getSample());
    }
}
