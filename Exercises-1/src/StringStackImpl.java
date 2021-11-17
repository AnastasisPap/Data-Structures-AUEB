import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T>{
    // Head = top of the stack and points to next newest node
    private Node<T> head = null;
    // the height of the stack
    private int length = 0;

    // returns true if the stack has no items otherwise false
    public boolean isEmpty() {
        return head == null;
    }

    // Pushes an item to the top of the stack
    public void push(T item) {
        // Create the new node
        Node<T> node = new Node<T>(item);

        // Check if it's an empty stack
        if (head == null) head = node;
        else {
            // head points to the newest node
            node.setNext(head);
            head = node;
        }

        // the height is increased by 1
        length++;
    }

    // Removes the top item and returns its value
    public T pop() throws NoSuchElementException {
        // If it's an empty stack throw exception
        if (isEmpty()) throw new NoSuchElementException();

        // get the value of the top item
        T data = head.getData();
        // point to the next newest item in the stack
        head = head.getNext();
        // decrease the height by 1
        length--;

        return data;
    }

    // Returns the value of the item on top and throws exception if it's an empty stack
    public T peek() throws NoSuchElementException{
        if (head == null) throw new NoSuchElementException();

        return head.getData();
    }

    // Print the stack starting from the newest item
    public void printStack(PrintStream stream) {
        // temporary node that points to the address of the head
        Node<T> currNode = head;

        // while currNode has a value
        while (currNode != null) {
            stream.print(currNode.getData() + " -> ");
            // point to the next newest item
            currNode = currNode.getNext();
        }
        stream.println("NULL");
    }

    // Return the height of the stack
    public int size() {
        return length;
    }
}