package test.task2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task2.FoodItem;
import task2.ConsumerBasket;
import test.TestUtil;

public class ConsumerBasketTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private ConsumerBasket instance;

	@Before
	public void setUp() throws Exception {
		instance = new ConsumerBasket(5);
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
	public void atribut_namirnice() {
		assertTrue("U klasi nije definisan atribut food", TestUtil.doesFieldExist(ConsumerBasket.class, "food"));
	}
	
	@Test
	public void atribut_namirnicevidljivost() {
		assertTrue("Atribut food nije privatan", TestUtil.hasFieldModifier(ConsumerBasket.class, "food", Modifier.PRIVATE));
	}
	
	@Test
	public void konstruktor_PotrosackaKorpa() {
		FoodItem[] namirniceValue = (FoodItem[]) TestUtil.getFieldValue(instance, "food");
		
		assertTrue("Za pozvani konstruktor sa argumentom 5, atribut food nije inicijalizovan", namirniceValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 5, atribut food nije kapaciteta 5, vec "+namirniceValue.length, 5, namirniceValue.length);
	}
	
	@Test
	public void konstruktor_PotrosackaKorpa_kapacitetManjiOd1() {
		ConsumerBasket g1 = new ConsumerBasket(0);
		
		assertTrue("NE ispisuje se rec ERROR u slucaju da je prosledjeno 0 konstruktoru PotrosackaKorpa()", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		FoodItem[] namirniceValue = (FoodItem[]) TestUtil.getFieldValue(g1, "food");
		
		assertTrue("Za pozvani konstruktor sa argumentom 0, atribut food nije inicijalizovan", namirniceValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 0, atribut food nema kapacitet 10, vec "+namirniceValue.length, 10, namirniceValue.length);
	}
	
	@Test
	public void metoda_dodajUKorpu_imaMesta() {
		FoodItem n1 = new FoodItem();
		n1.setName("sok");
		instance.addToTheBasket(n1);
		
		FoodItem[] namirniceValue = (FoodItem[]) TestUtil.getFieldValue(instance, "food");
		
		assertTrue("Nakon sto se doda nova namirnica u korpu, a u nizu food ima slobodnih mesta, ta namirnica se ne nalazi u nizu food", Arrays.stream(namirniceValue).anyMatch(m -> m == n1));
	}
	
	@Test
	public void metoda_uvedi_nemaMesta() {
		FoodItem n1 = new FoodItem();
		FoodItem n2 = new FoodItem();
		
		ConsumerBasket pk1 = new ConsumerBasket(1);
		pk1.addToTheBasket(n1);
		pk1.addToTheBasket(n2);
		
		assertTrue("NE ispisuje se poruka THE BASKET IS FULL u slucaju da je dodata nova namirnica u korpu u kojoj nema slobodnih mesta", outContent.toString().trim().equalsIgnoreCase("THE BASKET IS FULL"));
	}
	
	@Test
	public void metoda_izracunajUkupnuCenu() {
		FoodItem n1 = new FoodItem();
		n1.setPrice(10);
		FoodItem n2 = new FoodItem();
		n2.setPrice(15);
		FoodItem n3 = new FoodItem();
		n3.setPrice(20);
		
		instance.addToTheBasket(n1);
		instance.addToTheBasket(n2);
		instance.addToTheBasket(n3);
		
		assertEquals("Kada su u korpu dodate namirnice sa cenama 10, 15 i 20 dinara, metoda ne vraca ukupnu cenu 45", 45, instance.totalPrice(), 0.001);
	}
	
	@Test
	public void metoda_pronadjiNajskuplju() {
		FoodItem n1 = new FoodItem();
		n1.setPrice(10);
		FoodItem n2 = new FoodItem();
		n2.setPrice(15);
		FoodItem n3 = new FoodItem();
		n3.setPrice(20);
		
		instance.addToTheBasket(n1);
		instance.addToTheBasket(n2);
		instance.addToTheBasket(n3);
		
		assertTrue("Kada su u korpu dodate namirnice sa cenama 10, 15 i 20 dinara, metoda ne vraca kao najskuplju trecu namirnicu", instance.theMostExpensive() == n3);
	}
}
