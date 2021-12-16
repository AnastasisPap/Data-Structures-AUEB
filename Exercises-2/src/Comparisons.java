import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

public class Comparisons {

    // Run the algorithm (sorted or not)
    private static int runAlgorithm(FileContents contents, Task[] tasks) {
        // create the priority queue with the tasks inserted in the given order
        MaxPQ maxPQ = Greedy.makePriorityQueue(contents, tasks);
        // get the makespan of the priority queue
        int makespan = maxPQ.getMakespan();
        return makespan;
    }

    // read the contents of the file
    private static FileContents getContents(String filePath) {
        FileContents contents = ReadFile.readFile(filePath);
        return contents;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Generating files...");
        System.out.println();
        // Number of tasks
        int[] taskNumbers = {100, 250, 500};
        // Number of files for each N
        int fileNumber = 10;
        // Create the directories and files
        GenereateFiles.Generate(taskNumbers, fileNumber);
        
        // Get the Random_Files directory
        String directory_path = Paths.get("").toAbsolutePath().toString() + "/Random_Files";

        // For every N
        for (int i = 0; i < taskNumbers.length; i++) {
            // variable that has the sum of all makespans for each N (first Algo)
            int makeSpan1Sum = 0;
            // variable that has the sum of all makespans for each N (second Algo)
            int makeSpan2Sum = 0;

            // For every file in directory N_tasks
            for (int j = 0; j < fileNumber; j++) {
                // Read the contents of that file
                String fileName = directory_path + "/" + taskNumbers[i] + "_tasks/" + taskNumbers[i] + "_tasks_" + (j+1) + ".txt";
                FileContents contents = getContents(fileName);
                Task[] tasks = Greedy.makeTasks(contents);
                
                // Run the first algorithm and get the makespan
                int makespan1 = runAlgorithm(contents, tasks);
                
                // Run the second algorithm (first sort the tasks then run the algorithm on the sorted tasks) and save the makespan
                Sort.quickSort(tasks, 0, contents.getTotalTasks() - 1);
                int makespan2 = runAlgorithm(contents, tasks);

                // and current makespan to the appropriate variable (will be used for the means)
                makeSpan1Sum += makespan1;
                makeSpan2Sum += makespan2;
            }
            // Get the means
            float makeSpan1Mean = (float) makeSpan1Sum / fileNumber;
            float makeSpan2Mean = (float) makeSpan2Sum / fileNumber;

            // Print results
            System.out.println("Mean makespan for the first algorithm (N=" + taskNumbers[i] + "): " + makeSpan1Mean);
            System.out.println("Mean makespan for the second algorithm (N=" + taskNumbers[i] + "): " + makeSpan2Mean);
            System.out.println("The second algorithm is faster by " + (makeSpan1Mean - makeSpan2Mean) + " time units.");
            System.out.println();
        }
    }
}
