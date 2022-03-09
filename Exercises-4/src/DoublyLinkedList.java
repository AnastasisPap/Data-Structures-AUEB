public class DoublyLinkedList<K, V> implements DoublyLinkedListInterface<K, V>{


    private Node<K, V> head;
    private Node<K, V> tail;

    public DoublyLinkedList(){
        this.head = new Node<K,V>(null,null);
        this.tail = new Node<K,V>(null, null);
        this.head.next = tail; this.head.prev = head;
        this.tail.prev = head; this.tail.next = tail;

    }

    @Override
    public void setHead(Node<K, V> newNode, int inc){
        newNode.next = head.next;
        head.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;
    }

    @Override
    public void setHead(Node<K, V> newNode){
        setHead(newNode, 1);
    }

    @Override
    public K deleteTail(){
        
        K currTailKey = tail.prev.key;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;

        return currTailKey;
    }

    @Override
    public void makeMostRecent(Node<K, V> recent){
        recent.prev.next = recent.next;
        recent.next.prev = recent.prev;
        setHead(recent, 0);
    }

    public Node<K, V> getHead() {
        return head.next;
    }

    public Node<K, V> getTail() {
        return tail.prev;
    }
}
