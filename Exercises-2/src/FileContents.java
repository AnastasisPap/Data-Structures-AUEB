import java.io.File;

public class FileContents {
    // Stores ids of the tasks
    private int[] ids;
    // Stores the times of the tasks
    private int[] tasks;
    // Number of total tasks
    private int totalTasks;
    // Number of total processors
    private int totalProcessors;
    // False if there are errors with the file (wrong path, or more/less tasks than should be)
    private boolean correctFormat;
    // stores the error message
    private String errorMessage;

    public FileContents(int totalTasks, int totalProcessors) {
        this.totalTasks = totalTasks;
        this.totalProcessors = totalProcessors;
        this.ids = new int[totalTasks];
        this.tasks = new int[totalTasks];
        this.correctFormat = true;
    }

    public FileContents(boolean format) {
        this.correctFormat = format;
    }

    // Getters and setters
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int[] getIds() {
        return this.ids;
    }

    public void setTasks(int[] tasks) {
        this.tasks = tasks;
    }

    public int[] getTasks() {
        return this.tasks;
    }

    public int getProcessorCount() {
        return this.totalProcessors;
    }

    public void setFormat(int cnt) {
        if (cnt != totalTasks) {
            this.correctFormat = false;
            setErrorMessage("The amount of tasks given is not corrent.");
        }
    }

    public int getTotalTasks() {
        return this.totalTasks;
    }

    public boolean isCorrect() {
        return this.correctFormat;
    }
}
