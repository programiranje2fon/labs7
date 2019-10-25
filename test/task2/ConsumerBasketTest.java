package task2;

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
	public void attribute_food() {
		assertTrue("There is no attribute food declared", TestUtil.doesFieldExist(ConsumerBasket.class, "food"));
	}
	
	@Test
	public void attribute_food_visibility() {
		assertTrue("Attribute food is not private", TestUtil.hasFieldModifier(ConsumerBasket.class, "food", Modifier.PRIVATE));
	}
	
	@Test
	public void constructor_ConsumerBasket() {
		FoodItem[] namirniceValue = (FoodItem[]) TestUtil.getFieldValue(instance, "food");
		
		assertTrue("When passed argument is 5, the array food is not initialized", namirniceValue != null);
		assertEquals("When passed argument is 5, the length of the array food is not 5", 5, namirniceValue.length);
	}
	
	@Test
	public void constructor_ConsumerBasket_capacityLowerThan1() {
		ConsumerBasket g1 = new ConsumerBasket(0);
		
		assertTrue("When passed argument is 0, the construcotr should print ERROR to the output", outContent.toString().trim().equalsIgnoreCase("ERROR"));
		
		FoodItem[] namirniceValue = (FoodItem[]) TestUtil.getFieldValue(g1, "food");
		
		assertTrue("When passed argument is 0, the array food is not initialized", namirniceValue != null);
		assertEquals("When passed argument is 0, the length of the array food is not 10 (the default value)", 10, namirniceValue.length);
	}
	
	@Test
	public void method_addToTheBasket_thereIsAvailableSpace() {
		FoodItem n1 = new FoodItem();
		n1.setName("sok");
		instance.addToTheBasket(n1);
		
		FoodItem[] namirniceValue = (FoodItem[]) TestUtil.getFieldValue(instance, "food");
		
		assertTrue("If there is available space in the array food, after calling the method addToTheBasket(FoodItem), the food item is not present in the array food", Arrays.stream(namirniceValue).anyMatch(m -> m == n1));
	}
	
	@Test
	public void method_addToTheBasket_noAvailableSpace() {
		FoodItem n1 = new FoodItem();
		FoodItem n2 = new FoodItem();
		
		ConsumerBasket pk1 = new ConsumerBasket(1);
		pk1.addToTheBasket(n1);
		pk1.addToTheBasket(n2);
		
		assertTrue("In case there is no available space in the array food, after calling the method addToTheBasket(FoodItem), the method should print to the output THE BASKET IS FULL", outContent.toString().trim().equalsIgnoreCase("THE BASKET IS FULL"));
	}
	
	@Test
	public void method_totalPrice() {
		FoodItem n1 = new FoodItem();
		n1.setPrice(10);
		FoodItem n2 = new FoodItem();
		n2.setPrice(15);
		FoodItem n3 = new FoodItem();
		n3.setPrice(20);
		
		instance.addToTheBasket(n1);
		instance.addToTheBasket(n2);
		instance.addToTheBasket(n3);
		
		assertEquals("When there are three food items in the food array with the foolowing prices: 10, 15 and 20, the method should return the total price of 45", 45, instance.totalPrice(), 0.001);
	}
	
	@Test
	public void method_theMostExpensive() {
		FoodItem n1 = new FoodItem();
		n1.setPrice(10);
		FoodItem n2 = new FoodItem();
		n2.setPrice(15);
		FoodItem n3 = new FoodItem();
		n3.setPrice(20);
		
		instance.addToTheBasket(n1);
		instance.addToTheBasket(n2);
		instance.addToTheBasket(n3);
		
		assertTrue("When there are three food items in the food array with the foolowing prices: 10, 15 and 20, the method should return 20 as the most expensive item.", instance.theMostExpensive() == n3);
	}
}
