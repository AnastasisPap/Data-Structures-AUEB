public class Node {
    public String data;
    private Node next;

    Node(String data) { this.data = data; }

    public String getData() { return this.data; }

    public void setNext(Node next) { this.next = next; }

    public Node getNext() { return this.next; }
}
