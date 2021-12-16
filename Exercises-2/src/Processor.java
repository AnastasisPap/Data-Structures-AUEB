public class Processor implements ProcessorInterface{
    // id of the processor
    private int id;
    // Queue that stores the tasks of the processor
    private Queue processedTasks;

    public Processor(int id){
        processedTasks = new Queue();
        this.id = id;
    }

    // returns the sum of times of each task
    @Override
    public int getActiveTime() {
        return processedTasks.totalWork();
    }

    // returns true if the current processor does less work that B or if they have equal work time returns true if this processor has a smaller id
    public boolean compareTo(Processor B) {
        if (this.getActiveTime() > B.getActiveTime()) return true;
        else if (this.getActiveTime() < B.getActiveTime()) return false;
        else return this.id > B.id;
    }

    // add task to the queue
    public void addTask(Task task) {
        processedTasks.insert(task);
    }

    // return the queue of all processed tasks
    public Queue getProcessedTasks() {
        return this.processedTasks;
    }

    // converts tasks inside of the queue to a string
    public String getTasks() {
        String result = "";

        while (!processedTasks.isEmpty()) {
            result += processedTasks.get().getTime() + ", ";
        }

        return result + "END";
    }

    public int getId() {
        return this.id;
    }

    // return if two processors have the same tasks
    public boolean haveSameTasks(Processor B) {
        return getProcessedTasks().isEqual(B.getProcessedTasks());
    }
}