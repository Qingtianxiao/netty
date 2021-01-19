package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FSOutputSummer;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/**
 * Created by ligc on 2021/1/7 20:32
 */
public class WriteSample {
    private static final Logger logger = LoggerFactory.getLogger(WriteSample.class);
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException, SQLException {
        String hdfsuri = "hdfs://192.168.137.129:9000";
        String path = "/inputdata/bulkload";
        System.setProperty("HADOOP_USER_NAME", "hdfs");
        System.setProperty("hadoop.home.dir", "/");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",hdfsuri);

        FileSystem fs=FileSystem.get(new URI(hdfsuri),conf,"root");

        //mysql配置
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://192.168.137.131:3306/mysql";
        String user = "root";
        String passwd = "Lll030921";

        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_URL,user,passwd);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        String sql = "select * from tb_cdr";

        ResultSet rs = statement.executeQuery(sql);
        StringBuilder sb = new StringBuilder(1024 * 64);
        FSDataOutputStream out = fs.create(new Path("/inputdata/bulkload/test1.txt"));
        ResultSetMetaData metaData = rs.getMetaData();
        int columns = metaData.getColumnCount();
        while(rs.next()){
            for(int i = 1; i < columns; i ++){
                String columnValue = rs.getString(i);
                sb.append(columnValue + " ");
            }
            sb.append(rs.getString(columns) + "\n");
        }
        out.write(new String(sb).getBytes());
        out.flush();
        out.close();

    }
}
