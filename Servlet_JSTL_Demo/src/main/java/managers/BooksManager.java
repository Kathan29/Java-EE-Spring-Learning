package managers;

import java.util.ArrayList;
import java.util.List;

import entities.Book;

public class BooksManager {
	private BooksManager() {}
	
	private static BooksManager instance = new BooksManager();
	
	public static BooksManager getInstance() {
		return instance;
	}
	
	public List<Book> getBooks(){
		
		List<Book> myBooks = new ArrayList<Book>();
		// First book
		Book book = new Book();
		book.setTitle("Java book");
		book.setAuthor("Erich Segal");
		book.setRating(3.44);
		
		myBooks.add(book);
		
		// Second book
		book = new Book();
		book.setTitle("System design book");
		book.setAuthor("Lillian Eichler Watson");
		book.setRating(5.0);
		
		myBooks.add(book);

		return myBooks;
	}
}
