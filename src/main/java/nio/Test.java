package nio;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ligc on 2021/2/5 22:13
 */
public class Test {
    private final static String CONTENT = "Zero copy implemented by MappedByteBuffer";
    private final static String FILE_NAME = "/mmpa.txt";
    private final static String CHARSET = "UTF-8";

    public void writeToFileByMappedByteBuffer(){
        Path path = Paths.get(getClass().getResource(FILE_NAME).getPath());
        byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));

    }
}
