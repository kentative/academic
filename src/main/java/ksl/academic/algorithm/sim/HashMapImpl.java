package ksl.academic.algorithm.sim;

public class HashMapImpl<K, V> {

	public static void main(String[] args) {
		
		HashMapImpl<String, String> map = new HashMapImpl<>(4, 0.75);
		map.put("Name1", "Kent");
		map.put("Name2", "Nadia");
		map.put("Name3", "Kydan");
		map.put("Name4", "Kaelyn");
		map.put("Name5", "Bear");
		
		System.out.println(map.get("Name5"));
		System.out.println(map.size);
		
	}
	
	private double loadFactor;
	private int capacity; // if capacity is power of 2, faster modulos 
	private int size;
	
	private Node<K, V>[] table;
	
	@SuppressWarnings("unchecked")
	public HashMapImpl(int capacity, double loadFactor) {
		this.capacity = getPowerOf2(capacity);
		this.loadFactor = loadFactor;
		this.table = new Node[capacity];
		this.size = 0;
	}
	
	private int getPowerOf2(int cap) {
		int n = -1 >>> Integer.numberOfLeadingZeros(cap-1);
		return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
	}

	public V put(K key, V value) {

		int hash = key.hashCode();
		int keySize = resize();
		Node<K, V> item = table[hash%keySize];
	
		if (item == null) table[hash%keySize] = new Node<>(key, value, hash);
		else 		      item.next = new Node<>(key, value, hash);
		
		size++;
		return value;
	}
	
	public V get(K key) {
		int hash = key.hashCode();
		return table[hash%capacity].value;
	}	
	
	private int resize() {
		
		if (size >= (capacity * loadFactor)) {
			capacity *= 2;
			
			@SuppressWarnings("unchecked")
			Node<K,V>[] newTable = new Node[capacity];
			for (int i = 0; i < table.length; i++) {
				
				Node<K, V> node = table[i];
				if (node != null) {
					int hash = table[i].hash;
					newTable[hash%capacity] = table[i];
				}
			}
			table = newTable;
		}
		return table.length;
	}

	static private class Node<K, V> {
		
		final int hash;
		final K key;
		V value;
		Node<K,V> next;
		
		Node(K key, V value, int hash) {
			this.key = key;
			this.value = value;
			this.hash = hash;
		}
		
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

}
