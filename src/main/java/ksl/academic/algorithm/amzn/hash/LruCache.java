package ksl.academic.algorithm.amzn.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LruCache<K, V> {

	
	public static void main(String[] args) {
		LruCache<Integer, String> cache = new LruCache<>();
		
		cache.put(1, "Kent");
		cache.put(2, "Nadia");
		cache.put(3, "Kydan");
		cache.put(4, "Kaelyn");
		
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		System.out.println(cache.get(1));
		System.out.println(cache.getRecent());
		
	}
	
	
	private Map<K, V> map;
	private List<K> lruList;
	
	public LruCache() {
		
		map = new HashMap<>();
		lruList = new LinkedList<>();
	}
	
	public V get(K key) {
		
		if (map.containsKey(key)) {
			lruList.remove(key);
			lruList.add(0, key);
			return map.get(key);
		}
		
		return null;
	}
	
	public void put(K key, V value) {
		
		map.put(key, value);
		lruList.remove(key);
		lruList.add(0, key);
		
	}
	
	public V getRecent() {
		
		if (lruList.isEmpty()) return null;
		return map.get(lruList.get(0));
	}
}
