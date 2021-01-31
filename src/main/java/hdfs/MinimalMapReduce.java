package hdfs;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by ligc on 2021/1/24 21:38
 * hadoop权威指南 P212
 * 不指定Mapper 或者Reducer就运行MapReduce
 *
 */
public class MinimalMapReduce extends Configured implements Tool {
    @Override
    public int run(String[] strings)  {
        try{
            Job job = new Job(getConf());
            job.setJarByClass(getClass());
            FileInputFormat.addInputPath(job, new Path(strings[0]));
            FileOutputFormat.setOutputPath(job, new Path(strings[1]));
            return job.waitForCompletion(true) ? 0 : 1;
        }catch (Exception e){
            return -1;
        }

    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MinimalMapReduce(), args);
        System.exit(exitCode);

    }
}
