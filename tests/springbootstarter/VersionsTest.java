package springbootstarter;

import springbootstarter.validation.Version;

public class VersionsTest extends SpringBootTestCase{
    public void testVersionComparator() {
        assertTrue(new Version("3.1.6").compareTo(new Version("3.1.7 (SNAPSHOT)")) < 0);
        assertTrue(new Version("3.2.0 (SNAPSHOT)").compareTo(new Version("3.1.7 (SNAPSHOT)")) > 0);
        assertTrue(new Version("3.2.0 (SNAPSHOT)").compareTo(new Version("3.2.0 (SNAPSHOT)")) == 0);
    }

    public void testVersionCorrectness() {
        try {
            new Version("1.2.A");
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            new Version("1.2.3-SNAPSHAPE");
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Version("1.2.3-SNAPSHOT");
        new Version("1.2.3-M1");
        new Version("1.2.333-RELEASE");
    }

}
