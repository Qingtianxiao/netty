package mapreduce.demo2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by ligc on 2021/1/31 20:53
 */
public class MyMapper  extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] friends = value.toString().split(":")[1].split(",");
        String user = value.toString().split(":")[0];
        for(int i = 0; i < friends.length; i ++){
            context.write(new Text(friends[i].trim()),new Text(user));
        }
    }
}
