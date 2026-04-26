package collections;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetDemo {
	public static void main(String[] args) {
		// hashSetDemo();
		// linkedHashSetDemo();
		// treeSetDemo();
		   treeSetDemo2();
	}

	private static void treeSetDemo2() {
		NavigableSet<Integer> ts = new TreeSet<Integer>();
		ts.add(24);
		ts.add(74);
		ts.add(5);
		ts.add(50);
		System.out.println(ts); //It will be sorted
		
		System.out.println("Lower : "+ts.lower(24));
		System.out.println("Higher : "+ts.higher(24));
		System.out.println("Ceiling : "+ts.ceiling(24));
		System.out.println("Floor :"+ts.floor(24));
		
		NavigableSet<Integer> headSet = ts.headSet(50,false);
		System.out.println("HeadSet example (exclusive) : "+headSet);
		headSet = ts.headSet(50, true);
		System.out.println("HeadSet example (inclusive) : "+headSet);
		
		headSet.add(7);
		System.out.println("After adding element, original tree set is now : "+ts);
		System.out.println("After adding element, headset is : "+headSet);
		
		//Trying to add element out of 50
		// headSet.add(55); //Exception : key out of range
		ts.add(55); // No exception, just original tree set changes
		System.out.println("After adding element, original tree set is now : "+ts);
		System.out.println("After adding element, headset is : "+headSet);
		
		SortedSet<Integer> subSet = ts.subSet(7, 55);
		System.out.println("SubSet example : "+subSet);
		
		subSet.add(20);
		System.out.println("After adding element, original tree set is now : "+ts);
		System.out.println("After adding element, subset is : "+subSet);
				
		//Trying to add element out of range
		//subSet.add(3); //Exception : key out of range
		ts.add(3);
		System.out.println("After adding element, original tree set is now : "+ts);
		System.out.println("After adding element, subset is : "+subSet);
		
	}

	private static void treeSetDemo() {

		Set<String> ts = new TreeSet<String>();
		ts.add("Kohli");
		ts.add("Rohit");
		ts.add("Dhoni");
		System.out.println("Tree set example with strings : " + ts); //It will be sorted
		//O/P : [Dhoni, Kohli,Rohit]

		Set<Book> treeSet = new TreeSet<Book>();
		Book book1 = new Book("harry potter", "jk rowling", 1997);
		Book book2 = new Book("harry potter", "jk rowling", 1997);
		Book book3 = new Book("walden", "henry david", 1854);
		Book book4 = new Book("effective java", "joshua bloch", 2008);
		treeSet.addAll(List.of(book1, book2, book3, book4));

		System.out.println("Tree Set example with Book Class (comparable) : " + treeSet);

		Set<Book> treeSet2 = new TreeSet<Book>(new TitleComparator());
		treeSet2.addAll(List.of(book1, book2, book3, book4));

		System.out.println("Tree Set example with Book Class (comparator) : " + treeSet2);
	}

	private static void linkedHashSetDemo() {
		Set<String> hashSet = new HashSet<String>();
		hashSet.add("Kohli");
		hashSet.add("Rohit");
		hashSet.add("Dhoni");

		Set<String> linkHashSet = new LinkedHashSet<String>();
		linkHashSet.add("Kohli");
		linkHashSet.add("Rohit");
		linkHashSet.add("Dhoni");

		System.out.println("Hash set : " + hashSet);
		System.out.println("Linked Hash Set : " + linkHashSet);

	}

	private static void hashSetDemo() {

		// Understanding set, duplicates will not be stored
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("a");
		System.out.println(set);

		// Checking for duplicates in Book class
		Set<Book> set2 = new HashSet<Book>();
		Book book1 = new Book("harry potter", "jk", 2000);
		Book book2 = new Book("harry potter", "jk", 2000);
		set2.add(book1);
		set2.add(book2);
		System.out.println("Printing normally : " + set2);
		// Now here even though both book contain same content , it is not considered
		// duplicates

		// So to treat them as duplicates , override hashCode and equals method
		Set<Book2> set3 = new HashSet<Book2>();
		Book2 book3 = new Book2("harry potter", "jk", 2000);
		Book2 book4 = new Book2("harry potter", "jk", 2000);
		set3.add(book3);
		set3.add(book4);
		System.out.println("Printing in which we override hashcode and equals : " + set3);
	}
}

class Book implements Comparable {
	private String title;
	private String author;
	private int year;

	// Generate constructor
	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	// Generate getter and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// Also generate toString method for easy printing
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}

	@Override
	// So suppose here we want to sort according to title
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return getTitle().compareTo(((Book) o).getTitle()); // Here we are utilizing string's compareTo
	}

}

class Book2 {
	private String title;
	private String author;
	private int year;

	// Generate constructor
	public Book2(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}

	// Generate getter and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// Also generate toString method for easy printing
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}

	// Override hashcode and equals
	@Override
	public int hashCode() {
		return Objects.hash(author, title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book2 other = (Book2) obj;
		return Objects.equals(author, other.author) && Objects.equals(title, other.title) && year == other.year;
	}

}

class TitleComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		return ((Book) o1).getTitle().compareTo(((Book) o2).getTitle());
	}

}
