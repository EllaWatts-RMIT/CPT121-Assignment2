// class for objects of Workwear in the catalogue. 
// has member variables from Item (ID, title, loaned)
// and one unique member variable - size.

public class Workwear extends Item {

	private int size;
	
	public Workwear(String id, String title, int size) {
		super(id, title);
		this.size = size;
	}
	
	// inherited from Item - for displaying a specific Item details
	public void displayItem() {
		System.out.printf("%-10s %-20s %-6s %-10s", "Item ID", 
				"Title", "Size", "Available");
		String available = "No";
		if (this.loaned == false) {
			available = "Yes";
		}
		System.out.printf("\n------------------------------------\n"
				+ "%-10s %-20s %-6s %-10s", id, title, 
				this.size, available);
	}
	
	public int getSize() {
		return size;
	}
}