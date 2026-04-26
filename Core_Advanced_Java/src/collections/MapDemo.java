package collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapDemo {

	private static void hashMapDemo() {
		
		Map<String,Integer> mp = new HashMap<>();
		mp.put("Rohit", 45);
		mp.put("Kohli", 18);
		mp.put("Dhoni", null);
		
		System.out.println(mp);
		
		mp.put("Dhoni", 7);
		System.out.println(mp);
		
		System.out.println("Iterating through keySet :");
		Set<String> names = mp.keySet();
		for (String key : names) {
			System.out.println("Key : "+key+" , Value : "+mp.get(key));
		}
		
		System.out.println("Iterating through entry Set :");
		Set<Entry<String, Integer>> mappings = mp.entrySet();
		for (Entry<String, Integer> mapping : mappings) {
			System.out.println("Key : "+mapping.getKey()+" , Value : "+mapping.getValue());
		}
		
		//Also this names set is backed by original set , hence change in this will reflect in original
		names.remove("Rohit");
		System.out.println("Original Map after removing Rohit from set returned by keySet: "+mp);
		
		//Trying complex map
		Map<String,Map<String,Object>> userProfile = new HashMap<>();
		
		Map<String,Object> profile = new HashMap<>();
		profile.put("age", 24);
		profile.put("state", "gujarat");
		profile.put("hasPassport", false);
		userProfile.put("Ram", profile);
		
		profile = new HashMap<String, Object>();
		profile.put("age", 27);
		profile.put("state", "UP");
		profile.put("hasPassport", true);
		userProfile.put("Shyam", profile);
		
		System.out.println(userProfile);
		
		//Lets try to get age 
		Map<String,Object> profile1 = userProfile.get("Shyam");
		int age = (Integer)profile1.get("age");
		System.out.println("Age of Shyam : "+age);
		
		//Lets try put All method 
		Map<String,Integer> mp2 = new HashMap<String, Integer>();
		mp2.putAll(mp);
		mp2.put("ABD", 17);
		System.out.println(mp2);
		mp2.clear();
		System.out.println("After using clear() : "+mp2);
		
	}
	
	private static void linkedHashmapDemo() {
		
		//Insertion order is maintained
		Map<String,Integer> mp = new LinkedHashMap<String, Integer>(); 
		Map<String,Integer> mp2 = new LinkedHashMap<>(10,0.75f,true); //if we want to run it as lru cache then in constructor pass true
		//mp 2 will work as lru cache but it will have infinite size, so if we want with maximum entries, then check lruTest.
		
		mp.put("a", 1);
		mp.put("b", 2);
		mp.put("c", 3);
		System.out.println(mp);
		
		mp2.putAll(mp);
		
		mp.get("a");
		mp.get("a");
		mp.get("a");
		System.out.println(mp);
		mp.get("b");
		System.out.println(mp);
		mp.put("d", 4);
		System.out.println(mp);
		mp.put("e", 5);
		System.out.println(mp);
		
		//Now doing same with map 2 as LRU Cache
		System.out.println("Trying map as LRU Cache");
		System.out.println("Current state : "+mp2);
		
		mp2.get("a");
		mp2.get("a");
		mp2.get("a");
		mp2.get("a");
		System.out.println("Accessed a 3 times : "+mp2); //So whenever inserted/accessed then it will be shown on rightmost side, and least recently used on left side
		mp2.get("b");
		System.out.println("Accessed b : "+mp2);
		mp2.put("d", 4);
		System.out.println("Inserted d :  "+mp2);
		mp2.put("e", 5);
		System.out.println("Inserted e : "+mp2);
	}
	
	static class LRUCache<K,V> extends LinkedHashMap<K,V>{
		private static final int MAX_ENTRIES = 3;
		
		public LRUCache(int capacity,float loadFactor,boolean accessOrder) {
			super(capacity, loadFactor, accessOrder);
		}
		
		public boolean removeEldestEntry(Map.Entry eldest) {
			return size() > MAX_ENTRIES;
		}
	}

	
	private static void lruTest() {
		
		System.out.println("Inside LRU Cache Demo with size 3");
		Map<String,Integer> lruCache = new LRUCache<>(10, 0.75f, true); 
		lruCache.put("a", 1);
		lruCache.put("b", 2);
		lruCache.put("c", 3);
		System.out.println(lruCache);
		
		lruCache.get("a");
		lruCache.get("a");
		lruCache.get("a");
		System.out.println(lruCache);
		lruCache.get("b");
		System.out.println(lruCache);
		lruCache.put("d", 4);
		System.out.println(lruCache);
		lruCache.put("e", 5);
		System.out.println(lruCache);
	}
	

	private static void treeMapDemo() {
		
		SortedMap<String, Integer> sm = new TreeMap<String, Integer>();
		sm.put("Rohit", 45);
		sm.put("Kohli", 18);
		sm.put("Dhoni", 7);
		
		System.out.println(sm); //Give in sorted order based on keys
		
		Set<Entry<String, Integer>> names = sm.entrySet();
		for (Entry<String, Integer> mappings : names) {
			System.out.println("Key : "+mappings.getKey()+" ,values: "+mappings.getValue());
			
			if(mappings.getKey()=="Kohli") {
				mappings.setValue(19);
			}
		}
		
		System.out.println(sm);
		
	}
	
	public static void main(String[] args) {
		//hashMapDemo();
		//linkedHashmapDemo();
		//lruTest();
		treeMapDemo();
	}


}
