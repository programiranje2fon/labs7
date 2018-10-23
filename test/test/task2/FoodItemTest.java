package test.task2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import task2.FoodItem;
import test.TestUtil;

public class FoodItemTest {

	private FoodItem instance;

	@Before
	public void setUp() throws Exception {
		instance = new FoodItem();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_naziv() {
		assertTrue("U klasi nije definisan atribut name", TestUtil.doesFieldExist(FoodItem.class, "name"));
	}
	
	@Test
	public void atribut_naziv_vidljivost() {
		assertTrue("Atribut name nije privatan", TestUtil.hasFieldModifier(FoodItem.class, "name", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_cena() {
		assertTrue("U klasi nije definisan atribut price", TestUtil.doesFieldExist(FoodItem.class, "price"));
	}
	
	@Test
	public void atribut_cena_vidljivost() {
		assertTrue("Atribut price nije privatan", TestUtil.hasFieldModifier(FoodItem.class, "price", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setNaziv() {
		instance.setName("bread");
		String nazivValue = (String) TestUtil.getFieldValue(instance, "name");
		assertEquals("Nakon poziva metode setNaziv(String) sa prosledjenim argumentom \"bread\", vrednost atributa name se nije promenila na tu vrednost", "bread", nazivValue);
	}
	
	@Test
	public void metoda_getNaziv() {
		String nazivValue = (String) TestUtil.getFieldValue(instance, "name");

		assertEquals("Metoda getNaziv ne vraca vrednost atributa name", nazivValue, instance.getName());
	}
	
	@Test
	public void metoda_setCena() {
		instance.setPrice(50.3);
		double cenaValue = (double) TestUtil.getFieldValue(instance, "price");
		assertEquals("Nakon poziva metode setCena(double) sa prosledjenim argumentom \"50.3\", vrednost atributa price se nije promenila na tu vrednost", 50.3, cenaValue, 0.001);
	}
	
	@Test
	public void metoda_getCena() {
		double cenaValue = (double) TestUtil.getFieldValue(instance, "price");
		
		assertEquals("Metoda getCena ne vraca vrednost atributa price", cenaValue, instance.getPrice(), 0.001);
	}
	
}
