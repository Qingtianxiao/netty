package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by ligc on 2021/1/8 8:21
 */
public class BulkLoadSample {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        System.setProperty("hadoop.home.dir","D:\\ligc\\hadoop\\hadoop");
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);

        Table table = connection.getTable(TableName.valueOf("tb_cdr"));
        Admin admin = connection.getAdmin();

        //设置Job
        try{
            Job job = Job.getInstance(conf, "bulkload");
            job.setJarByClass(BulkLoadSample.class);
            job.setMapperClass(HbaseMapper.class);
            job.setMapOutputKeyClass(ImmutableBytesWritable.class);
            job.setMapOutputValueClass(Put.class);

            //设置文件的输入输出路径
            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(HFileOutputFormat2.class);

            FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.137.129:9000/inputdata/bulkload"));
            FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.137.129:9000/hfile2"));
            //将数据加载到hbase中
            HFileOutputFormat2.configureIncrementalLoad(job, table, connection.getRegionLocator(TableName.valueOf("tb_cdr")));
//            if(job.waitForCompletion(true)){
//                LoadIncrementalHFiles load = new LoadIncrementalHFiles(conf);
//                load.doBulkLoad(new Path("hdfs://192.168.137.129:9000/hfile2"), admin
//                        , table,connection.getRegionLocator(TableName.valueOf("tb_cdr")));
//            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
