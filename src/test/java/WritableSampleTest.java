import hdfs.InWritableSample;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ligc on 2021/1/23 15:17
 */
public class WritableSampleTest {
    private InWritableSample sample;
    @Before
    public void init(){
        sample = new InWritableSample();
    }

    @Test
    public void serializeTest() throws IOException {
        IntWritable writable = new IntWritable(163);
        byte[] bytes = sample.serialize(writable);
        assertThat(StringUtils.byteToHexString(bytes), is("000000a3"));
    }

    @Test
    public void deserialize() throws IOException {
        IntWritable writable = new IntWritable(163);
        byte[] bytes = sample.serialize(writable);
        IntWritable writable1 = new IntWritable();
        sample.deserialize(writable1, bytes);
        assertThat(writable1.get(), is(163));
    }
}
