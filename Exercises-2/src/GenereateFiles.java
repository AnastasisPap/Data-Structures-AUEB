import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenereateFiles {

    public static void Generate(int[] taskNumbers, int fileNumber) throws FileNotFoundException, UnsupportedEncodingException {

        String directory_path = Paths.get("").toAbsolutePath().toString() + "/Random_Files";

        for (int i = 0; i < taskNumbers.length; i++)
        {
            makeDirectory(directory_path, taskNumbers[i]);
            makeFiles(taskNumbers[i], directory_path, fileNumber);
        }
    }

    private static void makeDirectory(String path, int N) {
        Path path1 = Paths.get(path + "/" + N + "_tasks/");

        if (Files.notExists(path1)) {
            
            new File(path1.toString()).mkdirs();
        }
    }

    private static void makeFiles(int totalTasks, String directory_path, int fileNumber) throws FileNotFoundException, UnsupportedEncodingException {
        int totalProcessors = (int) Math.floor(Math.sqrt(totalTasks));
        
        for (int k = 1; k < fileNumber + 1; k++) {
            String fileName = totalTasks + "_tasks_" + k + ".txt";
            File file = new File(directory_path + "/" + totalTasks + "_tasks/" + fileName);
            PrintWriter writer = new PrintWriter(file, "UTF-8");

            writer.println(totalProcessors);
            writer.println(totalTasks);

            Task[] tasks = makeTasks(totalTasks);
            for (int i = 0; i < totalTasks; i++)
                writer.println(tasks[i].getId() + " " + tasks[i].getTime());
            
            writer.close();
        }
    }

    private static Task[] makeTasks(int N) {
        Task[] tasks = new Task[N];
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            int taskTime = rand.nextInt(1, N+1);
            tasks[i] = new Task(i, taskTime);
        }

        return tasks;
    }
}
