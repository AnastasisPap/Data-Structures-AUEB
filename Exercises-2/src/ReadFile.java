import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static FileContents readFile(String strings) {
        // If the file is opened correctly
        try {
            BufferedReader in = new BufferedReader(new FileReader(strings));
            // Stores one line of the file
            String content;

            int totalProcessors, totalTasks;
            // The first line is the number of processors
            totalProcessors = Integer.parseInt(in.readLine());
            // The second line is the number of tasks
            totalTasks = Integer.parseInt(in.readLine());
            // Create new object
            FileContents fileContents = new FileContents(totalTasks, totalProcessors);

            // Stores all the ids of the tasks that will be read
            int[] ids = new int[totalTasks];
            // Stores all the times of the tasks that will be read
            int[] tasks = new int[totalTasks];
            // task counter
            int i = 0;

            while ((content = in.readLine()) != null) {
                // parts[0] = ids, parts[1] = times
                String[] parts = content.split(" ");
                
                // store the information to the arrays
                ids[i] = Integer.parseInt(parts[0]);
                tasks[i] = Integer.parseInt(parts[1]);
                i++;
            }

            // the format of the file is correct if the tasks read are as many as they file stated in the second line
            fileContents.setFormat(i);
            // add ids to the fileContents object
            fileContents.setIds(ids);
            // add task times to the fileContents object
            fileContents.setTasks(tasks);

            in.close();

            return fileContents;
        } catch (IOException e) {
            // Create new object
            FileContents fileContents = new FileContents(false);
            // add error message to the object and set format to false (not correct format)
            fileContents.setErrorMessage(e.getMessage());
            return fileContents;
        }
    }
}
