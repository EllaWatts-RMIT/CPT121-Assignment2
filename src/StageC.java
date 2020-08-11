//
// This class Stage C and the accompanying classes (Patron, Item, 
// Workwear, Book, Tool) form the code for Assignment 2 of CPT121, 
// Programming 1. The program is designed to handle a library 
// catalogue, including managing catalogue items, library patrons
// and items on loan to patrons. 
// 

import java.util.*;

// Stage C is the main class and is responsible managing data classes
// and creating objects of these classes. 
public class StageC {
	
	public static int MAX_PATRONS = 3;
	public static int MAX_ITEMS = 5;
	public int numItems = 0;
	public int numPatrons = 0;
	
	static Scanner console = new Scanner(System.in);
	
	public Item holdings[] = new Item[MAX_ITEMS];
	public Patron patrons[] = new Patron[MAX_PATRONS];
	
	// The constructor displays the welcome and calls the menu method
	public StageC() {
		System.out.print("--------Bendigo Tool Library---------\n\n"
				+ "  Welcome to the Library Database\n\n");
		displayMenu();
	}
	
	// The menu method calls majority of the program functionality,
	// allowing users to select actions.
	// This method unfortunately contains a lot of arrow code, which 
	// I have since found out can be remedied using patterns such as 
	// 'guard clauses' and using more modular code with returns 
	// throughout. 
	//
	// J. Atwood, "Flattening Arrow Code", Coding Horror, 2006. 
	// [Online]. Available: 
	// https://blog.codinghorror.com/flattening-arrow-code/. 
	// [Accessed: 26- Jul- 2020].
	
	public void displayMenu() {
		System.out.print("\n\n-----------------Menu---------------"
				+ "--\n\n(1) New Patron\n"
				+ "(2) Display Patron\n"
				+ "(3) Add Item\n"
				+ "(4) Display Item\n"
				+ "(5) Display All Items\n"
				+ "(6) Borrow\n"
				+ "(7) Return\n"
				+ "(8) Exit\n\n"
				+ "Select a menu item (enter the corresponding "
				+ "number): ");
		String menuSelect = console.nextLine().trim();
		
		// The method hinges on the 'menuSelect' variable. 
		// When it is 1, this allows users to add a new Patron up to 
		// MAX_PATRONS.
		if (menuSelect.equals("1")) {
			if (numPatrons<MAX_PATRONS) {
				System.out.print("\n\n-------------New Patron------------"
						+ "---\n\nEnter Patron name: ");
				String name = console.nextLine();
				int idNum = numPatrons + 1;
				patrons[numPatrons++] = new Patron(idNum, name);
				System.out.print("\nPatron ID and name: P" + idNum + ", " 
						+ name);
			} else {
				System.out.print("\nYou have reached the maximum number of"
						+ " Patrons.");
			}
			returnToMenu();
			
		//The following block allows users to view Patron details, including
		// ID, name and loans. 
		} else if (menuSelect.equals("2")) {
			System.out.print("\nPlease enter the ID of the Patron you wish "
					+ "to view: ");
			String searchID = console.nextLine().trim();
			boolean idFound = false;
			for (int i = 0; i < numPatrons; i++) {
				if (searchID.equalsIgnoreCase(patrons[i].getID())) {
					patrons[i].displayPatron();
					idFound = true;
				}
			}
			if (!idFound) {
				System.out.print("Patron ID not found.");
			}
			returnToMenu();
			
		//The following code block allows users to add an Item of type 
		// Workwear, Book or Tool. A Tool may be linked to a Book, but 
		// only if the Book is already in the system. 
		} else if (menuSelect.equals("3")) {
			if (numItems<MAX_ITEMS) {
				System.out.print("\n\n--------------New Item---------------"
						+ "-\n\nWhat type of Item would you like to add? "
						+ "Workwear (W), Book (B) or Tool (T): ");
				String itemType = console.nextLine().trim();
				if (!itemType.equalsIgnoreCase("W") 
						&& !itemType.equalsIgnoreCase("B") 
						&& !itemType.equalsIgnoreCase("T")) {
					System.out.print("\nThat's not a valid choice. Next time,"
							+ " please enter \"W\", \"B\" or \"T\".");
					returnToMenu();
					
				} else {
					int idNum = 100+numItems;
					String itemID = itemType.toUpperCase() + idNum;
					System.out.print("\n\nPlease enter the title of the item: ");
					String title = console.nextLine();
					if (itemType.equalsIgnoreCase("W")) {
						System.out.print("\n\nPlease enter the size of the "
								+ "item: ");
						int size = console.nextInt();
						console.nextLine();
						holdings[numItems] = new Workwear(itemID, title, size);
						System.out.print("\n");
						holdings[numItems++].displayItem();
						
					} else if (itemType.equalsIgnoreCase("B")) {
						System.out.print("\n\nPlease enter the author: ");
						String author = console.nextLine();
						System.out.print("\n\nPlease enter the publisher: ");
						String publisher = console.nextLine();
						holdings[numItems] = new Book(itemID, title, author, 
								publisher);
						System.out.print("\n");
						holdings[numItems++].displayItem();
						
					} else if (itemType.equalsIgnoreCase("T")) {
						System.out.print("\n\nPlease enter the manufacturer: ");
						String manufacturer = console.nextLine();
						System.out.print("\n\nPlease enter the model number: ");
						String model = console.nextLine();
						System.out.print("\n\nDoes the Library have the manual "
								+ "for this tool? (Y/N) ");
						String hasManual = console.nextLine().trim();
						if (hasManual.equalsIgnoreCase("Y")) {
							System.out.print("\n\nPlease enter the Item ID for "
									+ "the manual: ");
							String manualID = console.nextLine().trim();
							if (numItems > 0) {
								for (int i = 0; i < numItems; i++) {
									if (manualID.equalsIgnoreCase(holdings[i].getID()) 
											&& holdings[i].getID().charAt(0) == 'B') {
										holdings[numItems] = new Tool(itemID, 
												title, manufacturer, model, true, 
												manualID);
										System.out.print("\n");
										holdings[numItems++].displayItem();
										i = numItems + 1;
										
									} else {
										System.out.print("Item ID not found. Please "
												+ "enter the manual (book) first before "
												+ "adding the related tool.");
									}
								}
							} else {
								System.out.print("Item ID not found. Please enter "
										+ "the manual (book) first before adding the "
										+ "related tool.");
							}
							
						} else if (hasManual.equalsIgnoreCase("N")) {
							holdings[numItems] = new Tool(itemID, title, manufacturer, 
									model, false, "none");
							System.out.print("\n");
							holdings[numItems++].displayItem();
							
						} else {
							System.out.print("\n\nThat's not a valid input.");
						}
					}
				}	
			} else {
				System.out.print("\nYou have reached the maximum number of items in "
						+ "the catalogue.");
			}
			returnToMenu();
		
		// Block menuSelect = 4 allows users to view details of a specific Item.
		} else if (menuSelect.equals("4")) {
			System.out.print("\nPlease enter the ID of the Item you wish "
					+ "to view: ");
			String searchID = console.nextLine().trim();
			boolean idFound = false;
			for (int i = 0; i < numItems; i++) {
				if (searchID.equalsIgnoreCase(holdings[i].getID())) {
					System.out.print("\n\n---------------Item Catalogue"
							+ "----------------\n\n");
					holdings[i].displayItem();
					idFound = true;
				}
			}
			if (!idFound) {
				System.out.print("Item ID not found.");
			}
			returnToMenu();
		
		// menuSelect = 5 allows users to view the entire catalogue of items.
		} else if (menuSelect.equals("5")) {
			System.out.println("\n----------Library Catalogue-----------");
			if (numItems > 0) {
				System.out.printf("\n%-10s %-20s %-12s\n--------------------"
						+ "-----------------------\n", "Item ID", "Title", 
						"Availabile");
				for (int i = 0; i < numItems; i++) {
					holdings[i].displayList();
				}
			} else {
				System.out.print("No Catalogue Items are available.\n");
			}
			returnToMenu();
			
		//menuSelect = 6 allows users to loan an Item to a Patron. 
		} else if (menuSelect.equals("6")) {
			System.out.print("\n----------------Borrowing---------------"
					+ "----\n\nEnter the ID of the Patron who wishes to "
					+ "borrow an item: ");
			String borrowersID = console.nextLine().trim();
			int patronIndex = 0;
			boolean idFound = false;
			for (int i = 0; i < numPatrons; i++) {
				if (borrowersID.equalsIgnoreCase(patrons[i].getID())) {
					patronIndex = i;
					i = numPatrons;
					idFound = true;
				}
			}
			if (!idFound) {
				System.out.print("\nPatron ID not found.");
				
			} else if (patrons[patronIndex].getNumLoans() >= 
					patrons[patronIndex].getMaxLoans()) {
				System.out.print("This Patron has reached the maximum "
						+ "number of loans");
				
			} else {
				System.out.print("\nEnter the ID code of the Item to "
						+ "be borrowed: ");
				String borrowedItem = console.nextLine();
				int itemIndex = 0;
				boolean itemFound = false;
				for (int i = 0; i < numItems; i++) {
					if (borrowedItem.equalsIgnoreCase(holdings[i].getID())) {
						itemIndex = i;
						i = numItems;
						itemFound = true;
					}
				}
				if(!itemFound) {
					System.out.print("\nItem not found.");
				} else if (holdings[itemIndex].isLoaned()) {
					System.out.print("\nThis item is already on loan.");
				} else {
					String title = holdings[itemIndex].getTitle();
					if (holdings[itemIndex] instanceof Workwear) {
						int size = 
								((Workwear) holdings[itemIndex]).getSize();
						patrons[patronIndex].borrowItem(borrowedItem, 
								title, size);
						System.out.print("\n\n" + title + " has been "
								+ "loaned to " + patrons[patronIndex].getName());
						
					} else if (holdings[itemIndex] instanceof Book) {
						String author = 
								((Book) holdings[itemIndex]).getAuthor();
						String publisher = 
								((Book) holdings[itemIndex]).getPublisher();
						patrons[patronIndex].borrowItem(borrowedItem, title, 
								author, publisher);
						System.out.print("\n\n" + title + " has been loaned to " 
								+ patrons[patronIndex].getName());
						
					} else if (holdings[itemIndex] instanceof Tool) {
						
						boolean hasManual = 
								((Tool) holdings[itemIndex]).hasManual();
						String manualID = 
								((Tool) holdings[itemIndex]).getManual();
						
						if (hasManual) {
							if (patrons[patronIndex].getNumLoans() > 
							patrons[patronIndex].getMaxLoans() - 1) {
								System.out.print("The Patron cannot borrow the manual "
										+ "and tool as they already have "
										+ patrons[patronIndex].getNumLoans() 
										+ " current loans.");
							} else {
								int manualIndex = 0;
								for (int i = 0; i < numItems; i++) {
									if (manualID.equalsIgnoreCase(holdings[i].getID())) {
										manualIndex = i;
										i = numItems;
									}
								}
								if (holdings[manualIndex].isLoaned()) {
									System.out.print("\nThe tool cannot be borrowed "
											+ "because the manual is not available.");
								} else {
									String manufacturer = 
											((Tool) holdings[itemIndex]).getManufacturer();
									String model = ((Tool) holdings[itemIndex]).getModel();
									
									patrons[patronIndex].borrowItem(borrowedItem, 
											title, manufacturer, model, hasManual, 
											manualID);
									
									String manualTitle = 
											holdings[manualIndex].getTitle();
									String author = 
											((Book) holdings[manualIndex]).getAuthor();
									String publisher = 
											((Book) holdings[manualIndex]).getPublisher();
									patrons[patronIndex].borrowItem(manualID, manualTitle, 
											author, publisher);
									holdings[manualIndex].borrowItem();
									System.out.print("\n\n" + title + " and " + manualTitle 
											+ " have been loaned to " 
											+ patrons[patronIndex].getName());
								}
							}
						}
					}
					holdings[itemIndex].borrowItem();
				}
			}
			returnToMenu();
			
		// menuSelect = 7 allows users to return an Item, making it available
		// and removing it from the Patron's list of loans. 
		} else if (menuSelect.equals("7")) {
			System.out.print("\n\n-----------------Returning-----------------"
					+ "\n\n Enter the ID of the patron who wishes to return "
					+ "an item: ");
			String patronID = console.nextLine();
			int patronIndex = 0;
			boolean idFound = false;
			for (int i = 0; i < numPatrons; i++) {
				if (patronID.equalsIgnoreCase(patrons[i].getID())) {
					patronIndex = i;
					i = numPatrons;
					idFound = true;
				}
			}
			if (!idFound) {
				System.out.print("\nPatron ID not found.");
			} else {
				System.out.print("\nEnter the ID code of the Item to "
						+ "be returned: ");
				String returnedItem = console.nextLine();
				int itemIndex = 0;
				boolean itemFound = false;
				for (int i = 0; i < numItems; i++) {
					if (returnedItem.equalsIgnoreCase(holdings[i].getID())) {
						itemIndex = i;
						i = numItems;
						itemFound = true;
					}
				}
				if(!itemFound) {
					System.out.print("\nItem not found.");
				} else {
					if (patrons[patronIndex].returnItem(returnedItem)) {
						holdings[itemIndex].returnItem();
						if (returnedItem.charAt(0) == 'T') {
							if (((Tool) holdings[itemIndex]).hasManual()) {
								int manualIndex = 0;
								for (int i = 0; i < numItems; i++) {
									if (((Tool) holdings[itemIndex]).getManual()
											.equalsIgnoreCase(holdings[i].getID())) {
										manualIndex = i;
										i = numItems;
									}
								}
								holdings[manualIndex].returnItem();
								patrons[patronIndex].returnItem(((Tool) holdings[itemIndex])
										.getManual());
							}
						}
					} else {
						System.out.print("Return failed - " 
								+ holdings[itemIndex].getTitle() + " not on loan to " 
								+ patrons[patronIndex].getName() + ".");
					}
				}
			}
			returnToMenu();
			
		// method to exit the program. 
		} else if (menuSelect.equals("8")) {
			quitDatabase();
			
		// This block catches incorrect user input. 
		} else {
			System.out.print("\n" + menuSelect + " isn't a valid choice. "
					+ "Please try again.");
			displayMenu();
		}
	}
	
	//called at the end of every major if else block in displayMenu
	//so that the menu is reloaded after an action is finished. 
	public void returnToMenu() {
		System.out.print("\n\nPress Enter↵ to return to Menu.");
		console.nextLine();
		displayMenu();
	}
	
	public void quitDatabase() {
		System.out.print("\nExiting Database. Goodbye.");
		System.exit(0);
	}

	public static void main(String args[]) {
		StageC obj = new StageC();
	}
}