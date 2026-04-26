package springBootDemo.entities;



public class Book {
	private long id;
	private String title;
	private double amazonRating;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public double getAmazonRating() {
		return amazonRating;
	}
	public void setAmazonRating(double amazonRating) {
		this.amazonRating = amazonRating;
	}
	
	public Book(long id, String title, double amazonRating) {
		super();
		this.id = id;
		this.title = title;
		this.amazonRating = amazonRating;
		
	}
	
	
}
