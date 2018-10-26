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
	public void atribut_mesta() {
		assertTrue("U klasi nije definisan atribut parkingSpaces", TestUtil.doesFieldExist(Garage.class, "parkingSpaces"));
	}
	
	@Test
	public void atribut_mesta_vidljivost() {
		assertTrue("Atribut parkingSpaces nije privatan", TestUtil.hasFieldModifier(Garage.class, "parkingSpaces", Modifier.PRIVATE));
	}
	
	@Test
	public void konstruktor_GarazaInt() {
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(instance, "parkingSpaces");
		
		assertTrue("Za pozvani konstruktor sa argumentom 5, atribut parkingSpaces nije inicijalizovan", mestaValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 5, atribut namirnice nije kapaciteta 5, vec "+mestaValue.length, 5, mestaValue.length);
		
		for (ParkingSpace parkingMesto : mestaValue) {
			if (parkingMesto == null) {
				fail("Za pozvani konstruktor sa argumentom 5, nisu inicijalizovani svi elementi atributa parkingSpaces");
			} else if (!parkingMesto.isFree()) {
				fail("Za pozvani konstruktor sa argumentom 5, u nizu parkingSpaces nemaju svi elementi u atributu slobodan vrednost \"true\"");
			}
		}
	}
	
	@Test
	public void konstruktor_GarazaInt_brojMestaManjiOd1() {
		Garage g1 = new Garage(0);
		
		assertTrue("NE ispisuje se rec GRESKA u slucaju da je prosledjeno 0 konstruktoru Garaza(int)", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(g1, "parkingSpaces");
		
		assertTrue("Za pozvani konstruktor sa argumentom 0, atribut parkingSpaces nije inicijalizovan", mestaValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 0, atribut parkingSpaces nema kapacitet 40, vec "+mestaValue.length, 40, mestaValue.length);
		
		for (ParkingSpace parkingMesto : mestaValue) {
			if (parkingMesto == null) {
				fail("Za pozvani konstruktor sa argumentom 0, nisu inicijalizovani svi elementi atributa parkingSpaces");
			} else if (!parkingMesto.isFree()) {
				fail("Za pozvani konstruktor sa argumentom 0, u nizu parkingSpaces nemaju svi elementi u atributu slobodan vrednost \"true\"");
			}
		}
	}
	
	@Test
	public void konstruktor_Garaza() {
		ParkingSpace[] mesta = new ParkingSpace[1];
		
		Garage g1 = new Garage(mesta);
		
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(g1, "parkingSpaces");
		
		assertTrue("Za pozvani konstruktor Garaza() sa prosledjenim nizom objekata ParkingMesto, atribut parkingSpaces ne sadrzi prosledjeni niz", mesta == mestaValue);
	}
	
	@Test
	public void konstruktor_Garaza_null() {
		new Garage(null);
		
		assertTrue("NE ispisuje se rec GRESKA u slucaju da je prosledjeno null konstruktoru Garaza()", outContent.toString().trim().equalsIgnoreCase("ERROR"));
	}
	
	@Test
	public void metoda_daLiImaSlobodnih_True() {
		Garage g1 = new Garage(1);
		
		assertEquals("U slucaju kada nije uvedeno nijedno vozilo, metoda ne vraca true", true, g1.anyFreeSpace());
	}
	
	@Test
	public void metoda_daLiImaSlobodnih_False() {
		Vehicle v1 = new Vehicle();
		
		Garage g1 = new Garage(1);
		g1.parkTheVehicle(v1);
		
		assertEquals("U slucaju kada je uvedeno jedno vozilo, a kapacitet garaze je 1, metoda ne vraca false", false, g1.anyFreeSpace());
	}
	
	@Test
	public void metoda_uvedi() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AB123XY");
		instance.parkTheVehicle(v1);
		
		ParkingSpace[] mestaValue = (ParkingSpace[]) TestUtil.getFieldValue(instance, "parkingSpaces");
		
		assertTrue("Nakon sto se uvede novo vozilo, a u nizu parkingSpaces ima slobodnih mesta, to vozilo se ne nalazi u nizu parkingSpaces", Arrays.stream(mestaValue).anyMatch(m -> m.getVehicle() != null && m.getVehicle() == v1));
	}
	
	@Test
	public void metoda_uvedi_nemaMesta() {
		Vehicle v1 = new Vehicle();
		Vehicle v2 = new Vehicle();
		
		Garage g1 = new Garage(1);
		g1.parkTheVehicle(v1);
		g1.parkTheVehicle(v2);
		
		assertTrue("NE ispisuje se poruka THE GARAGE IS FULL u slucaju da je uvedeno novo vozilo u garazu u kojoj nema slobodnih mesta", outContent.toString().trim().equalsIgnoreCase("THE GARAGE IS FULL"));
	}
	
	@Test
	public void metoda_izvedi() {
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
		
		assertTrue("U slucaju da se pozove metoda sa prosledjenim vozilom sa registracijom koju vec poseduje vozilo koje je na parkingu, to vozilo se ne izbaci iz niza", !Arrays.stream(mestaValue).anyMatch(m -> m.getVehicle() != null && m.getVehicle().equals(v3)));
	}
	
	@Test
	public void metoda_ispisi() {
		Vehicle v1 = new Vehicle();
		v1.setRegNum("AB123XY");
		instance.parkTheVehicle(v1);
		
		Garage g1 = new Garage(1);
		g1.parkTheVehicle(v1);
		instance.print();
 		String ocekivaniIspis =
				"1. OCCUPIED, Registration number: AB123XY" + System.lineSeparator() +
				"2. FREE" + System.lineSeparator() +
				"3. FREE" + System.lineSeparator() +
				"4. FREE" + System.lineSeparator() +
				"5. FREE";
		
		ocekivaniIspis = ocekivaniIspis.replaceAll("\\s","");
		String ispis = outContent.toString().replaceAll("\\s","");
		
		assertTrue("Metoda ne ispisuje dobro sve podatke o parking mestima", ispis.equalsIgnoreCase(ocekivaniIspis));
	}
}
