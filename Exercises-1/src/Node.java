class Node<T> {
    // value of the node
    private T data;
    // reference to the next node
    private Node<T> next = null;

    // constructor
    Node(T data) { this.data = data; }

    // get the value of the node
    public T getData() { return this.data; }

    // set reference to another node
    public void setNext(Node<T> next) { this.next = next; }

    // get next node
    public Node<T> getNext() { return this.next; }
}
