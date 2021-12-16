public class MaxPQ implements PQInterface {
    // Stores the processors
    private Processor[] heap;
    // Size of the heap == number of processors + 1
    private int size;
    // stores the makespan
    private int makespan;
    
    public MaxPQ(int capacity) {
        this.heap = new Processor[capacity + 1];
        this.size = 0;
        this.makespan = -1;
    }

    // Returns true if there are no items in the heap
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Returns the number of items in the heap
    @Override
    public int size() {
        return this.size;
    }
       
    // Returns the item with the maximum priority (min active time) or null if no items in the PQ
    @Override
    public Processor max() {
        if (size == 0) return null;

        return heap[1];
    }

    // Insert a processor into the PQ
    @Override
    public void insert(Processor x) {
        // If the heap has reached 75% of capacity, double its size
        int max_capacity = (int) Math.round(heap.length * 0.75);
        if (size == max_capacity-1) resize();

        size++;

        // makespan = max(previous makespan or the active time of the current processor)
        makespan = Math.max(makespan, x.getActiveTime());

        // add the item to the heap
        heap[size] = x;
        swim(size);
    }

    // Get the item with the highest priority (min time) and remove it from the heap
    @Override
    public Processor getmax() {
        if (size == 0) return null;

        // Get the processor that has the minimum active time
        Processor root = heap[1];
        heap[1] = heap[size];

        // remove from the heap
        size--;
        sink(1);

        return root;
    }

    // update the heap based on total active time (used when inserting)
    private void swim(int i) {
        if (i == 1) return;
        int parent_idx = i / 2;

        while (i > 1 && !heap[i].compareTo(heap[parent_idx])) {
            swap(i, parent_idx);
            i = parent_idx;
            parent_idx = i / 2;
        }
    }

    private void printLine(Processor processor) {
        System.out.println("id: " + processor.getId() + ", load=" + processor.getActiveTime() + ": " + processor.getProcessedTasks().toString());
    }

    public Processor[] getProcessors() {
        return this.heap;
    }

    // Print the times of each processor and its tasks
    public void printProcessors(int totalTasks) {
        if (totalTasks <= 50) {
            for (int i = 1; i <= size; i++) {
                Processor processor = heap[i];
                printLine(processor);
            }
        }

        System.out.println("Makespan = " + this.makespan);
    }

    // get the makespan
    public int getMakespan() {
        return this.makespan;
    }

    // update the heap based on total active time (used when deleting)
    private void sink(int i) {
        while (2 * i <= this.size) {
            int j = 2 * i;
            if (j < size && heap[j].compareTo(heap[j+1])) j++;
            if (!heap[i].compareTo(heap[j])) break;
            swap(i, j);
            i = j;
        }
    }

    private void swap(int i, int j) {
        Processor temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // double the heap array length
    private void resize() {
        Processor[] newHeap = new Processor[2 * heap.length];

        for (int i = 0; i <= size; i++)
            newHeap[i] = heap[i];
        
        heap = newHeap;
    }
}
