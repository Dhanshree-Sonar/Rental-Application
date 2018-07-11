import java.io.*;
import java.util.HashMap;

public class DBHelper {
	public static HashMap<Integer, Location> readLocations() {
		HashMap<Integer, Location> locations = new HashMap<Integer, Location>();
		try {
			FileReader locFr = new FileReader("Locations.csv");
			BufferedReader locReader = new BufferedReader(locFr);
			String line = locReader.readLine();
			while ((line = locReader.readLine()) != null) {
				String[] record = line.split(",");
				int locId = Integer.parseInt(record[0]);
				String locName = record[1];
				String locZip = record[2];
				double rate = Double.parseDouble(record[3]);
				locations.put(locId, new Location(locId, locName, locZip, rate));
			}
			locReader.close();
			FileReader vehicleFr = new FileReader("Vehicles.csv");
			BufferedReader vehicleReader = new BufferedReader(vehicleFr);
			line = vehicleReader.readLine();
			while ((line = vehicleReader.readLine()) != null) {
				String[] record = line.split(",");
				int locId = Integer.parseInt(record[0]);
				int vehicleId = Integer.parseInt(record[1]);
				boolean isRented = Boolean.parseBoolean(record[2]);
				Location location = locations.get(locId);
				location.vehicles.add(new Vehicle(vehicleId, isRented));
			}
			vehicleReader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return locations;
	}
}

