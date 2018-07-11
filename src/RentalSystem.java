import java.util.*;
public class RentalSystem {
	private HashMap<Integer, Location> locations = null;
	String option = null;
	Scanner in = new Scanner(System.in);

	public RentalSystem() {
		// Loading location and vehicle data into objects.
		locations = DBHelper.readLocations();
		menuOption();
	}

	//Searching locations by zipcode.
	public void searchByZip(String locZip) {
		Set<Integer> keys = locations.keySet();
		Iterator<Integer> it = keys.iterator();
		System.out.println("List of location(s) near " + locZip + ":");
		boolean isValid = false;
		while (it.hasNext()) {
			int locId = it.next();
			Location location = locations.get(locId);
			if (location.getLocZip().equals(locZip)) {
				isValid = true;
				System.out.println(location.getLocName());
			}
		}
		if (!isValid) {
			System.out.println("No rental location near " + locZip);
		}
	}

	//Find available vehicles at particular location.
	public void findAvailableVehicles(String locName) {
		Set<Integer> keys = locations.keySet();
		Iterator<Integer> it = keys.iterator();
		boolean isValid = false;
		while (it.hasNext()) {
			int locId = it.next();
			Location location = locations.get(locId);
			if (location.getLocName().equals(locName)) {
				isValid = true;
				System.out.println("Number of available vehicles at " + locName + ": " 
				+ location.findAvailableVehicles());
			}
		}
		if (!isValid) {
			System.out.println("Could not find location " + locName);
		}
	}

	//Display all the locations and their rates.
	public void listLocationAndRate() {
		Set<Integer> keys = locations.keySet();
		Iterator<Integer> it = keys.iterator();
		boolean isEmpty = true;
		System.out.println("Location  Rate");
		System.out.println("--------------");
		while (it.hasNext()) {
			isEmpty = false;
			int locId = it.next();
			Location location = locations.get(locId);
			System.out.println(location.getLocName() + "  " + location.getRate());
		}
		if (isEmpty) {
			System.out.println("No location data available ");
		}
	}

	//Calculate the daily revenue
	// Revenue = number of rented vehicles * daily rate.
	public void calculateDailyRevenue(String locName) {
		boolean isValid = false;
		for(Integer key: locations.keySet()) {
			Location location = locations.get(key);
			if(location.getLocName().equals(locName)) {
				isValid = true;
				double revenue = location.getDailyRevenue();
				System.out.println("Daily revenue for " + locName + " is: $" + revenue);
			}
		}
		if(!isValid) {
			System.out.println("Could not find location " + locName);
		}
	}

	//Display daily rate for particular location.
	public void findDailyRate(String locName) {
		boolean isValid = false;
		for(Integer key: locations.keySet()) {
			Location location = locations.get(key);
			if(location.getLocName().equals(locName)) {
				isValid = true;
				System.out.println("Daily rate for " + locName + " is: " + location.getRate());
			}
		}
		if(!isValid) {
			System.out.println("Could not find location " + locName);
		}
	}

	//Display all the data associated with location.
	public void displayLocationData(String locName) {
		boolean isValid = false;
		for(Integer key: locations.keySet()) {
			Location location = locations.get(key);
			if(location.getLocName().equals(locName)) {
				isValid = true;
				System.out.println("Informantion for " + locName + ":" + location);
				System.out.println("Total Vehicles: " + location.vehicles.size());
			}
		}
		if(!isValid) {
			System.out.println("Could not find location " + locName);
		}
	}
	
	//Customer Operations
	public void customerOptions() {
		while (true) {
			System.out.println("\n*********************************");
			System.out.println("1: Find locations by zipcode");
			System.out.println("2: List all rental locations");
			System.out.println("3: Find Daily Rate" );
			System.out.println("E: Exit");
			System.out.println("*********************************");
			System.out.println("Enter option>");
			option = in.nextLine();
			if (option.equals("E")) {
				break;
			} else if (option.equals("1")) {
				System.out.println("Enter zipcode>");
				String locZip = in.nextLine();
				searchByZip(locZip);
			} else if (option.equals("2")) {
				listLocationAndRate();
			} 
			else if (option.equals("3")) {
				System.out.println("Enter Location Name>");
				String locName = in.nextLine();
				findDailyRate(locName);
			} else {
				System.out.println("Invalid Option");
			}
		}
	}
	
	//Admin Operations
	public void adminOptions() {
		while (true) {
			System.out.println("\n*********************************");
			System.out.println("1: Find number of available vehicles");
			System.out.println("2: Output Data Associated with Location");
			System.out.println("E: Exit");
			System.out.println("*********************************");
			System.out.println("Enter option>");
			option = in.nextLine();
			if (option.equals("E")) {
				break;
			} else if (option.equals("1")) {
				System.out.println("Enter the Location Name>");
				String locName = in.nextLine();
				findAvailableVehicles(locName); 
			} else if (option.equals("2")) {
				System.out.println("Enter the Location Name>");
				String locName = in.nextLine();
				displayLocationData(locName);
			} else {
				System.out.println("Invalid Option");
			}
		}
	}
	
	//Finance/ Accountant opertaions
	public void financeOptions() {
		while (true) {
			System.out.println("\n*********************************");
			System.out.println("1: Calculate daily revenue");
			System.out.println("E: Exit");
			System.out.println("*********************************");
			System.out.println("Enter option>");
			option = in.nextLine();
			if (option.equals("E")) {
				break;
			} else if (option.equals("1")) {
				System.out.println("Enter the Location Name>");
				String locName = in.nextLine();
				calculateDailyRevenue(locName);
			} else {
				System.out.println("Invalid Option");
			}
		}
	}

	public void menuOption() {
		while (true) {
			System.out.println("\n*********************************");
			System.out.println("A: Admin");
			System.out.println("C: Customer");
			System.out.println("F: Finance / Accountant");
			System.out.println("Q: Quit");
			System.out.println("*********************************");
			System.out.println("Enter option>");
			option = in.nextLine();
			if (option.equals("Q")) {
				break;
			} else if (option.equals("A")) {
				adminOptions();
			} else if (option.equals("C")) {
				customerOptions();
			} else if (option.equals("F")) {
				financeOptions();
			} else {
				System.out.println("Invalid Option");
			}
		}
	}

	public static void main(String[] args) {
		new RentalSystem();
	}
}
