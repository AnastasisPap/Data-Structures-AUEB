public class Processor implements ProcessorInterface{
    private int id;
    private Queue processedTasks;

    public Processor(int id){
        processedTasks = new Queue();
        this.id = id;
    }
    
    @Override
    public int getActiveTime() {
        return processedTasks.totalWork();
    }

    public boolean compareTo(Processor B) {
        if (this.getActiveTime() > B.getActiveTime()) return true;
        else if (this.getActiveTime() < B.getActiveTime()) return false;
        else return this.id > B.id;
    }

    public void addTask(Task task) {
        processedTasks.insert(task);
    }

    public Queue getProcessedTasks() {
        return this.processedTasks;
    }

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
}