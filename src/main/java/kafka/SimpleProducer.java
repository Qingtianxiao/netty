package kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import zookeeper.ZkStat;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by ligc on 2021/1/21 21:48
 */
public class SimpleProducer {
    private static Properties prop = ZkStat.kafkaProducerInit();
    private static String topic = "wtf";

    private static String messages;

    static {
        try {
            messages = new String(ZkStat.mysqlInit());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public SimpleProducer() throws SQLException, ClassNotFoundException {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        KafkaProducer producer = new KafkaProducer(prop);
        System.out.println(messages);
        producer.send(new ProducerRecord(topic, messages), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e != null){
                    e.printStackTrace();
                    System.out.println(e);
                }
                if(recordMetadata != null){
                    System.out.println(recordMetadata.topic() +  " " + recordMetadata.partition());
                }
            }
        });

        Thread.sleep(Integer.MAX_VALUE);
    }
}
