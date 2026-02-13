package main_container;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";
	public static final String PURPLE = "\u001B[35m";

	void start() throws IOException {
		Cart cart = new Cart(10);
		Buy buy = new Buy();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(BLUE + "Enter name : " + RESET);
		String name = br.readLine();
		System.out.println(GREEN + "Welcome to Vamsi Mart " + name + RESET);
		System.out.println(YELLOW + "Happy Shopping..." + RESET);

		boolean back = true;

		while (back) {
			System.out.println("--------------------------------------");
			BufferedReader homeReader = new BufferedReader(
					new FileReader("D:/Project2/Ecommerce_project1/HomePage.txt"));

			String line;
			while ((line = homeReader.readLine()) != null) {
				System.out.println(PURPLE + line + RESET);
			}
			homeReader.close();

			System.out.println(YELLOW + "6|Back" + RESET);
			System.out.println(YELLOW + "7|Show Cart" + RESET);
			System.out.println(YELLOW + "8|Buy" + RESET);

			System.out.print(BLUE + "Enter a choice: " + RESET);

			int choice = Integer.parseInt(br.readLine());

			if (choice == 6) {
				System.out.println("Thank you for shoping.");
				break;
			}
			if (choice == 7) {
				cart.showCart();
				continue;
			}
			if (choice == 8) {
				if (!cart.isEmpty()) {
					buy.makePayment(cart.getTotal(), cart);
				} else {
					System.out.println(RED + "Cart is empty,Select a product" + RESET);
				}
				continue;
			}
			String b = "";
			System.out.println("--------------------------------------");

			switch (choice) {
			case 1 -> b = "D:/Project2/Ecommerce_project1/BakeryProducts_subCat.txt";
			case 2 -> b = "D:/Project2/Ecommerce_project1/Electronics.txt";
			case 3 -> b = "D:/Project2/Ecommerce_project1/Fashion.txt";
			case 4 -> b = "D:/Project2/Ecommerce_project1/HouseHolds.txt";
			case 5 -> b = "D:/Project2/Ecommerce_project1/Cosmetics.txt";
			default -> {
				System.out.println(RED + " Invalid choice" + RESET);
				continue;
			}
			}
			String[] subCat = new String[10];
			int count1 = 0;
			try (BufferedReader subReader = new BufferedReader(new FileReader(b))) {
				String line1;
				while ((line1 = subReader.readLine()) != null) {
					System.out.println(GREEN + line1 + RESET);
					subCat[count1++] = line1.split("\\|")[1].trim();
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
			System.out.println(BLUE + "Choose one subCategory : " + RESET);
			int subChoice = Integer.parseInt(br.readLine());
			if (subChoice < 1 || subChoice > count1)
				continue;
			Product[] pro = new Product[10];
			String productFile = "D:/Project2/Ecommerce_project1/" + subCat[subChoice - 1] + ".txt";
			try (BufferedReader products = new BufferedReader(new FileReader(productFile))) {
				String product;
				int count = 0;
				while ((product = products.readLine()) != null) {
					String[] array = product.split("\\|");
					int id = Integer.parseInt(array[0].trim());
					String Pname = array[1].trim();
					String Pprice = array[2].replace("$", "").trim();
					double price = Double.parseDouble(Pprice);

					pro[count++] = new Product(id, Pname, price);
				}
			} catch (FileNotFoundException e) {
				System.out.println(RED + "File not found: " + b + RESET);
			}
			System.out.printf(YELLOW + "%-5s %-20s %10s%n", "ID", "NAME", "PRICE" + RESET);
			System.out.println("----------------------------------------");

			for (Product p : pro) {
				if (p != null)
					System.out.printf(CYAN + "%-5d %-20s %10.2f%n" + RESET, p.getId(), p.getName(), p.getPrice());
			}
			System.out.println("-----------Add to cart-----------");
			try {
				System.out.println(BLUE + "Enter the product id(1-10):" + RESET);
				int p = Integer.parseInt(br.readLine());
				cart.addToCart(pro[p - 1]);
				System.out.println(GREEN + "Added to cart successfully!" + RESET);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(RED + "Invalid product selected" + RESET);
			}
			System.out.println("--------------------------------------");
		}
	}
}