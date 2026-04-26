package nestedClass;

import java.util.Arrays;
import java.util.Comparator;

interface CacheIterator{
	boolean hasNext();
	Bookmark next();
}

public class Cache_DemoAnonymousClass {
	
	public Bookmark[] items;
	private int next = 0;
	
	public Cache_DemoAnonymousClass(int size) {
		items = new Bookmark[size];
	}
	
	public void add(Bookmark item) {
		if(next < items.length) {
			items[next++] = item;
		}
	}

	/*
	public CacheIterator iterator() {
		return new MyCacheIterator();
	}
	
	private class MyCacheIterator implements CacheIterator{
		int i;
		@Override
		public boolean hasNext() {
			
			return i<items.length;
		}

		@Override
		public Bookmark next() {
			// TODO Auto-generated method stub
			return items[i++];
		}
		
	}
	*/
	
//Commenting the above part in which nested class is created, here creating anonymous class inside method
	public CacheIterator iterator() {
		CacheIterator iterator = new CacheIterator() {
			
			private int index;
			
			@Override
			public boolean hasNext() {
				
				return index<items.length;
			}

			@Override
			public Bookmark next() {
				// TODO Auto-generated method stub
				return items[index++];
			}
			
		};
		return iterator;
	}
	
	//One of way of writing anonymous class 
	public static final Comparator<Bookmark> RATING_COMPARATOR = new Comparator<Bookmark>() {

		@Override
		public int compare(Bookmark o1, Bookmark o2) {
			return (o1.getRating() < o2.getRating()) ? -1 : 1;
		}
		
	};
	
	public static void main(String[] args) {
		
		Cache_DemoAnonymousClass cache = new Cache_DemoAnonymousClass(3);
		
		Bookmark b1 = new Bookmark();
		b1.setId(1);
		b1.setTitle("Effective Java");
		b1.setRating(3.0);
		
		Bookmark b2 = new Bookmark();
		b2.setId(2);
		b2.setTitle("C++ for begineers");
		b2.setRating(5.0);
		
		Bookmark b3 = new Bookmark();
		b3.setId(1);
		b3.setTitle("System design book");
		b3.setRating(2.0);
		
		cache.add(b1);
		cache.add(b2);
		cache.add(b3);
		
		//First way to create object of nested class
		//CacheIterator iterator = cache.new MyCacheIterator();
		
		//Second way to create object of nested class
		CacheIterator iterator = cache.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
		
//Using Anonymous class for sorting based on rating : 1st way
		Arrays.sort(cache.items, new Comparator<Bookmark>(){

			@Override
			//sorting in descending order
			public int compare(Bookmark o1, Bookmark o2) {
				
				return (o1.getRating() < o2.getRating()) ? 1 : -1;
			}
			
		});
		
		System.out.println("\nAfter Sorting based on title(Descending order): ");
		iterator = cache.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
		
//Using Anonymous Class for sorting based on rating : 2nd way 	
		Arrays.sort(cache.items,RATING_COMPARATOR); //Sorting in ascending order
		
		System.out.println("\nAfter Sorting based on title(Ascending order): ");
		iterator = cache.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());	
		}
		
//Creating anonymous class , so commenting class MyCacheIterator and creating anonymous class directly in iterator method	

	}

}
