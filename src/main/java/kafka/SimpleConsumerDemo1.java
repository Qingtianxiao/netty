package kafka;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import org.apache.kafka.common.requests.MetadataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by ligc on 2020/12/29 22:25
 */
public class SimpleConsumerDemo1 {
   private List<String> m_replicaBrokers = new ArrayList<String>();
   private static final Logger logger = LoggerFactory.getLogger(SimpleConsumerDemo1.class);

   public SimpleConsumerDemo1(){

   }

    public static void main(String[] args) {
       SimpleConsumerDemo1 example = new SimpleConsumerDemo1();
       //最大读取消息量
        long maxReads = Long.parseLong("3");
        //要订阅的topic
        String topic = "DW_TB_CDR.V001";
        int partition = Integer.parseInt("0");

        List<String> seeds = new ArrayList<>();
        seeds.add("192.168.137.129");
        seeds.add("192.168.137.130");
        int port = 9092;
        try{
//            example.run(maxReads, topic, partition,seeds,port);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

//    private void run(long maxReads, String topic, int partition, List<String> seeds, int port) {
//       //获取指定topic-partition的元数据
//        MetadataResponse.PartitionMetadata metadata = findLeader(seeds, port, topic, partition);
//        if(metadata == null){
//            logger.info("can't find metadata for Topic:{}, Partition:{}",topic,partition);
//            return;
//        }
//        String leaderBroker = metadata.leader().host();
//        String clientName = "Client_" + topic + "_" + partition;
//
//        SimpleConsumer consumer = new SimpleConsumer(leaderBroker, port,100000, 64 * 1024,
//                clientName);
//        long readOffset = getLastOffset(consumer, topic, partition, kafka.api.OffsetRequest.EarliestTime(),clientName);
//
//        int numErrors = 0;
//        while(maxReads > 0){
//            if(consumer == null){
//                consumer = new SimpleConsumer(leaderBroker, port,100000, 64 * 1024, clientName);
//            }
//
//            FetchRequest req = new FetchRequestBuilder().clientId(clientName).addFetch(topic, partition, readOffset,
//                    100000).build();
//            FetchResponse fetchResponse = consumer.fetch(req);
//
//            if(fetchResponse.hasError()){
//                numErrors ++;
//
//            }
//
//        }
//    }

}
