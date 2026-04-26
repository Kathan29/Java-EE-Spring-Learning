package lambda_streams.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamOperations {

	 static class Book implements Comparable{
		private long isbn;
		private String title;
		private double rating;
		private double price;
		private String source;
	
		
		public long getIsbn() {
			return isbn;
		}
		
		public String getTitle() {
			return title;
		}
		
		public double getRating() {
			return rating;
		}
		
		public double getPrice() {
			return price;
		}
		
		public String getSource() {
			return source;
		}
		
		public Book(long isbn, String title, double rating, double price, String source) {
			super();
			this.isbn = isbn;
			this.title = title;
			this.rating = rating;
			this.price = price;
			this.source = source;
		}
		@Override
		public String toString() {
			return "Book [isbn=" + isbn + ", title=" + title + ", rating=" + rating + ", price=" + price + ", source="
					+ source + "]";
		}
		@Override
		public int hashCode() {
			return Objects.hash(isbn);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Book other = (Book) obj;
			return isbn == other.isbn;
		}
		@Override
		public int compareTo(Object o) {

			return Long.valueOf(isbn).compareTo(((Book)o).getIsbn());
		}
			
	}
	
	static List<Book> booklist;
	 static ArrayList<Book> getBooks(){
		Book b1 = new Book(9780596009201L, "Java 1", 4.0, 25.0, "Amazon");
		Book b2 = new Book(9780596009202L, "Java 2", 4.1, 25.0, "Amazon");
		Book b3 = new Book(9780596009203L, "Java 3", 4.5, 25.0, "Amazon");
		Book b4 = new Book(9780596009204L, "Java 4", 4.5, 25.0, "Amazon");
		Book b5 = new Book(9780596009205L, "Java 5", 3.9, 30.0, "Amazon");
		Book b6 = new Book(9780596009206L, "Java 6", 4.8, 40.0, "Amazon");
		Book b7 = new Book(9780596009207L, "Java 7", 4.9, 40.0, "Amazon");
		Book b8 = new Book(9780596009208L, "Java 8", 4.7, 50.0, "Amazon");
		Book b9 = new Book(9780596009209L, "Java 9", 4.3, 50.0, "Amazon");
		Book b10 = new Book(9780596009210L, "Java 10", 4.5, 60.0, "Amazon");
		Book b11 = new Book(9780596009201L, "Java 1", 3.9, 20.0, "B&N");
		Book b12 = new Book(9780596009202L, "Java 2", 4.1, 20.0, "B&N");
		Book b13 = new Book(9780596009203L, "Java 3", 4.6, 20.0, "B&N");
		Book b14 = new Book(9780596009204L, "Java 4", 4.5, 20.0, "B&N");
		Book b15 = new Book(9780596009211L, "Java 11", 4.9, 40.0, "B&N");
		Book b16 = new Book(9780596009212L, "Java 12", 4.9, 55.0, "B&N");
		Book b17 = new Book(9780596009213L, "Java 13", 4.9, 60.0, "B&N");
		return new ArrayList<>(List.of(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17));
	}
	
	public static void main(String[] args) {
		
		booklist = getBooks();
		
//Intermediate operation : returns stream object
		slice(booklist);
		
//Terminal Operation : matching : Returns non stream object
		match(booklist);
		
//Terminal Operation : finding
		find(booklist);
		

		
		
	}

	


	private static void find(List<Book> booklist) {
		
		System.out.println("\nIn find method : ");
		
		Optional<Book> result = booklist.stream()
		.filter(d -> d.getRating()>=4.5 && d.getPrice()<=50)
		.findAny(); // or .findFirst();
		
		if(result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("not found");
		}
		
		//One other way to work with find
		booklist.stream()
		.filter(d -> d.getRating()>=4.8 && d.getPrice()<=50)
		.findAny().ifPresentOrElse(d -> System.out.println(d),() -> System.out.println("not found"));
		
		//Some other ways to work around
		booklist.stream()
		.filter(d -> d.getRating()>=5.0 && d.getPrice()<=50)
		.findAny().orElseGet(() -> { System.out.println("not found, returning new empty book object");
			return new Book(0L, "", 0.0, 0.0, "");});
	}

	private static void match(List<Book> booklist) {
		//Query 1 : Any of book which is highly rated(>4.8) and inexpensive(<50)
		boolean anyMatch = booklist.stream()
		.anyMatch(d -> d.getRating()>=4.8 && d.getPrice()<=50);
		System.out.println("\nAny of book which is highly rated(>4.8) and inexpensive(<50) : "+anyMatch);
		
		//Query 2 : Are all books highly rated(>=4.8)
		boolean allMatch = booklist.stream()
				.allMatch(d -> d.getRating()>=4.8);
				System.out.println("Are all books highly rated(>=4.8) : "+allMatch);
			
		//Query 3 : None of books has bad rating(<=2.0)		
		boolean noneMatch = booklist.stream()
				.noneMatch(d -> d.getRating()<=2.0);
				System.out.println("None of books has bad rating(<=2.0) : "+noneMatch);		
		
	}

	//Suppose we want to get top 5 distinct book with rating >= 4.5
	private static void slice(List<Book> bookList) {
		
		System.out.println("get top 5 distinct book with rating >= 4.5 :");
		bookList.stream()
			.filter(d -> d.getRating() >= 4.5)
			.distinct()
			.limit(5)  //Instead of limit , if we use .skip(5) then it will skip top 5 elements and give
			//.skip(5)
			.map(d -> d.getTitle())
			.forEach(System.out :: println);
	}
}
