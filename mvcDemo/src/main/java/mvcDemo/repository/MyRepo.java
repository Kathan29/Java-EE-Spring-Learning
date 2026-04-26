package mvcDemo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mvcDemo.entities.Book;

@Component
public class MyRepo {
	
	List<Book> books = new ArrayList<Book>();
	{
		books.add(new Book(1,"Design First Pattern",4.8));
		books.add(new Book(2,"Harry potter",4.5));
		books.add(new Book(3,"Java essentials",4));
	}
	public List<Book> fetchBestSellers()
	{
		return books;
	}
}
