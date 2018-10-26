package test.task1;

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
	public void atribut_registarskiBroj() {
		assertTrue("U klasi nije definisan atribut regNum", TestUtil.doesFieldExist(Vehicle.class, "regNum"));
	}
	
	@Test
	public void atribut_registarskiBroj_vidljivost() {
		assertTrue("Atribut regNum nije privatan", TestUtil.hasFieldModifier(Vehicle.class, "regNum", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setRegistarskiBroj() {
		instance.setRegNum("AA112BB");
		String registarskiBrojValue = (String) TestUtil.getFieldValue(instance, "regNum");
		assertEquals("Nakon poziva metode setRegistarskiBroj(String) sa prosledjenim argumentom \"AA112BB\", vrednost atributa regNum se nije promenila na \"AA112BB\"", "AA112BB", registarskiBrojValue);
	}
	
	@Test
	public void metoda_getRegistarskiBroj() {
		String registarskiBrojValue = (String) TestUtil.getFieldValue(instance, "regNum");

		assertEquals("Metoda getRegistarskiBroj ne vraca vrednost atributa regNum", registarskiBrojValue, instance.getRegNum());
	}
	
	@Test
	public void metoda_equals_pogresanTip() {
		assertEquals("Metoda equals ne vraca false ako je prosledjen objekat koji nije klase Vozilo", false, instance.equals(new Object()));
	}
	
	@Test
	public void metoda_equals_isti() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AA123BB");
		Vehicle v2 = new Vehicle();
		v2.setRegNum("AA123BB");
		
		assertEquals("Metoda equals ne vraca true kada je pozvana nad vozilom sa registracijom \"AA123BB\", a prosledjeno je vozilo sa registarskim brojem \"AA123BB\"", true, v1.equals(v2));
	}
	
	@Test
	public void metoda_equals_razliciti() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AA123BB");
		Vehicle v2 = new Vehicle();
		v2.setRegNum("cc456DD");
		
		assertEquals("Metoda equals ne vraca false kada je pozvana nad vozilom sa registracijom \"AA123BB\", a prosledjeno je vozilo sa registarskim brojem \"cc456DD\"", false, v1.equals(v2));
	}
	
	@Test
	public void metoda_toString() {
		instance.setRegNum("AA123BB");
		assertEquals("Metoda toString ne vraca String u odgovarajucem formatu", "Registration number: AA123BB", instance.toString());
	}
	
}
