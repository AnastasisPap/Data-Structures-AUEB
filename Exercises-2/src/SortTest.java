import org.junit.Assert;
import org.junit.Test;

public class SortTest {

    // Test if quicksort works correctly
    @Test
    public void checkSort() {
        Task[] tasks = {new Task(1, 25), new Task(2, 30), new Task(3, 20), new Task(4, 35), new Task(5, 50)};
        int[] sortedTimes = {50, 35, 30, 25, 20};

        Sort.quickSort(tasks, 0, tasks.length-1);

        for (int i = 0; i < tasks.length; i++) {
            Assert.assertTrue(tasks[i].getTime() == sortedTimes[i]);
        }
    }
    
}
