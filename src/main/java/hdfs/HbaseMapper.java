package hdfs;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by ligc on 2021/1/7 21:29
 */
public class HbaseMapper extends Mapper {
    @Override
    protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] str = line.split(" ");
        String usernum = str[0];
        String geohash = str[1];
        String eventtype = str[2];
        String starttime = str[3];
        String endtime = str[4];

        ImmutableBytesWritable rowkey = new ImmutableBytesWritable(usernum.getBytes());
        Put put = new Put(usernum.getBytes());
        put.addColumn("a".getBytes(), "usernum".getBytes(), usernum.getBytes());
        put.addColumn("a".getBytes(), "geohash".getBytes(),geohash.getBytes());
        put.addColumn("a".getBytes(),"eventtype".getBytes(), eventtype.getBytes());
        put.addColumn("a".getBytes(),"starttime".getBytes(), starttime.getBytes());
        put.addColumn("a".getBytes(), "endtime".getBytes(), endtime.getBytes());
        context.write(rowkey,put);
    }
}
