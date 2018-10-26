package test.task1;

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
	public void atribut_slobodno() {
		assertTrue("U klasi nije definisan atribut free", TestUtil.doesFieldExist(ParkingSpace.class, "free"));
	}
	
	@Test
	public void atribut_slobodno_vidljivost() {
		assertTrue("Atribut free nije privatan", TestUtil.hasFieldModifier(ParkingSpace.class, "free", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_vozilo() {
		assertTrue("U klasi nije definisan atribut vehicle", TestUtil.doesFieldExist(ParkingSpace.class, "vehicle"));
	}
	
	@Test
	public void atribut_vozilo_vidljivost() {
		assertTrue("Atribut vehicle nije privatan", TestUtil.hasFieldModifier(ParkingSpace.class, "vehicle", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setSlobodno_true() {
		instance.setFree(true);
		boolean slobodnoValue = (boolean) TestUtil.getFieldValue(instance, "free");
		assertEquals("Nakon poziva metode setSlobodno(boolean) sa prosledjenim argumentom \"true\", atribut free ima vrednost \""+slobodnoValue+"\"", true, slobodnoValue);
	}
	
	@Test
	public void metoda_setSlobodno_false() {
		instance.setFree(false);
		boolean slobodnoValue = (boolean) TestUtil.getFieldValue(instance, "free");
		assertEquals("Nakon poziva metode setSlobodno(boolean) sa prosledjenim argumentom \"false\", atribut free ima vrednost \""+slobodnoValue+"\"", false, slobodnoValue);
	}
	
	@Test
	public void metoda_isSlobodno_false() {
		instance.setFree(false);
		assertEquals("Metoda isSlobodno() vraca \"true\", a vrednost atributa free je \"false\"", false, instance.isFree());
	}
	
	@Test
	public void metoda_isSlobodno_true() {
		instance.setFree(true);
		assertEquals("Metoda isSlobodno() vraca \"false\", a vrednost atributa free je \"true\"", true, instance.isFree());
	}
	
	@Test
	public void metoda_setVozilo() {
		Vehicle v = new Vehicle();
		instance.setVehicle(v);
		
		Vehicle voziloValue = (Vehicle) TestUtil.getFieldValue(instance, "vehicle");

		assertTrue("Metoda setVozilo() ne postavlja odgovarajucu vrednost u atribut vehicle", v == voziloValue);
	}
	
	@Test
	public void metoda_getVozilo() {
		Vehicle voziloValue = (Vehicle) TestUtil.getFieldValue(instance, "vehicle");
		
		assertTrue("Metoda getVozilo ne vraca vrednost atributa vehicle", voziloValue == instance.getVehicle());
	}
	
	@Test
	public void metoda_toString_slobodno() {
		instance.setFree(true);
		assertEquals("Metoda toString ne vraca String u odgovarajucem formatu", "FREE", instance.toString());
	}
	
	@Test
	public void metoda_toString_zauzeto() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AA123BB");
		
		instance.setVehicle(v1);
		instance.setFree(false);
		assertEquals("Metoda toString ne vraca String u odgovarajucem formatu", "OCCUPIED, "+instance.getVehicle().toString(), instance.toString());
	}
	
}
