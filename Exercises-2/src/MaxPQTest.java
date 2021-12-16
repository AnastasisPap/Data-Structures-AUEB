import org.junit.Assert;
import org.junit.Test;

public class MaxPQTest {

    // check if isEmpty() works
    @Test
    public void checkIfEmpty() {
        MaxPQ maxPQ = new MaxPQ(1);
        Assert.assertTrue(maxPQ.isEmpty());
    }
    
    // Checks if returns the correct size and if it outputs the corrent makespan
    @Test
    public void checkInsert() {
        MaxPQ maxPQ = new MaxPQ(3);

        Processor p1 = new Processor(1);
        Processor p2 = new Processor(2);
        Processor p3 = new Processor(3);

        p1.addTask(new Task(1, 30));
        p2.addTask(new Task(2, 20));
        p3.addTask(new Task(3, 10));

        maxPQ.insert(p1);
        maxPQ.insert(p2);
        maxPQ.insert(p3);

        Assert.assertTrue(maxPQ.size() == 3);
        Assert.assertTrue(maxPQ.getMakespan() == 30);
    }

    // Checks if returns the correct item (highest priority)
    @Test
    public void checkGetMax() {
        MaxPQ maxPQ = new MaxPQ(3);

        Processor p1 = new Processor(1);
        Processor p2 = new Processor(2);
        Processor p3 = new Processor(3);

        p1.addTask(new Task(1, 30));
        p2.addTask(new Task(2, 20));
        p3.addTask(new Task(3, 10));

        maxPQ.insert(p1);
        maxPQ.insert(p2);
        maxPQ.insert(p3);

        p1 = maxPQ.getmax();
        p2 = maxPQ.getmax();
        p3 = maxPQ.getmax();

        Assert.assertTrue(maxPQ.size() == 0);
        Assert.assertTrue((p1.getActiveTime() == 10) && (p2.getActiveTime() == 20) && (p3.getActiveTime() == 30));
    }
}
