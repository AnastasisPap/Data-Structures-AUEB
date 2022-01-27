import java.util.NoSuchElementException;

public interface LinkedListInterface {
    boolean isEmpty();
    void put(String item);
    String remove(String word) throws NoSuchElementException;
    String peek() throws NoSuchElementException;
    int size();
    void printLinkedList();
    boolean hasItem(String item);
}
