import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AlgorithmTest {

    private final Path path = Paths.get(AlgorithmTest.class.getResource(".").toURI());

    public AlgorithmTest() throws URISyntaxException {}

    @Test
    public void algorithmTest1() {
        String filePath = path.getParent() + "\\TestingFiles\\data.txt";
        FileContents contents = ReadFile.readFile(filePath);

        Task[] tasks = Greedy.makeTasks(contents);
        MaxPQ maxPQ = Greedy.makePriorityQueue(contents, tasks);
        Processor[] processors = maxPQ.getProcessors();
        
        Processor[] correctProcessors = new Processor[contents.getProcessorCount() + 1];
        Processor p1 = new Processor(1);
        p1.addTask(new Task(12, 25));
        p1.addTask(new Task(4, 50));

        Processor p2 = new Processor(2);
        p2.addTask(new Task(1, 30));

        Processor p3 = new Processor(3);
        p3.addTask(new Task(32, 20));
        p3.addTask(new Task(128, 35));

        correctProcessors[1] = p2;
        correctProcessors[2] = p3;
        correctProcessors[3] = p1;

        for (int i = 1; i < correctProcessors.length; i++) {
            Assert.assertTrue(correctProcessors[i].haveSameTasks(processors[i]));
        }   
    }

    @Test
    public void algorithmTest1Sorted() {
        String filePath = path.getParent() + "\\TestingFiles\\data.txt";
        FileContents contents = ReadFile.readFile(filePath);

        Task[] tasks = Greedy.makeTasks(contents);
        Sort.quickSort(tasks, 0, tasks.length-1);
        MaxPQ maxPQ = Greedy.makePriorityQueue(contents, tasks);
        Processor[] processors = maxPQ.getProcessors();
        
        Processor[] correctProcessors = new Processor[contents.getProcessorCount() + 1];
        Processor p1 = new Processor(1);
        p1.addTask(new Task(4, 50));

        Processor p2 = new Processor(2);
        p2.addTask(new Task(128, 35));
        p2.addTask(new Task(32, 20));

        Processor p3 = new Processor(3);
        p3.addTask(new Task(1, 30));
        p3.addTask(new Task(12, 25));

        correctProcessors[1] = p1;
        correctProcessors[2] = p3;
        correctProcessors[3] = p2;

        for (int i = 1; i < correctProcessors.length; i++) {
            Assert.assertTrue(correctProcessors[i].haveSameTasks(processors[i]));
        }
    }

    @Test
    public void algorithmTest2() {
        String filePath = path.getParent() + "\\TestingFiles\\data2.txt";
        FileContents contents = ReadFile.readFile(filePath);

        Task[] tasks = Greedy.makeTasks(contents);
        MaxPQ maxPQ = Greedy.makePriorityQueue(contents, tasks);
        Processor[] processors = maxPQ.getProcessors();
        
        Processor[] correctProcessors = new Processor[contents.getProcessorCount() + 1];
        Processor p1 = new Processor(1);
        Processor p2 = new Processor(2);
        Processor p3 = new Processor(3);
        Processor p4 = new Processor(4);

        p1.addTask(new Task(1, 97));
        p1.addTask(new Task(8, 83));

        p2.addTask(new Task(2, 50));
        p2.addTask(new Task(6, 68));
        p2.addTask(new Task(11, 34));
        p2.addTask(new Task(13, 58));

        p3.addTask(new Task(3, 36));
        p3.addTask(new Task(5, 80));
        p3.addTask(new Task(10, 78));

        p4.addTask(new Task(4, 56));
        p4.addTask(new Task(7, 57));
        p4.addTask(new Task(9, 36));
        p4.addTask(new Task(12, 57));

        correctProcessors[1] = p1;
        correctProcessors[2] = p3;
        correctProcessors[3] = p4;
        correctProcessors[4] = p2;


        for (int i = 1; i < correctProcessors.length; i++) {
            Assert.assertTrue(correctProcessors[i].haveSameTasks(processors[i]));
        }
    }

    @Test
    public void algorithmTest2Sorted() {
        String filePath = path.getParent() + "\\TestingFiles\\data2.txt";
        FileContents contents = ReadFile.readFile(filePath);

        Task[] tasks = Greedy.makeTasks(contents);
        Sort.quickSort(tasks, 0, tasks.length - 1);
        MaxPQ maxPQ = Greedy.makePriorityQueue(contents, tasks);
        Processor[] processors = maxPQ.getProcessors();
        
        Processor[] correctProcessors = new Processor[contents.getProcessorCount() + 1];
        Processor p1 = new Processor(1);
        Processor p2 = new Processor(2);
        Processor p3 = new Processor(3);
        Processor p4 = new Processor(4);

        p1.addTask(new Task(1, 97));
        p1.addTask(new Task(7, 57));
        p1.addTask(new Task(9, 36));
        
        p2.addTask(new Task(8, 83));
        p2.addTask(new Task(12, 57));
        p2.addTask(new Task(2, 50));

        p3.addTask(new Task(5, 80));
        p3.addTask(new Task(13, 58));
        p3.addTask(new Task(4, 56));

        p4.addTask(new Task(10, 78));
        p4.addTask(new Task(6, 68));
        p4.addTask(new Task(3, 36));
        p4.addTask(new Task(11, 34));

        correctProcessors[1] = p1;
        correctProcessors[2] = p2;
        correctProcessors[3] = p3;
        correctProcessors[4] = p4;

        for (int i = 1; i < correctProcessors.length; i++) {
            Assert.assertTrue(correctProcessors[i].haveSameTasks(processors[i]));
        }
    }
}
