package task2;

public class TestConsumerBasket {

	public static void main(String[] args) {
		FoodItem item1 = new FoodItem();
		item1.setName("bread");
		item1.setPrice(50);
		
		FoodItem item2 = new FoodItem();
		item2.setName("milk");
		item2.setPrice(80);
		
		FoodItem item3 = new FoodItem();
		item3.setName("chocolate");
		item3.setPrice(100);
		
		ConsumerBasket basket = new ConsumerBasket(15);
		basket.addToTheBasket(item1);
		basket.addToTheBasket(item2);
		basket.addToTheBasket(item3);
		
		System.out.println(basket.totalPrice());
		
		FoodItem mostExpensive = basket.theMostExpensive();
		
		if (mostExpensive != null) {
			System.out.println(mostExpensive.getName());
		}
	}
}
