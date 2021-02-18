package hdfs;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * Created by ligc on 2021/1/31 14:31
 */
public class Util {
    public static void main(String[] args) throws IOException {
        createFiles(10, 128, "D:\\ligc\\data");
    }

    public static void createFiles(int nums, int size, String dir) throws IOException {
        String userNum;
        String relateNum;
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

        File fileParent = new File(dir);
        if(!fileParent.exists()){
            fileParent.mkdir();
        }
        String filePath;
        for(int i = 0; i < nums; i ++){
            //每次1024KB
            File file = new File(dir + File.separator + i + ".txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileChannel channel = new FileOutputStream(dir + File.separator + i +
                    ".txt").getChannel();
            for(int j = 0; j < size; j ++){
                while(buffer.position() < 1024 * 1024 - 27){
                    buffer.put(createUserNum(7).getBytes());
                    buffer.put("\t".getBytes());
                    buffer.put(createUserNum(7).getBytes());
                    buffer.put(System.lineSeparator().getBytes());
                }
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }
            channel.close();
        }

    }

    public static String createUserNum(int count){
        StringBuffer sb = new StringBuffer();
        int now;
        for(int i = 0; i < count; i ++){
            now = (int)(Math.random() * 9);
            sb.append(new StringBuffer(now + ""));
        }
        return  sb.toString();
    }
}
