import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NetBenefitTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final Path path = Paths.get(NetBenefitTest.class.getResource(".").toURI());

    public NetBenefitTest() throws URISyntaxException {
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void checkNetBenefitFile1() {
        NetBenefit.main(new String[]{path.getParent() + "\\TestingFiles\\stock1.txt"});

        Assert.assertEquals("Total Profit: 680", output.toString().trim());
    }

    @Test
    public void checkNetBenefitFile2() {
        NetBenefit.main(new String[]{path.getParent() + "\\TestingFiles\\stock2.txt"});

        Assert.assertEquals("Total Profit: 510", output.toString().trim());
    }

    @Test
    public void checkNetBenefitFile3() {
        NetBenefit.main(new String[]{path.getParent() + "\\TestingFiles\\stock3.txt"});

        Assert.assertEquals("Not sufficient amount to sell", output.toString().trim());
    }

    @Test
    public void checkNetBenefitFile4() {
        NetBenefit.main(new String[]{path.getParent() + "\\TestingFiles\\stock4.txt"});
        
        Assert.assertEquals("Total Profit: -254", output.toString().trim());
    }

}
