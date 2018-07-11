import java.util.*;

public class Location {
	private int locId;
	private String locName;
	private String locZip;
	private double rate;
	protected ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

	public Location(int locId, String locName, String locZip, double rate) {
		this.locId = locId;
		this.locName = locName;
		this.locZip = locZip;
		this.rate = rate; 
	}

	public String getLocZip() {
		return locZip;
	}

	public String getLocName() {
		return locName;
	}

	public double getRate() {
		return rate;
	}

	public int findAvailableVehicles() {
		int count = 0;
		for (Vehicle vehicle: vehicles) {
			if(!vehicle.isRented()) {
				count++;
			} 
		}
		return count;
	}

	public double getDailyRevenue() {
		int rentedVehicles = 0;
		for(Vehicle vehicle: vehicles) {
			if(vehicle.isRented()) {
				rentedVehicles++;
			}
		}
		return rentedVehicles * rate;
	}

	public String toString() {
		return "\nID: " + locId + "\nName: " + locName + "\nZipCode: " + locZip + "\nDaily Rate: " + rate;
	}
}