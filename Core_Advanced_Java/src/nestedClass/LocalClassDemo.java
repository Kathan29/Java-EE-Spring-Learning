package nestedClass;

public class LocalClassDemo {
	
	public static String giveBookAsXML(String title,String author,int copies) {
		//Local Class : Class inside method
		class Book{
			private String title;
			private String author;
			private int copies;
			
			//One thing to note is from this local class , as parameter names are same, we cannot access outer parameters.
			Book(String title,String author,int copies){
				this.title = title;
				this.author = author;
				this.copies = copies;
			}
		}
		
		Book book = new Book(title,author,copies);
		
		StringBuilder xml = new StringBuilder();
		xml.append("<Title>").append(book.title).append("</Title>\n");
		xml.append("<Author>").append(book.author).append("</Author>\n");
		xml.append("<Copies>").append(book.copies).append("</Copies>\n");
		
		return xml.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(giveBookAsXML("Effective Java","Joshua",100));
	}
}
