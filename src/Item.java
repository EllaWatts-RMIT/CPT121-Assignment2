// abstract class to hold all Items 

public abstract class Item {
	
	protected String id;
	protected String title;
	protected boolean loaned;
	
	public Item(String id, String title) {
		this.id = id;
		this.title = title;
		loaned = false;
	}
	
	//used when user wants to see all Items in a list
	public void displayList() {
		String available = "No";
		if (this.loaned == false) {
			available = "Yes";
		}
		System.out.printf("%-10s %-20s %-12s\n", id, title, 
				available);
	}
	
	public abstract void displayItem();
	
	public void borrowItem() {
		loaned = true;
	}
	
	public void returnItem() {
		loaned = false;
	}
	
	public String getID() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean isLoaned() {
		return loaned;
	}
}
