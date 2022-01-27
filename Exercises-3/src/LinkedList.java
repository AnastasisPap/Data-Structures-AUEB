import java.util.NoSuchElementException;

public class LinkedList implements LinkedListInterface {
    public Node head = null;

    private int length = 0;

    @Override
    public boolean isEmpty() { return head == null; }

    @Override
    public void put(String item) {
        Node node = new Node(item);
        length++;

        if (head != null)
            node.setNext(head);
        
        head = node;
    }

    @Override
    public String remove(String word) {
        if (head == null) return null;
        length--;

        Node currNode = head, prev = null;
        String wordToRemove = currNode.getData();

        if (currNode.getData().equals(word)) {
            head = currNode.getNext();
            return wordToRemove;
        }

        while (currNode != null && !currNode.getData().equals(word)) {
            prev = currNode;
            currNode = currNode.getNext();
        }

        if (currNode == null) return null;
        prev.setNext(currNode.getNext());

        return wordToRemove;
    }

    @Override
    public String peek() throws NoSuchElementException {
        if (head == null) throw new NoSuchElementException();

        return head.getData();
    }

    @Override
    public int size() { return length; }

    @Override
    public void printLinkedList() {
        Node currNode = head;

        while (currNode != null) {
            System.out.print(currNode.getData() + " -> ");
            currNode = currNode.getNext();
        }

        System.out.println("NULL");
    }

    @Override
    public boolean hasItem(String item) {
        Node currNode = head;

        while (currNode != null && !currNode.getData().equals(item))
            currNode = currNode.getNext();

        return (currNode != null);
    }
}
