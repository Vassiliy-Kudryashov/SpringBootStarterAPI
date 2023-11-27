package springbootstarter;

import junit.framework.TestCase;
import springbootstarter.util.IOUtil;

public abstract class SpringBootTestCase extends TestCase {

    @Override
    protected void setUp() throws Exception {
        System.setProperty(IOUtil.IO_LOCAL_KEY, "true");
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setProperty(IOUtil.IO_LOCAL_KEY, "false");
    }

    static void assertFails(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            System.out.println("Expected exception has been occurred: " + e);
            return;
        }
        fail("Expected exception is absent");
    }
}
