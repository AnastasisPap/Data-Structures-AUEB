import java.util.NoSuchElementException;

public class Queue {
    private Node<Task> head = null;
    private Node<Task> tail = null;
    private int length = 0;
    private int totalWork = 0;

    public boolean isEmpty() { return head == null; }

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

    public Task get() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        Task task = head.getData();
        length--;
        head = head.getNext();
        if (head == null) tail = null;

        totalWork -= task.getTime();
        return task;
    }

    public Task peak() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        return head.getData();
    }

    public int size() {
        return length;
    }

    public int totalWork() {
        return totalWork;
    }
}
