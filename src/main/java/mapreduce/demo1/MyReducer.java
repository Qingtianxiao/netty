package mapreduce.demo1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by ligc on 2021/1/31 17:24
 */
public class MyReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer sb = new StringBuffer();
        Iterator iterator = values.iterator();
        if(iterator.hasNext()){
            sb.append(new StringBuffer(iterator.next().toString()));
        }
        while(iterator.hasNext()){
            sb.append(",");
            sb.append(new StringBuffer(iterator.next().toString()));
        }
        key = new Text(key.toString() + ":");
        context.write(key, new Text(sb.toString()));
    }
}
