public class Greedy {

    public static MaxPQ makePriorityQueue(FileContents contents, Task[] tasks) {
        MaxPQ maxPQ = new MaxPQ(contents.getProcessorCount());

        for (int i = 1; i < contents.getTotalTasks() + 1; i++) {
            if (maxPQ.size() < contents.getProcessorCount()) {
                Processor temp = new Processor(i);
                temp.addTask(tasks[i-1]);
                maxPQ.insert(temp);
            } else {
                Processor currProcessor = maxPQ.getmax();
                currProcessor.addTask(tasks[i-1]);
                maxPQ.insert(currProcessor);;
            }
        }
        
        return maxPQ;
    }

    public static Task[] makeTasks(FileContents contents) {

        int[] taskTimes = contents.getTasks();
        int[] ids = contents.getIds();

        Task[] tasks = new Task[contents.getTotalTasks()];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(ids[i], taskTimes[i]);
        }

        return tasks;
    }
    public static void main(String[] args) {
        FileContents contents = ReadFile.readFile(args[0]);
        
        if (!contents.isCorrect()) System.out.println(contents.getErrorMessage());
        else {
            System.out.println("Unsorted tasks:");
            Task[] tasks = makeTasks(contents);
            MaxPQ maxPQ = makePriorityQueue(contents, tasks);
            maxPQ.printProcessors(contents.getTotalTasks());
            System.out.println();
            
            System.out.println("Sorted tasks:");
            int n = contents.getTotalTasks() - 1;
            Sort.quickSort(tasks, 0, n);
            MaxPQ sortedPQ = makePriorityQueue(contents, tasks);
            sortedPQ.printProcessors(contents.getTotalTasks());
        }
    }


}
