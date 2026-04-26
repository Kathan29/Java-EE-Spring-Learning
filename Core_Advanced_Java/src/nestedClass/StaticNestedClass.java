package nestedClass;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

class Demo {

//This is one of use cases of Static Nested Class : called as strategy
	public static final Comparator<Bookmark> TITLE_COMPARATOR =  new InsideClass();
	
	private static class InsideClass implements Comparator<Bookmark>,Serializable{

		@Override
		public int compare(Bookmark o1, Bookmark o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
		
	}

//This is second use case to create public static class : called as public helper class	
	public static class ComparatorList{
		
		public static class TitleComparator implements Comparator<Bookmark>,Serializable{

			@Override
			public int compare(Bookmark o1, Bookmark o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}	
		}
		
		public static class RatingComparator implements Comparator<Bookmark>,Serializable{
			@Override
			public int compare(Bookmark o1, Bookmark o2) {
				return Double.valueOf(o1.getRating()).compareTo(Double.valueOf(o2.getRating()));
			}
		}
		
	}
	
}

public class StaticNestedClass{
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
		
		System.out.println("Sorting through Title : (Static Nested Class ) Way 1 : ");
		Arrays.sort(cache.items,Demo.TITLE_COMPARATOR);
		
		CacheIterator iterator = cache.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
		
		System.out.println("\n\nSorting through Rating : (Static Nested Class ) Way 2 : ");
		Arrays.sort(cache.items,new Demo.ComparatorList.RatingComparator());
		iterator = cache.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
		
		System.out.println("\n\nSorting through Title : (Static Nested Class ) Way 2 : ");
		Arrays.sort(cache.items,new Demo.ComparatorList.TitleComparator());
		iterator = cache.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
	}
}
