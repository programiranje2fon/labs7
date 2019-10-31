package task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task1.Garage;
import task1.ParkingSpace;
import task1.Vehicle;
import test.TestUtil;

public class GarageTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private Garage instance;

	@Before
	public void setUp() throws Exception {
		instance = new Garage(5);
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	public void attribute_parkingSpaces() {
		assertTrue("There is no attribute \"parkingSpaces\" declared", TestUtil.doesFieldExist(Garage.class, "parkingSpaces"));
	}
	
	@Test
	public void attribute_parkingSpaces_visibility() {
		assertTrue("Attribute \"parkingSpaces\" is not private", TestUtil.hasFieldModifier(Garage.class, "parkingSpaces", Modifier.PRIVATE));
	}
	
	@Test
	public void constructor_Garage() {
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(instance, "parkingSpaces");
		
		assertTrue("When the argument is 5, the attribute \"parkingSpaces\" is not initialized", mestaValue != null);
		assertEquals("When the argument is 5, the attribute \"parkingSpaces\" does not have length 5, but "+mestaValue.length, 5, mestaValue.length);
		
		for (ParkingSpace parkingMesto : mestaValue) {
			if (parkingMesto == null) {
				fail("When the argument is 5, not all elements from the array \"parkingSpaces\" are initialized");
			} else if (!parkingMesto.isFree()) {
				fail("When the argument is 5, not all elements from the array \"parkingSpaces\" has the value of \"true\"");
			}
		}
	}
	
	@Test
	public void constructor_Garage_spacesLessThan1() {
		Garage g1 = new Garage(0);
		
		assertTrue("For the argument 0, the method should print ERROR to the output", outContent.toString().toLowerCase().contains("error"));
		
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(g1, "parkingSpaces");
		
		assertTrue("When the argument is 0, the attribute \"parkingSpaces\" is not initialized", mestaValue != null);
		assertEquals("When the argument is 0, the attribute \"parkingSpaces\" does not have length 40, but "+mestaValue.length, 40, mestaValue.length);
		
		for (ParkingSpace parkingMesto : mestaValue) {
			if (parkingMesto == null) {
				fail("When the argument is 0, not all elements from the array \"parkingSpaces\" are initialized");
			} else if (!parkingMesto.isFree()) {
				fail("When the argument is 0, not all elements from the array \"parkingSpaces\" has the value of \"true\"");
			}
		}
	}
	
	@Test
	public void constructor_Garage_spaces1() {
		ParkingSpace[] mesta = new ParkingSpace[1];
		
		Garage g1 = new Garage(mesta);
		
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(g1, "parkingSpaces");
		
		assertTrue("When the argument is an array of ParkingSpace instances, the attribute \"parkingSpaces\" does not hold the reference to the same array", mesta == mestaValue);
	}
	
	@Test
	public void constructor_Garage_spacessNull() {
		new Garage(null);
		
		assertTrue("For the argument NULL, the method should print ERROR to the console", outContent.toString().toLowerCase().contains("error"));
	}
	
	@Test
	public void method_anyFreeSpace_True() {
		Garage g1 = new Garage(1);
		
		assertEquals("When the parking is empty, the method should return \"true\"", true, g1.anyFreeSpace());
	}
	
	@Test
	public void method_anyFreeSpace_False() {
		Vehicle v1 = new Vehicle();
		
		Garage g1 = new Garage(1);
		g1.parkTheVehicle(v1);
		
		assertEquals("If the array is full, the method should return \'false\'", false, g1.anyFreeSpace());
	}
	
	@Test
	public void method_parkTheVehicle() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AB123XY");
		instance.parkTheVehicle(v1);
		
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(instance, "parkingSpaces");
		
		assertTrue("After parking a car (by calling the method parkTheVehicle(Vehicle)) when there are available spaces in the array, the vehicle cannot be found in the array \"parkingSpaces\"", Arrays.stream(mestaValue).anyMatch(m -> m.getVehicle() != null && m.getVehicle() == v1));
	}
	
	@Test
	public void method_parkTheVehicle_noAvailable() {
		Vehicle v1 = new Vehicle();
		Vehicle v2 = new Vehicle();
		
		Garage g1 = new Garage(1);
		g1.parkTheVehicle(v1);
		g1.parkTheVehicle(v2);
		
		assertTrue("When the array is full, when calling the method parkTheVehicle(Vehicle), the method des not print THE GARAGE IS FULL to the output", outContent.toString().toLowerCase().contains("the garage is full"));
	}
	
	@Test
	public void method_unparkVehicle() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AB123XY");
		Vehicle v2 = new Vehicle();
		v2.setRegNum("YU763KJ");
		Vehicle v3 = new Vehicle();
		v3.setRegNum("OK312SA");
		
		instance.parkTheVehicle(v1);
		instance.parkTheVehicle(v2);
		instance.parkTheVehicle(v3);
		
		instance.unparkVehicle(v3);
		
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(instance, "parkingSpaces");
		
		assertTrue("When passing as argument a plate number that is already in the array, that car (the array element) is not removed from the \"parkingSpaces\".", !Arrays.stream(mestaValue).anyMatch(m -> m.getVehicle() != null && m.getVehicle().equals(v3)));
	}
	
	@Test
	public void method_print() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AB123XY");
		instance.parkTheVehicle(v1);
		
		instance.print();
		
		String[] linijeIspisa = outContent.toString().split(System.lineSeparator());
		
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the first line of the output should contain \"1.\"", linijeIspisa[0].contains("1."));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the first line of the output should contain the word \"OCCUPIED\"", linijeIspisa[0].toLowerCase().contains("occupied"));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the first line of the output should contain the registration number of the vehicle", linijeIspisa[0].contains("AB123XY"));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the second line of the output should contain \"2.\"", linijeIspisa[1].contains("2."));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the second line of the output should contain the word \"FREE\"", linijeIspisa[1].toLowerCase().contains("free"));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the third line of the output should contain \"3.\"", linijeIspisa[2].contains("3."));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the third line of the output should contain the word \"FREE\"", linijeIspisa[3].toLowerCase().contains("free"));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the fourth line of the output should contain \"4.\"", linijeIspisa[3].contains("4."));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the fourth line of the output should contain the word \"FREE\"", linijeIspisa[3].toLowerCase().contains("free"));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the fifth line of the output should contain \"5.\"", linijeIspisa[4].contains("5."));
		assertTrue("If the garage is instantiated with five parking spaces, and only the first one is occupied, the fifth line of the output should contain the word \"FREE\"", linijeIspisa[4].toLowerCase().contains("free"));
	}
}
