package mapreduce.demo1;

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
 * Created by ligc on 2021/1/31 17:28
 */
public class MyDriver extends Configured implements Tool {

    @Override
    public int run(String[] strings) throws Exception {
        //0.设置Job对象
        Job job = Job.getInstance(getConf(), "MyJob1");
        //1.设置输入类和输入路径
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("hdfs://192.168.137.129:9000/input1"));

        //2.设置Mapper类和数据类型
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //3.4.5.6
        //7.设置Reducer类和数据类型
        job.setReducerClass(MyReducer.class);

        job.setNumReduceTasks(3);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //8.设置输出类和输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("hdfs://192.168.137.129:9000/output1"));

        //等待job任务结束
        boolean b1 = job.waitForCompletion(true);
        return b1? 0 : -1;

    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        int exitCode = ToolRunner.run(conf, new MyDriver(), args);
        System.exit(exitCode);
    }

}
