package hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * Created by ligc on 2021/1/31 22:34
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        TableName tname = TableName.valueOf("tb_cdr");
//        TableDescriptorBuilder tableDescBuilder = TableDescriptorBuilder.newBuilder(tname);
//        ColumnFamilyDescriptorBuilder columnDescBuilder = ColumnFamilyDescriptorBuilder
//                .newBuilder(Bytes.toBytes(family)).setBlocksize(32 * 1024)
//                .setCompressionType(Compression.Algorithm.SNAPPY).setDataBlockEncoding(DataBlockEncoding.NONE);
//        tableDescBuilder.setColumnFamily(columnDescBuilder.build());
//        tableDescBuilder.build();
//        HBaseAdmin
//        System.out.println(tableDescBuilder.build().isReadOnly());
        Connection connection = ConnectionFactory.createConnection();
        //线程不安全的
        Admin admin = connection.getAdmin();

        //创建表名描述
        TableName tableName = TableName.valueOf("default:tb_evt");

        //创建表描述符
        TableDescriptorBuilder tableBuilder = TableDescriptorBuilder.newBuilder(tableName);

        //创建列祖描述符
        ColumnFamilyDescriptor cf = ColumnFamilyDescriptorBuilder.newBuilder("cf".getBytes()).build();

        //这个才是表描述符
        TableDescriptor table = tableBuilder.setColumnFamily(cf).build();
        admin.createTable(table);
        admin.close();
        connection.close();
    }
}
