package task1;

public class Garage {

	private ParkingSpace[] parkingSpaces;

	public Garage(int capacity) {
		if (capacity > 0) {
			parkingSpaces = new ParkingSpace[capacity];
		} else {
			System.out.println("ERROR");
			parkingSpaces = new ParkingSpace[40];
		}

		for (int i = 0; i < parkingSpaces.length; i++) {
			parkingSpaces[i] = new ParkingSpace();
			parkingSpaces[i].setFree(true);
		}
	}

	public Garage(ParkingSpace[] parkSpaces) {
		if (parkSpaces != null) {
			this.parkingSpaces = parkSpaces;
		} else {
			System.out.println("ERROR");
		}
	}
	
	public void print() {
		for (int i = 0; i < parkingSpaces.length; i++) {
			System.out.println((i+1) + ". " + parkingSpaces[i].toString());
		}
	}

	public boolean anyFreeSpace() {
		for (ParkingSpace parkingSpace : parkingSpaces) {
			if (parkingSpace.isFree()) {
				return true;
			}
		}
		return false;
	}

	public void parkTheVehicle(Vehicle newVehicle) {
		for (ParkingSpace parkingSpace : parkingSpaces) {
			if (parkingSpace.isFree()) {
				parkingSpace.setVehicle(newVehicle);
				parkingSpace.setFree(false);
				return;
			}
		}

		System.out.println("THE GARAGE IS FULL");
	}

	public void unparkVehicle(Vehicle vehicle) {
		for (ParkingSpace parkingSpace : parkingSpaces) {
			if (!parkingSpace.isFree() && parkingSpace.getVehicle().equals(vehicle)) {
				parkingSpace.setVehicle(null);
				parkingSpace.setFree(true);
				break;
			}
		}
	}
}
