package task1;

public class ParkingSpace {

	private boolean free;
	private Vehicle vehicle;

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public String toString() {
		if (free) {
			return "FREE";
		} else {
			return "OCCUPIED, " + vehicle.toString();
		}
	}


}
