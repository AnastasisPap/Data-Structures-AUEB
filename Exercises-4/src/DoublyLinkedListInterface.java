public interface DoublyLinkedListInterface<K, V> {


    void setHead(Node<K, V> newNode, int inc);
    void setHead(Node<K, V> newNode);
    K deleteTail();
    void makeMostRecent(Node<K, V> recent);
    Node<K, V> getHead();
    Node<K, V> getTail();
}
