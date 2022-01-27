import javax.sound.sampled.SourceDataLine;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {
    // check if isEmpty() words
    @Test
    public void checkIfEmpty() {
        LinkedList linkedList = new LinkedList();
        Assert.assertTrue(linkedList.isEmpty());
    }

    // Checks if returns the correct size and if it inserts items correctly
    @Test
    public void checkInsert() {
        LinkedList linkedList = new LinkedList();

        Assert.assertTrue(linkedList.size() == 0);

        linkedList.put("a");
        linkedList.put("b");
        linkedList.put("c");
        linkedList.put("d");

        Assert.assertTrue(linkedList.head.getData() == "d");
        Assert.assertTrue(linkedList.head.getNext().getData() == "c");
        Assert.assertTrue(linkedList.head.getNext().getNext().getData() == "b");
        Assert.assertTrue(linkedList.head.getNext().getNext().getNext().getData() == "a");
        Assert.assertTrue(linkedList.head.getNext().getNext().getNext().getNext() == null);
        Assert.assertTrue(linkedList.size() == 4);
    }

    // Checks if the items are removed correctly from the linkedlist
    @Test
    public void checkRemove() {
        LinkedList linkedList = new LinkedList();

        linkedList.put("a");
        linkedList.put("b");
        linkedList.put("c");
        linkedList.put("d");

        linkedList.remove("c");

        Assert.assertTrue(linkedList.head.getData() == "d");
        Assert.assertTrue(linkedList.head.getNext().getData() == "b");
        Assert.assertTrue(linkedList.head.getNext().getNext().getData() == "a");
        Assert.assertTrue(linkedList.head.getNext().getNext().getNext() == null);
        Assert.assertTrue(linkedList.size() == 3);

        linkedList.remove("d");
        Assert.assertTrue(linkedList.head.getData() == "b");
        Assert.assertTrue(linkedList.head.getNext().getData() == "a");
        Assert.assertTrue(linkedList.head.getNext().getNext() == null);
        Assert.assertTrue(linkedList.size() == 2);

        linkedList.remove("a");
        linkedList.remove("b");
        Assert.assertTrue(linkedList.head == null);
        Assert.assertTrue(linkedList.size() == 0);
    }
}
