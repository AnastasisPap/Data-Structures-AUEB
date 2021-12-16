public class Greedy {

    // Creates the Priority Queue
    public static MaxPQ makePriorityQueue(FileContents contents, Task[] tasks) {
        // Creates a new object with parameter the number of processors
        MaxPQ maxPQ = new MaxPQ(contents.getProcessorCount());

        // loop all the tasks
        for (int i = 1; i < contents.getTotalTasks() + 1; i++) {
            // if there are processors with no tasks, add this task to that processor
            if (maxPQ.size() < contents.getProcessorCount()) {
                Processor temp = new Processor(i);
                temp.addTask(tasks[i-1]);
                maxPQ.insert(temp);
            // if all processors have at least one task get the processor with the highest priority, add the task and insert it again to the PQ
            } else {
                // get processor with highest priority
                Processor currProcessor = maxPQ.getmax();
                // add the current task
                currProcessor.addTask(tasks[i-1]);
                // insert it back to the PQ
                maxPQ.insert(currProcessor);;
            }
        }
        
        return maxPQ;
    }

    // Create the tasks from the file contents
    public static Task[] makeTasks(FileContents contents) {

        // Stores the times that are read from the file
        int[] taskTimes = contents.getTasks();
        // Stores the ids of those tasks
        int[] ids = contents.getIds();

        // Create new object
        Task[] tasks = new Task[contents.getTotalTasks()];
        // Add task to array
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(ids[i], taskTimes[i]);
        }

        return tasks;
    }
    public static void main(String[] args) {
        // Get the contents of the file
        FileContents contents = ReadFile.readFile(args[0]);
        
        // If read correctly (correct filepath, correct number of tasks) otherwise print error message
        if (!contents.isCorrect()) System.out.println(contents.getErrorMessage());
        else {
            // Create PQ for the first algorithm (unsorted tasks)
            System.out.println("Unsorted tasks:");
            Task[] tasks = makeTasks(contents);
            MaxPQ maxPQ = makePriorityQueue(contents, tasks);
            // Print the results
            maxPQ.printProcessors(contents.getTotalTasks());
            System.out.println();
            
            // Create PQ for the second algorithm (sorted tasks)
            System.out.println("Sorted tasks:");
            int n = contents.getTotalTasks() - 1;
            // Sort the tasks using quicksort
            Sort.quickSort(tasks, 0, n);
            MaxPQ sortedPQ = makePriorityQueue(contents, tasks);
            // Print the results
            sortedPQ.printProcessors(contents.getTotalTasks());
        }
    }
}
