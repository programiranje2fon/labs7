package task2;

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
	public void attribute_name() {
		assertTrue("There is no attribute \"name\" declared", TestUtil.doesFieldExist(FoodItem.class, "name"));
	}
	
	@Test
	public void attribute_name_visibility() {
		assertTrue("Attribute \"name\" is not private", TestUtil.hasFieldModifier(FoodItem.class, "name", Modifier.PRIVATE));
	}
	
	@Test
	public void attribute_price() {
		assertTrue("There is no attribute \"price\" declared", TestUtil.doesFieldExist(FoodItem.class, "price"));
	}
	
	@Test
	public void attribute_price_visibility() {
		assertTrue("Attribute \"price\" is not private", TestUtil.hasFieldModifier(FoodItem.class, "price", Modifier.PRIVATE));
	}
	
	@Test
	public void method_setName() {
		instance.setName("bread");
		String nazivValue = (String) TestUtil.getFieldValue(instance, "name");
		assertEquals("After passing as argument \"bread\", the attribute \"name\" should have the value \"bread\"", "bread", nazivValue);
	}
	
	@Test
	public void method_getName() {
		String nazivValue = (String) TestUtil.getFieldValue(instance, "name");

		assertEquals("The method does not return the value of the attribute \"name\"", nazivValue, instance.getName());
	}
	
	@Test
	public void method_setPrice() {
		instance.setPrice(50.3);
		double cenaValue = (double) TestUtil.getFieldValue(instance, "price");
		assertEquals("After passing as argument \"50.3\", the attribute \"price\" should have the value \"50.3\"", 50.3, cenaValue, 0.001);
	}
	
	@Test
	public void method_getPrice() {
		double cenaValue = (double) TestUtil.getFieldValue(instance, "price");
		
		assertEquals("The method does not return the value of the attribute \"price\"", cenaValue, instance.getPrice(), 0.001);
	}
	
}
