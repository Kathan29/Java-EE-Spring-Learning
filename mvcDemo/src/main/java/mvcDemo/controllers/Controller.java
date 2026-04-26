package mvcDemo.controllers;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvcDemo.entities.Book;
import mvcDemo.repository.MyRepo;

@org.springframework.stereotype.Controller
public class Controller {

	private MyRepo repo;
	public Controller(MyRepo repo) {
		this.repo = repo;
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<Book> books = repo.fetchBestSellers();
		
		model.addAttribute("books", books);
		
		return "Home";
	}
	
	@GetMapping("/book")
	public String book(@RequestParam("id") long id,Model model) {
		List<Book> books = repo.fetchBestSellers();
		
		Book book = books.stream()
		.filter(b -> b.getId()==id)
		.findAny().get();
		
		model.addAttribute("book", book);
		//books.add(book);
		return "Book";
	}
	
	@RequestMapping("/create")
	public String create() {
		return "Create";
	}
	
	@PostMapping("/book")
	public String addBook(@RequestParam("title") String title,
			@RequestParam(value = "rating", defaultValue = "0.0") double amazonRating,Model model) {
		List<Book> books = repo.fetchBestSellers();
		Book book = new Book(books.size()+1,title,amazonRating);
		books.add(book);
		
		model.addAttribute("books",books);
		return "Home";
	}
}
