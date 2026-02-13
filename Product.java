package main_container;

public class Product {
	int Id;
	String name;
	double price;

	public Product(int id, String name, double price) {
		Id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

}
