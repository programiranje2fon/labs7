package task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task1.Vehicle;
import test.TestUtil;

public class VehicleTest {

	private Vehicle instance;

	@Before
	public void setUp() throws Exception {
		instance = new Vehicle();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void attribute_regNum() {
		assertTrue("There is no attribute \"regNum\" declared", TestUtil.doesFieldExist(Vehicle.class, "regNum"));
	}
	
	@Test
	public void attribute_regNum_visibility() {
		assertTrue("Attribute \"regNum\" is not private", TestUtil.hasFieldModifier(Vehicle.class, "regNum", Modifier.PRIVATE));
	}
	
	@Test
	public void method_setRegNum() {
		instance.setRegNum("AA112BB");
		String registarskiBrojValue = (String) TestUtil.getFieldValue(instance, "regNum");
		assertEquals("After passing as argument \"AA112BB\", the attribute \"regNum\" should have the value \"regNum\"", "AA112BB", registarskiBrojValue);
	}
	
	@Test
	public void method_getRegNum() {
		String registarskiBrojValue = (String) TestUtil.getFieldValue(instance, "regNum");

		assertEquals("The method does not return the value of the attribute \"regNum\"", registarskiBrojValue, instance.getRegNum());
	}
	
	@Test
	public void method_equals_wrongType() {
		assertEquals("When an object of a different type is passed, the method should return false", false, instance.equals(new Object()));
	}
	
	@Test
	public void method_equals_same() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AA123BB");
		Vehicle v2 = new Vehicle();
		v2.setRegNum("AA123BB");
		
		assertEquals("If the attribute \"regNum\" has the value \"AA123BB\", and the object passed as argument has the same \"regNum\" of \"AA123BB\", the method should return true", true, v1.equals(v2));
	}
	
	@Test
	public void method_equals_different() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AA123BB");
		Vehicle v2 = new Vehicle();
		v2.setRegNum("cc456DD");
		
		assertEquals("If the attribute \"regNum\" has the value \"AA123BB\", and the object passed as argument has the same \"regNum\" of  \"cc456DD\", the method should return false", false, v1.equals(v2));
	}
	
	@Test
	public void method_toString() {
		instance.setRegNum("AA123BB");
		assertTrue("The return value does not contain the value of the \"regNum\" attribute", instance.toString().contains("AA123BB"));
	}
	
}
