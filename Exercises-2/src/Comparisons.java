import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Comparisons {

    private static int runAlgorithm(FileContents contents, Task[] tasks) {
        MaxPQ maxPQ = Greedy.makePriorityQueue(contents, tasks);
        int makespan = maxPQ.getMakespan();
        return makespan;
    }

    private static FileContents getContents(String filePath) {
        FileContents contents = ReadFile.readFile(filePath);
        return contents;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Generating files...");
        System.out.println();
        int[] taskNumbers = {100, 250, 500};
        int fileNumber = 10;
        GenereateFiles.Generate(taskNumbers, fileNumber);
        
        String directory_path = Paths.get("").toAbsolutePath().toString() + "/Random_Files";

        for (int i = 0; i < taskNumbers.length; i++) {
            int makeSpan1Sum = 0;
            int makeSpan2Sum = 0;
            for (int j = 0; j < fileNumber; j++) {
                String fileName = directory_path + "/" + taskNumbers[i] + "_tasks/" + taskNumbers[i] + "_tasks_" + (j+1) + ".txt";
                FileContents contents = getContents(fileName);
                Task[] tasks = Greedy.makeTasks(contents);
                
                int makespan1 = runAlgorithm(contents, tasks);
                
                Sort.quickSort(tasks, 0, contents.getTotalTasks() - 1);
                int makespan2 = runAlgorithm(contents, tasks);

                makeSpan1Sum += makespan1;
                makeSpan2Sum += makespan2;
            }
            float makeSpan1Mean = (float) makeSpan1Sum / fileNumber;
            float makeSpan2Mean = (float) makeSpan2Sum / fileNumber;

            System.out.println("Mean makespan for the first algorithm (N=" + taskNumbers[i] + "): " + makeSpan1Mean);
            System.out.println("Mean makespan for the second algorithm (N=" + taskNumbers[i] + "): " + makeSpan2Mean);
            System.out.println("The second algorithm is faster by " + (makeSpan1Mean - makeSpan2Mean) + " time units.");
            System.out.println();
        }
    }
}
