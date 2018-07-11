public class Vehicle {
	private int vehicleId;
	private boolean isRented;

	public Vehicle(int vehicleId, boolean isRented) {
		this.vehicleId = vehicleId;
		this.isRented = isRented;
	}
	
	public boolean isRented() {
		return isRented;
	}
	
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", isRented=" + isRented + "]";
	}
}
