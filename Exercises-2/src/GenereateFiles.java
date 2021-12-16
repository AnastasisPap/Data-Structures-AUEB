import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenereateFiles {

    // Firstly make the files
    public static void Generate(int[] taskNumbers, int fileNumber) throws FileNotFoundException, UnsupportedEncodingException {
        // The directory with all the other subfolders will be called random files
        String directory_path = Paths.get("").toAbsolutePath().toString() + "/Random_Files";

        // make a directory for every N we have (in our case 100, 250, 500) 
        for (int i = 0; i < taskNumbers.length; i++)
        {
            // make the directory
            makeDirectory(directory_path, taskNumbers[i]);
            // for every N its subdirectory will have 10 files, make these files and put them in their folder
            makeFiles(taskNumbers[i], directory_path, fileNumber);
        }
    }

    // create a directory
    private static void makeDirectory(String path, int N) {
        // get the path of the Random_Files directory and append the N_tasks/ name in the end
        Path path1 = Paths.get(path + "/" + N + "_tasks/");

        // if there isn't such directory, create it
        if (Files.notExists(path1)) {
            
            new File(path1.toString()).mkdirs();
        }
    }

    // Create the files and write the info in them
    private static void makeFiles(int totalTasks, String directory_path, int fileNumber) throws FileNotFoundException, UnsupportedEncodingException {
        // Total processors
        int totalProcessors = (int) Math.floor(Math.sqrt(totalTasks));
        
        // Create fileNumber files with similar properties (same N, same # of processors)
        for (int k = 1; k < fileNumber + 1; k++) {
            // the file name will be X_tasks_i.txt
            String fileName = totalTasks + "_tasks_" + k + ".txt";
            // create the file
            File file = new File(directory_path + "/" + totalTasks + "_tasks/" + fileName);
            // Start writing to the file
            PrintWriter writer = new PrintWriter(file, "UTF-8");

            // Write first line (number of processors)
            writer.println(totalProcessors);
            // Write second line (number of tasks)
            writer.println(totalTasks);

            // Get an array of random tasks
            Task[] tasks = makeTasks(totalTasks);
            // append each task to the file
            for (int i = 0; i < totalTasks; i++)
                writer.println(tasks[i].getId() + " " + tasks[i].getTime());
            
            writer.close();
        }
    }

    // make an array of randomized tasks (the times will be random)
    private static Task[] makeTasks(int N) {
        Task[] tasks = new Task[N];
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            // get random time
            int taskTime = rand.nextInt(1, N+1);
            // task i will have id of i and a random time
            tasks[i] = new Task(i, taskTime);
        }

        return tasks;
    }
}
