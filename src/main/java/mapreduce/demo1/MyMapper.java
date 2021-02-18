package mapreduce.demo1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by ligc on 2021/1/31 17:20
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.write(new Text(value.toString().split("\t")[0]),
                new Text(value.toString().split("\t")[1]));
    }
}
