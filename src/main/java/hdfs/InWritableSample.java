package hdfs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.StringUtils;
import org.mortbay.util.StringUtil;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by ligc on 2021/1/23 15:11
 */
public class InWritableSample {
    public static void main(String[] args) throws IOException {
        IntWritable writable = new IntWritable(163);
        byte[] bytes = new InWritableSample().serialize(writable);
        System.out.println(StringUtils.byteToHexString(bytes));
        assertThat("a", is("a"));
    }

    public byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        writable.write(dataOut);
        dataOut.close();
        return out.toByteArray();
    }

    public byte[] deserialize(Writable writable, byte[] bytes) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        DataInputStream input = new DataInputStream(in);
        writable.readFields(input);
        input.close();
        return bytes;
    }
}
