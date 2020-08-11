//
// The Patron class of Assignment 2 - CPT121 is
// a data class that manages Patron data including 
// an array of objects of type Item, which are 
// essentially duplicates from the holdings array
// in Stage C - when a Patron borrows an item, it will
// remain flagged as 'loaned' in the holdings class, 
// and a new object will be created in the Patron class
// with identical field values. 
// 

public class Patron {
	
	private static int MAX_LOANS = 4;
	private Item loans[] = new Item[MAX_LOANS];
	private int numLoans;
	
	private String patronID;
	private String name;
	
	public Patron(int idNum, String name) {
		this.patronID = "P" + idNum;
		this.name = name;
		numLoans = 0;
	}
	
	public void displayPatron() {
		System.out.print("\n\n-----------Patron Profile-------------\n"
				+ "\nID:     " + patronID
				+ "\nName:   " + name
				+ "\nLoans:  ");
		if (numLoans > 0 ) {
			System.out.printf("\n%-10s %-20s"
					+ "\n-----------------------------\n", 
					"Item ID", "Title");
			for (int i = 0; i < numLoans; i++) {
				System.out.printf("%-8s %-20s\n", loans[i].getID(), 
						loans[i].getTitle());
			}
		} else {
			System.out.print("This Patron does not have any current "
					+ "loans.\n");
		}
		
	}
	
	//overloaded borrowItem method for all Item subclasses. 
	public void borrowItem(String id, String title, int size) {
		loans[numLoans++] = new Workwear(id, title, size);
	}
	
	public void borrowItem(String id, String title, String author, 
			String publisher) {
		loans[numLoans++] = new Book(id, title, author, publisher);
	}
	
	public void borrowItem(String id, String title, String manufacturer, 
			String model, boolean hasManual, String manualID) {
		loans[numLoans++] = new Tool(id, title, manufacturer, model, 
				hasManual, manualID);
	}
	
	//removes the Item object from the loans array
	public boolean returnItem(String itemID) {
		boolean success = false;
		int itemIndex = 0;
		for (int i = 0; i < numLoans; i++) {
			if (itemID.equalsIgnoreCase(loans[i].getID())) {
				itemIndex = i;
				i = numLoans;
				success = true;
			}
		}
		
		Item tempLoans[] = new Item[MAX_LOANS];
		for (int i = 0; i < numLoans; i++) {
			if (i < itemIndex) {
				tempLoans[i] = loans[i];
			} else if (i > itemIndex) {
				tempLoans[i-1] = loans[i];
			}
		}
		loans = tempLoans;
		numLoans--;
		return success;
	}
	
	public String getID() {
		return patronID;
	}
	
	public int getMaxLoans() {
		return MAX_LOANS;
	}
	
	public int getNumLoans() {
		return numLoans;
	}
	
	public String getName() {
		return name;
	}
}