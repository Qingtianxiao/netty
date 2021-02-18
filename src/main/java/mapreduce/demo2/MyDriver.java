package mapreduce.demo2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by ligc on 2021/1/31 21:01
 */
public class MyDriver extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        //0.实例一个Job
        Job job = Job.getInstance(getConf(), "myDriver2");
        //1.设施输入类和输入路径
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("hdfs://192.168.137.129:9000/output1"));
        //2.设置Mapper输出的键值类型
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //3.4.5.6

        //7.设置reduce的类和键值类型
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //8.设置输出类和输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("hdfs://192.168.137.129:9000/midResult"));

        //附加：设置reduce线程数
        job.setNumReduceTasks(3);

        //等待任务执行结束
        boolean completion = job.waitForCompletion(true);
        return completion? 0 : -1;
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        int exitCode = ToolRunner.run(conf, new MyDriver(), args);
        System.exit(exitCode);
    }
}
