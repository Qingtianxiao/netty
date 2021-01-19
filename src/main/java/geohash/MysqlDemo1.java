package geohash;

import ch.hsr.geohash.GeoHash;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligc on 2021/1/5 20:50
 */
public class MysqlDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //初始化链接
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://192.168.137.131:3306/mysql";
        String user = "root";
        String passwd = "Lll030921";

        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_URL,user,passwd);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        String sql = "insert into tb_cdr (usernum,geohash,eventtype,starttime,endtime) values (?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        RandomLangtitudeAndLatitude random = new RandomLangtitudeAndLatitude(100000);
        Map<Double, Double> map = random.getMap();
        System.out.println("map的大小为" + random.size());
        for(double key : map.keySet()){
            int usernum = (int) (Math.random() * 1000);
            String geohash = GeoHash.geoHashStringWithCharacterPrecision(map.get(key), key,6);
            int eventtype = (int) (Math.random() * 3);
            String starttime = System.currentTimeMillis() / 1000 - (int)(Math.random() * 1440 * 60) + "";
            String endtime = (Long.valueOf(starttime) + 500000L) + "";
            preparedStatement.setInt(1, usernum);
            preparedStatement.setString(2, geohash);
            preparedStatement.setInt(3, eventtype);
            preparedStatement.setString(4,starttime);
            preparedStatement.setString(5,endtime);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        connection.commit();
    }
}
