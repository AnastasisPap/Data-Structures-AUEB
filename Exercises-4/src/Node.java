public class Node<K, V>{

    public K key;
    public V value;
    public Node<K, V> next;
    public Node<K, V> prev;
    public int psl;

    public Node(K key, V value){
        this.key = key;
        this.value = value;
        this.next = this.prev = null;
        this.psl = 0;
    }
}