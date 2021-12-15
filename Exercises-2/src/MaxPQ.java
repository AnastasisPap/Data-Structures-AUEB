public class MaxPQ implements PQInterface {
    private Processor[] heap;
    private int size;
    private int makespan;
    
    public MaxPQ(int capacity) {
        this.heap = new Processor[capacity + 1];
        this.size = 0;
        this.makespan = -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public int size() {
        return this.size;
    }
        
    @Override
    public Processor max() {
        if (size == 0) return null;

        return heap[1];
    }

    @Override
    public void insert(Processor x) {
        int max_capacity = (int) Math.round(heap.length * 0.75);
        if (size == max_capacity-1) resize();

        size++;

        makespan = Math.max(makespan, x.getActiveTime());

        heap[size] = x;
        swim(size);
    }

    @Override
    public Processor getmax() {
        if (size == 0) return null;

        Processor root = heap[1];
        heap[1] = heap[size];
        size--;
        sink(1);

        return root;
    }

    private void swim(int i) {
        if (i == 1) return;
        int parent_idx = i / 2;

        while (i > 1 && !heap[i].compareTo(heap[parent_idx])) {
            swap(i, parent_idx);
            i = parent_idx;
            parent_idx = i / 2;
        }
    }

    public void printProcessors(int totalTasks) {
        if (totalTasks <= 50) {
            while (!this.isEmpty()) {
                Processor processor = getmax();
                Queue items = processor.getProcessedTasks();
    
                System.out.print("id: " + processor.getId() + ", load=" + processor.getActiveTime() + ": ");
                
                while (!items.isEmpty()) {
                    System.out.print(items.get().getTime() + " ");
    
                }
                
                System.out.println("");
            }
        }

        System.out.println("Makespan = " + this.makespan);
    }

    public int getMakespan() {
        return this.makespan;
    }

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

    private void resize() {
        Processor[] newHeap = new Processor[2 * heap.length];

        for (int i = 0; i <= size; i++)
            newHeap[i] = heap[i];
        
        heap = newHeap;
    }
}
