import hdfs.MaxTemperatureMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ligc on 2021/1/22 7:40
 */
public class MaxTemperatureMapperTest {
    @Test
    public void processesValidRecord() throws IOException {
//        System.setProperty("HADOOP_HOME", "D:\\ligc\\hadoop\\hadoop");
//        System.out.println(System.getProperty("HADOOP_HOME"));
        Text value = new Text("0043011990999991950051518004+68750+623550FM-120382+" +
                "99999V0203201N00261220000CN9999999N9-00111+99999999999");
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("1950"), new IntWritable(-11))
                .runTest();
    }
}
