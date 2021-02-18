package jvm.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by ligc on 2021/2/18 10:06
 * 管道输入输出
 * 主要用于线程之间的数据传输，而传输的媒介是内存
 */
public class Piped {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        //将输入流和输出流进程连接
        out.connect(in);
        Thread printThread = new Thread(new Print(in), "printThread");
        printThread.start();
        int receive = 0;
        try{
            while((receive = System.in.read()) != -1){
                out.write(receive);
            }
        }finally {
            out.close();
        }
    }

    static class Print implements Runnable{
        private PipedReader in;

        public Print(PipedReader in){
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try{
                while((receive = in.read()) != -1){
                    System.out.println((char) receive);
                }
            }catch (IOException e){

            }
        }
    }
}
