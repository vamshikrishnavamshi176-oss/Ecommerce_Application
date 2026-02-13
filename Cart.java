package main_container;

public class Cart {

	private Product[] items;
	private int size;

	// Constructor
	public Cart(int capacity) {
		items = new Product[capacity];
		size = 0;
	}

	// ➕ Add product to cart
	public void addToCart(Product p) {
		if (size < items.length) {
			items[size++] = p;
			System.out.println("✅ Added to cart: " + p.getName());
		} else {
			System.out.println("❌ Cart is full!");
		}
	}

	// 🧮 Calculate total
	public double getTotal() {
		double total = 0;
		for (int i = 0; i < size; i++) {
			total += items[i].getPrice();
		}
		return total;
	}

	// 🖨 Display cart
	public void showCart() {
		if (isEmpty()) {
			System.out.println("🛒 Your cart is empty!");
			return;
		}

		System.out.println("\n🛒 Your Cart");
		System.out.printf("%-5s %-20s %10s%n", "ID", "NAME", "PRICE");
		System.out.println("----------------------------------------");

		for (int i = 0; i < size; i++) {
			Product p = items[i];
			System.out.printf("%-5d %-20s %10.2f%n", p.getId(), p.getName(), p.getPrice());
		}

		System.out.println("----------------------------------------");
		System.out.printf("TOTAL: %29.2f%n", getTotal());
	}

	// 🔎 Check if empty
	public boolean isEmpty() {
		return size == 0;
	}

	public Product[] getItems() {
		// TODO Auto-generated method stub
		return items;
	}
}
