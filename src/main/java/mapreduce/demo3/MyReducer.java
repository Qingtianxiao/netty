package mapreduce.demo3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by ligc on 2021/1/31 22:21
 */
public class MyReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator iterator = values.iterator();
        StringBuffer sb = new StringBuffer();
        if(iterator.hasNext()){
            sb.append(iterator.next());
        }

        while(iterator.hasNext()){

        }
    }
}
