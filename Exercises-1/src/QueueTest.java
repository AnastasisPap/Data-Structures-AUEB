import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;


public class QueueTest {

    // Check if puts correctly the items and check if peek() returns the oldest item
    @Test
    public void putBack() {
        IntQueueImpl<Integer> queue = new IntQueueImpl<>();

        queue.put(1);
        queue.put(2);

        Assert.assertTrue(queue.peek() == 1);
        queue.get();
        Assert.assertTrue(queue.peek() == 2);
    }

    // Check if isEmpty works
    @Test
    public void checkIfEmpty() {
        IntQueueImpl<Integer> queue = new IntQueueImpl<>();

        Assert.assertTrue(queue.isEmpty());
        queue.put(1);
        queue.put(2);
        queue.put(3);

        Assert.assertTrue(!queue.isEmpty());
    }

    // Check if remove item works
    @Test
    public void checkRemove() {
        IntQueueImpl<Integer> queue = new IntQueueImpl<>();

        queue.put(1);
        queue.get();

        Assert.assertTrue(queue.isEmpty());

        queue.put(1);
        queue.put(2);
        Assert.assertTrue(1 == queue.get());
    }

    // Check if throws exception if try to remove from empty queue
    @Test(expected = NoSuchElementException.class)
    public void removeFromEmpty() {
        IntQueueImpl<String> queue = new IntQueueImpl<>();

        queue.get();
    }

    // Checks if throws exception if trying to get the latest item from empty queue
    @Test(expected = NoSuchElementException.class)
    public void readFromEmpty() {
        IntQueueImpl<String> queue = new IntQueueImpl<>();

        System.out.println(queue.peek());
    }

    // Test storing order
    @Test
    public void checkStore() {
        IntQueueImpl<Integer> queue = new IntQueueImpl<>();

        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.get();

        String output = "";

        while (!queue.isEmpty()) {
            int num = queue.get();
            output += num;
        }

        Assert.assertEquals(output, "234");
    }

    // Test the if length works
    @Test
    public void checkLength() {
        IntQueueImpl<Integer> queue = new IntQueueImpl<>();

        Assert.assertTrue(queue.size() == 0);

        queue.put(1);
        queue.put(2);
        queue.put(3);

        Assert.assertTrue(queue.size() == 3);

        queue.get();
        queue.get();

        Assert.assertTrue(queue.size() == 1);
    }
}
