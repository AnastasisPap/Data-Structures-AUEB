import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TestTagMatching {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final Path path = Paths.get(TestTagMatching.class.getResource(".").toURI());

    public TestTagMatching() throws URISyntaxException {
    }


    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void checkTagMatchingFile1() {
        TagMatching.main(new String[]{path.getParent() + "\\TestingFiles\\file1.html"});

        Assert.assertEquals("The given html file has matched tags.", output.toString().trim());
    }

    @Test
    public void checkingTagMatchingFile2() {
        TagMatching.main(new String[]{path.getParent() + "\\TestingFiles\\file2.html"});

        Assert.assertEquals("The given html file has matched tags.", output.toString().trim());
    }

    @Test
    public void checkingTagMatchingFile3() {
        TagMatching.main(new String[]{path.getParent() + "\\TestingFiles\\file3.html"});

        Assert.assertEquals("The given html file does not have matched tags.", output.toString().trim());
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
    }

}
