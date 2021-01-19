package nonaction.multy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ligc on 2020/12/21 17:18
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        logger.warn("hello, logger{}", "ligc");
        System.out.println(1);
    }
}
