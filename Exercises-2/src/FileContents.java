import java.io.File;

public class FileContents {
    private int[] ids;
    private int[] tasks;
    private int totalTasks;
    private int totalProcessors;
    private boolean correctFormat;
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
