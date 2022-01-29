import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class BSTTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final Path path = Paths.get(BSTTest.class.getResource(".").toURI());

    public BSTTest() throws URISyntaxException {}

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }
    // checks if nodes are insterted correctly in the BST
    // and have the correct subtree sizes
    @Test
    public void checkInsert() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");

        Assert.assertTrue(bst.head.getData().key() == "d" && bst.head.getSubtreeSize() == 5);
        Assert.assertTrue(bst.head.left().getData().key() == "c" && bst.head.left().getSubtreeSize() == 1);
        Assert.assertTrue(bst.head.right().getData().key() == "z" && bst.head.right().getSubtreeSize() == 2);
        Assert.assertTrue(bst.head.left().left().getData().key() == "a" && bst.head.left().left().getData().getOccurrences() == 2 && bst.head.left().left().getSubtreeSize() == 0);
        Assert.assertTrue(bst.head.right().left().getData().key() == "f" && bst.head.right().left().getSubtreeSize() == 1);
        Assert.assertTrue(bst.head.right().left().right().getData().key() == "g" && bst.head.right().left().right().getSubtreeSize() == 0);
    }

    // checks if the search functions works properly
    @Test
    public void checkSearch() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("a");
        bst.insert("a");

        bst.search("a");

        Assert.assertTrue(bst.head.getData().key() == "a" && bst.head.getSubtreeSize() == 5);
        Assert.assertTrue(bst.head.left() == null);
        Assert.assertTrue(bst.head.right().getData().key() == "d" && bst.head.right().getSubtreeSize() == 4);
        Assert.assertTrue(bst.head.right().left().getData().key() == "c" && bst.head.right().left().getSubtreeSize() == 0);
        Assert.assertTrue(bst.head.right().right().getData().key() == "z" && bst.head.right().right().getSubtreeSize() == 2);
        Assert.assertTrue(bst.head.right().right().left().getData().key() == "f" && bst.head.right().right().left().getSubtreeSize() == 1);
        Assert.assertTrue(bst.head.right().right().left().right().getData().key() == "g" && bst.head.right().right().left().right().getSubtreeSize() == 0);
    }

    // checks if correctly removes items from the subtree and the subtree sizes are correct
    @Test
    public void checkRemove() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");

        bst.remove("z");
        
        Assert.assertTrue(bst.head.getData().key() == "d" && bst.head.getSubtreeSize() == 4);
        Assert.assertTrue(bst.head.right().getData().key() == "f" && bst.head.right().getSubtreeSize() == 1);
        Assert.assertTrue(bst.head.right().right().getData().key() == "g" && bst.head.right().right().getSubtreeSize() == 0);

        bst.remove("d");
        Assert.assertTrue(bst.head.getData().key() == "f" && bst.head.getSubtreeSize() == 3);
        Assert.assertTrue(bst.head.right().getData().key() == "g" && bst.head.right().getSubtreeSize() == 0);
        Assert.assertTrue(bst.head.left().getData().key() == "c" && bst.head.left().getSubtreeSize() == 1);
    }

    // checks if it returns the correct number of total words
    @Test
    public void checkTotalWords() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("f");

        Assert.assertTrue(bst.getTotalWords() == 8);

        bst.remove("a");
        Assert.assertTrue(bst.getTotalWords() == 6);

        bst.remove("f");
        Assert.assertTrue(bst.getTotalWords() == 4);
    }

    // checks if it returns the correct number of distinct words
    @Test
    public void checkDistinctWords() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("f");

        Assert.assertTrue(bst.getDistinctWords() == 6);

        bst.remove("a");
        Assert.assertTrue(bst.getDistinctWords() == 5);

        bst.remove("f");
        Assert.assertTrue(bst.getDistinctWords() == 4);
    }

    // checks if it returns the correct frequency of a word
    @Test
    public void checkFrequency() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("f");
        bst.insert("a");

        Assert.assertTrue(bst.getFrequency("a") == 3);
        Assert.assertTrue(bst.getFrequency("f") == 2);
        Assert.assertTrue(bst.getFrequency("g") == 1);
    }

    // checks if it returns the correct max frequency
    @Test
    public void checkMaxFrequency() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("f");
        bst.insert("a");

        WordFreq maxWord = bst.getMaximumFrequency();
        Assert.assertTrue(maxWord.key() == "a" && maxWord.getOccurrences() == 3);
        bst.remove("a");

        maxWord = bst.getMaximumFrequency();
        Assert.assertTrue(maxWord.key() == "f" && maxWord.getOccurrences() == 2);
    }

    // checks if it returns the correct mean frequency
    @Test
    public void checkMeanFrequency() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("f");
        bst.insert("a");

        Assert.assertTrue(bst.getMeanFrequency() == 1.5);

        bst.remove("a");
        Assert.assertTrue(bst.getMeanFrequency() == 1.2);
    }

    // checks if reads data from files correctly and builds correctly the BST
    @Test
    public void checkLoadFile1() {
        BST bst = new BST();
        
        bst.load(path.getParent() + "\\TestingFiles\\test1.txt");
        bst.search("you");
        
        Assert.assertTrue(bst.getMaximumFrequency().key() == bst.head.getData().key() && bst.getMaximumFrequency().getOccurrences() == bst.head.getData().getOccurrences());
        Assert.assertTrue(bst.getDistinctWords() == 15);
        Assert.assertTrue(bst.getTotalWords() == 22);

        bst = new BST();
        
        bst.addStopWord("you");
        bst.load(path.getParent() + "\\TestingFiles\\test1.txt");

        bst.search("how");
        bst.search("you");
        Assert.assertTrue(bst.head.getData().key().equals("how") && bst.head.getData().getOccurrences() == 3);
        Assert.assertTrue(bst.getDistinctWords() == 14);
        Assert.assertTrue(bst.getTotalWords() == 17);
    }
    // checks if it prints alphabetically correctly
    @Test
    public void checkPrintAlphabetically() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("f");
        bst.insert("a");

        bst.printTreeAlphabetically(System.out);
        Assert.assertEquals("a: 3 -> c: 1 -> d: 1 -> f: 2 -> g: 1 -> z: 1 -> NULL", output.toString().trim());
    }

    // checks if it prints by frequency correctly
    @Test
    public void checkPrintByFrequency() {
        BST bst = new BST();

        bst.insert("d");
        bst.insert("c");
        bst.insert("z");
        bst.insert("a");
        bst.insert("f");
        bst.insert("a");
        bst.insert("g");
        bst.insert("f");
        bst.insert("a");

        bst.printTreeByFrequency(System.out);
        Assert.assertEquals("z: 1 -> g: 1 -> c: 1 -> d: 1 -> f: 2 -> a: 3 -> NULL", output.toString().trim());
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
    }
}
