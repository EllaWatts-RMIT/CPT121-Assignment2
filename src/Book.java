//class for item type Book. has inherited member variables from Item 
// (ID, title, loaned) as well as unique author and publisher. 

public class Book extends Item {
	
	private String author;
	private String publisher;
	
	public Book(String id, String title, String author, String publisher) {
		super(id, title);
		this.author = author;
		this.publisher = publisher;
	}
	
	// inherited from Item - for displaying a specific Item details
	public void displayItem() {
		System.out.printf("%-10s %-20s %-20s %-30s %-10s", "Item ID", 
				"Title", "Author", "Publisher", "Available");
		String available = "No";
		if (this.loaned == false) {
			available = "Yes";
		}
		System.out.printf("\n--------------------------------------"
				+ "------------------------\n%-10s %-20s %-20s %-30s %-10s", 
				id, title, author, publisher, available);
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getPublisher() {
		return publisher;
	}
}