package hdfs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by ligc on 2021/1/21 8:41
 */
public class Test {
    public static void main(String[] args) throws IOException {
        IntWritable writable = new IntWritable(163);
        byte[] bytes = serialize(writable);
        System.out.println(bytes.length);

    }

    public static byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataout = new DataOutputStream(out);
        writable.write(dataout);
        dataout.close();
        return out.toByteArray();
    }
}
