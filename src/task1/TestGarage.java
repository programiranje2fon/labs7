package task1;

public class TestGarage {

	public static void main(String[] args) {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("BG123-AB");
		
		Vehicle v2 = new Vehicle();
		v2.setRegNum("NS456-CD");
		
		
		Garage g1 = new Garage(10);
		g1.parkTheVehicle(v1);
		g1.parkTheVehicle(v2);
		
		g1.unparkVehicle(v1);
	}
}
