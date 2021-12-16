public interface ProcessorInterface {
    int getActiveTime();
    boolean compareTo(Processor B);
    Queue getProcessedTasks();
    String getTasks();
    int getId();
    boolean haveSameTasks(Processor B);
}
