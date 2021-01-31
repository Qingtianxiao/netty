package hdfs;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

/**
 * Created by ligc on 2021/1/25 8:44
 */
public class MaxTemperatureWithCounters extends Configured implements Tool {
    enum Temperature{
        MISSING,
        MALFORMED
    }
    static class MaxTemperatureMapperWithCounters extends Mapper<LongWritable, Text,
            Text, IntWritable> {
        private NcdcRecordParser parser = new NcdcRecordParser();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            parser.parse(value);
            if(parser.isValidTemperature()){
                int airTemperature = parser.getAirTemperature();
                context.write(new Text(parser.getYear()), new IntWritable(airTemperature));
            }else if(parser.isMalformedTemperature()){
                System.err.println("Ignoring possibly corrupt input: " + value);
                context.getCounter(Temperature.MALFORMED).increment(1);
            }else if(parser.isMalformedTemperature()){
                context.getCounter(Temperature.MISSING).increment(1);
            }

        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        return 0;
    }
}
