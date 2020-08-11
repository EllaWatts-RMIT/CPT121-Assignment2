//class for Item type Tool. Has inherited member variables,
// and 4 unique member variables, manufacturer, model, 
// hasManual, manualID. Latter two are for determining and
// linking a manual to the tool. 

public class Tool extends Item {
	
	private String manufacturer;
	private String model;
	private boolean hasManual;
	private String manualID;
	
	public Tool(String id, String title, String manufacturer, 
			String model, boolean hasManual, String manualID) {
		super(id, title);
		this.manufacturer = manufacturer;
		this.model = model;
		this.hasManual = hasManual;
		this.manualID = manualID; 
	}
	
	// inherited from Item - for displaying a specific Item details
	public void displayItem() {
		System.out.printf("%-10s %-20s %-15s %-10s %-10s %-10s", 
				"Item ID", "Title", "Manufacturer", "Model No.", 
				"Manual ID", "Available");
		String available = "No";
		if (this.loaned == false) {
			available = "Yes";
		}
		System.out.printf("\n----------------------------------"
				+ "--------------\n%-10s %-20s %-15s %-10s %-10s %-10s", 
				id, title, manufacturer, model, manualID, available);
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String getModel() {
		return model;
	}
	
	public boolean hasManual() {
		return hasManual;
	}
	
	public String getManual() {
		return manualID;
	}
}