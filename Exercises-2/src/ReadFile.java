import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static FileContents readFile(String filePath) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String content;

            int totalProcessors, totalTasks;
            totalProcessors = Integer.parseInt(in.readLine());
            totalTasks = Integer.parseInt(in.readLine());
            FileContents fileContents = new FileContents(totalTasks, totalProcessors);

            int[] ids = new int[totalTasks];
            int[] tasks = new int[totalTasks];
            int i = 0;

            while ((content = in.readLine()) != null) {
                String[] parts = content.split(" ");
                
                ids[i] = Integer.parseInt(parts[0]);
                tasks[i] = Integer.parseInt(parts[1]);
                i++;
            }

            fileContents.setFormat(i);
            fileContents.setIds(ids);
            fileContents.setTasks(tasks);

            in.close();

            return fileContents;
        } catch (IOException e) {
            FileContents fileContents = new FileContents(false);
            fileContents.setErrorMessage(e.getMessage());
            return fileContents;
        }
    }
}
