package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map;

/**
 * Created by ligc on 2021/1/22 7:29
 */
public class ConfiguratiionPrinter extends Configured implements Tool {
    static{
        Configuration.addDefaultResource("../conf/hbase-site.xml");
    }
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        for(Map.Entry<String, String> entry :conf){
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new ConfiguratiionPrinter(), args);
        System.exit(exitCode);
    }
}
