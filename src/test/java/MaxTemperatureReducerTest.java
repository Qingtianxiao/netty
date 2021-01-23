import hdfs.MaxTemperatureReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ligc on 2021/1/23 17:00
 */
public class MaxTemperatureReducerTest {
    @Test
    public void reduceTest() throws IOException {
        new ReduceDriver<Text, IntWritable, Text, IntWritable>()
                .withReducer(new MaxTemperatureReducer())
                .withInputKey(new Text("1950"))
                .withInputValues(Arrays.asList(new IntWritable(5), new IntWritable(10)))
                .withOutput(new Text("1950"), new IntWritable(10))
                .runTest();
    }
}
