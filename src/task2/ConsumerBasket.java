package task2;

public class ConsumerBasket {

	private FoodItem[] food;

	public ConsumerBasket(int capacity) {
		if (capacity > 0) {
			food = new FoodItem[capacity];
		} else {
			System.out.println("ERROR");
			food = new FoodItem[10];
		}
	}
	
	public void addToTheBasket(FoodItem foodItem) {
		for (int i = 0; i < food.length; i++) {
			if (food[i] == null) {
				food[i] = foodItem;
				return;
			}
		}
		
		System.out.println("THE BASKET IS FULL");
	}
	
	public double totalPrice() {
		double total = 0;
		
		for (FoodItem item : food) {
			if (item != null) {
				total += item.getPrice();
			}
		}
		
		return total;
	}
	
	public FoodItem theMostExpensive() {
		FoodItem maxCostly = null;
		
		for (FoodItem item : food) {
			if (item != null) {
				if (maxCostly == null || item.getPrice() > maxCostly.getPrice()) {
					maxCostly = item;
				}
			}
		}
		
		return maxCostly;
	}
}
