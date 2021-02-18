package mapreduce.demo3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ligc on 2021/1/31 22:17
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] eachs = value.toString().split(" ")[1].split("-");
        String common = value.toString().split(" ")[0];

        Arrays.sort(eachs);

        for(int i = 0; i < eachs.length - 1; i ++){
            for(int j = 1; j <eachs.length; j ++){
                context.write(new Text(eachs[i] + " " + eachs[j]),
                        new Text(common));
            }
        }
    }
}
