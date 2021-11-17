import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    // return the contents of the file
    public static String readFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        // if file exists
        try {
            // append each line to a string
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String content;
            while ((content = in.readLine()) != null) {
                contentBuilder.append(content);
                contentBuilder.append(" ");
            }
            // close the file
            in.close();
        // file doesn't exist
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }

        // return the contents of the file
        return contentBuilder.toString();
    }
}
