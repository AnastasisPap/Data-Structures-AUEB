/*
    FOIVOS-NIKOLAOS PAPATHANASIOU, 3200138, p3200138@aueb.gr
    ANASTASIOS PAPAPANAGIOTOU, 3200143, p3200143@aueb.gr
*/

public class Cache<K, V> implements CacheInterface<K, V> {
    private HashMap<K, V> cache;
    private int maxSize;
    private int currentSize;
    public DoublyLinkedList<K, V> mostRecent;
	private long hits;
	private long numberOfLookUps;
    
    public Cache(int size) { 
        this.maxSize = size;
        cache = new HashMap<>(size);
        currentSize = 0;
        mostRecent = new DoublyLinkedList<>();
    }
    
    @Override
    public V lookUp(K key) {
        ++numberOfLookUps;

        Node<K, V> node = cache.get(key);
        
        if (node == null) return null;
        ++hits; // if we reach here, item with key was found.
        updateCache(node);
		
        return node.value;
    } 

	@Override
	public void store(K key, V value){
        
        Node<K, V> node = cache.get(key);
		if (node == null) {
            if (currentSize == maxSize) removeOldest();
            else ++currentSize;

            node = new Node<K, V>(key, value);
            mostRecent.setHead(node);
            cache.put(key, node);
        } else replaceKey(node, value);

        updateCache(node);
	}
    
    private void replaceKey(Node<K, V> node, V value) {
        node.value = value;
    }

	private void removeOldest(){
		K key = mostRecent.deleteTail();
        cache.remove(key);
	}
	
	private void updateCache(Node<K,V> node){
		mostRecent.makeMostRecent(node);
	}

	@Override
	public long getHits(){
		return hits;
	}

	@Override 
	public long getMisses(){
		return numberOfLookUps - hits;
	}

	@Override 
	public double getHitRatio() {
		return ((double) hits)/numberOfLookUps;
	}

	@Override 
	public long getNumberOfLookUps(){
		return numberOfLookUps;
	}
	
    public Node<K, V> getMostRecent() {
        if (currentSize == 0) return null;
        return mostRecent.getHead();
    }

    public int getSize() { return currentSize; }
}
