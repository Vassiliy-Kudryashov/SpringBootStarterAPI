package springbootstarter;

import springbootstarter.util.WebFragmentParser;

import java.io.IOException;
import java.net.URISyntaxException;

public class WebFragmentParserTest extends SpringBootTestCase {
    public void testBasic() throws IOException, URISyntaxException {
        WebFragmentParser.parseResourceFile();//get coverage with local snapshots
    }
}
