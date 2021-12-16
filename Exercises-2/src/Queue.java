import java.util.NoSuchElementException;

public class Queue {
    private Node<Task> head = null;
    private Node<Task> tail = null;
    private int length = 0;
    private int totalWork = 0;

    public boolean isEmpty() { return head == null; }

    // insert task to the queue and add its time to the total time
    public void insert(Task item) {
        Node<Task> node = new Node<>(item);
        length++;

        if (tail == null) head = tail = node;
        else {
            tail.setNext(node);
            tail = node;
        }
        totalWork += item.getTime();
    }

    // Dequeue and subtract the time of the current task from the total
    public Task get() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        Task task = head.getData();
        length--;
        head = head.getNext();
        if (head == null) tail = null;

        totalWork -= task.getTime();
        return task;
    }

    // returns the oldest item
    public Task peak() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        return head.getData();
    }

    // return the size of the queue
    public int size() {
        return length;
    }

    // return the sum of times of all tasks in the queue
    public int totalWork() {
        return totalWork;
    }

    // compares if this queue is equal to another queue in terms of time and id
    public boolean isEqual(Queue B) {
        Task curr = get();
        while (!isEmpty()) {
            Task taskB = B.get();
            if (!curr.isEqual(taskB) || B.isEmpty()) return false;
            curr = get();
        }

        return true;
    }

    public String toString() {
        String output = "";
        Node<Task> curr = head;

        while (curr != null){
            output += curr.getData().getTime() + " ";
            curr = curr.getNext();
        }

        return output ;
    }
}
