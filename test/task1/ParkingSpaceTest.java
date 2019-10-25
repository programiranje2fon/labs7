package task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task1.ParkingSpace;
import task1.Vehicle;
import test.TestUtil;

public class ParkingSpaceTest {

	private ParkingSpace instance;

	@Before
	public void setUp() throws Exception {
		instance = new ParkingSpace();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void attribute_free() {
		assertTrue("There is no attribute \"free\" declared", TestUtil.doesFieldExist(ParkingSpace.class, "free"));
	}
	
	@Test
	public void attribute_free_visibility() {
		assertTrue("Attribute \"free\" is not private", TestUtil.hasFieldModifier(ParkingSpace.class, "free", Modifier.PRIVATE));
	}
	
	@Test
	public void attribute_vehicle() {
		assertTrue("There is no attribute \"vehicle\" declared", TestUtil.doesFieldExist(ParkingSpace.class, "vehicle"));
	}
	
	@Test
	public void attribute_vehicle_visibility() {
		assertTrue("Attribute \"vehicle\" is not private", TestUtil.hasFieldModifier(ParkingSpace.class, "vehicle", Modifier.PRIVATE));
	}
	
	@Test
	public void method_setFree_true() {
		instance.setFree(true);
		boolean slobodnoValue = (boolean) TestUtil.getFieldValue(instance, "free");
		assertEquals("After passing as argument \"true\", the attribute \"free\" should have value \"true\"", true, slobodnoValue);
	}
	
	@Test
	public void method_setFree_false() {
		instance.setFree(false);
		boolean slobodnoValue = (boolean) TestUtil.getFieldValue(instance, "free");
		assertEquals("After passing as argument \"false\", the attribute \"free\" should have value \"false\"", false, slobodnoValue);
	}
	
	@Test
	public void method_isFree_false() {
		instance.setFree(false);
		assertEquals("When attribute \"free\" is \"false\", the method \"isFree()\" should return \"false\"", false, instance.isFree());
	}
	
	@Test
	public void method_isFree_true() {
		instance.setFree(true);
		assertEquals("When attribute \"free\" is \"true\", the method \"isFree()\" should return \"true\"", true, instance.isFree());
	}
	
	@Test
	public void method_setVehicle() {
		Vehicle v = new Vehicle();
		instance.setVehicle(v);
		
		Vehicle voziloValue = (Vehicle) TestUtil.getFieldValue(instance, "vehicle");

		assertTrue("The method does not set the correct value to the attribute \"vehicle\"", v == voziloValue);
	}
	
	@Test
	public void method_getVehicle() {
		Vehicle voziloValue = (Vehicle) TestUtil.getFieldValue(instance, "vehicle");
		
		assertTrue("The method does not return the value of the attribute \"vehicle\"", voziloValue == instance.getVehicle());
	}
	
	@Test
	public void method_toString_free() {
		instance.setFree(true);
		assertEquals("The return value does not contain the correct String", "FREE", instance.toString());
	}
	
	@Test
	public void method_toString_notFree() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AA123BB");
		
		instance.setVehicle(v1);
		instance.setFree(false);
		assertEquals("The return value does not contain the correct String", "OCCUPIED, "+instance.getVehicle().toString(), instance.toString());
	}
	
}
