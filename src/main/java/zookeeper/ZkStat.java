package zookeeper;

import kafka.Kafka;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.sql.*;
import java.util.Properties;

/**
 * Created by ligc on 2021/1/21 21:46
 */
public class ZkStat {
    public static StringBuilder mysqlInit() throws ClassNotFoundException, SQLException {
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
        ResultSetMetaData metaData = rs.getMetaData();
        int columns = metaData.getColumnCount();
        while(rs.next()){
            for(int i = 1; i < columns; i ++){
                String columnValue = rs.getString(i);
                sb.append(columnValue + " ");
            }
            sb.append(rs.getString(columns) + "\n");
        }
        return sb;
    }
    public static Properties kafkaProducerInit(){
        Properties prop = new Properties();
        prop.put("bootstrap.servers","192.168.137.129:9092");
        prop.put("client.id", "test");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        return prop;
    }


}
