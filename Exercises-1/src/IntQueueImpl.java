import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl<T> implements IntQueue<T>{
    // start of the queue
    private Node<T> head = null;
    // end of queue
    private Node<T> tail = null;
    // size of queue
    private int length = 0;

    // Returns true if the queue is empty otherwise false
    public boolean isEmpty() { return head == null; }

    // Puts an item in the end of the queue, which means that it gets appended to the tail
    public void put(T item) {
        // Create the new node with the given value
        Node<T> node = new Node<>(item);
        // increase the size of the queue
        length++;

        // If queue is empty at first then tail and head will be equal
        if (tail == null) head = tail = node;
        else {
            // previous newest elements points to current newest
            tail.setNext(node);
            // Tail is the newest node we added
            tail = node;
        }
    }

    // Gets returns the value of the oldest item and removes it from the queue
    public T get() throws NoSuchElementException {
        // If tries to remove from empty list throw exception
        if (head == null) throw new NoSuchElementException();
        // value of oldest node
        T data = head.getData();
        // length is less by 1
        length--;
        // head points to the next oldest item so the oldest one gets removed as nothing is pointing to it
        head = head.getNext();

        // if the queue's size was at first equal to 1 then we have to make both pointers null as they are not pointing to any nodes
        if (head == null) tail = null;

        // return the oldest value that got removed
        return data;
    }

    // Returns the oldest value
    public T peek() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        return head.getData();
    }

    // Prints the queue starting from the oldest to the newest
    public void printQueue(PrintStream stream) {
        Node<T> currNode = head;

        while (currNode != null) {
            stream.print(currNode.getData() + " -> ");
            currNode = currNode.getNext();
        }
        stream.println("NULL");
    }

    // Returns the size of the queue
    public int size() {
        return length;
    }
}
