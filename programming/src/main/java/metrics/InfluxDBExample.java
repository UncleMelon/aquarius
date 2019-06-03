package metrics;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

public class InfluxDBExample {
    public static void main(String[] args) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.0.29:8086");
        Point point = Point.measurement("test1")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("1", "2")
                .addField("userd", 1).build();
        influxDB.write("test", "autogen", point);
    }
}
