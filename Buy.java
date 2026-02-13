package main_container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Buy {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// Start payment process
	public void makePayment(double amount, Cart cart) throws IOException {
		System.out.println("\n💰 Total Amount to Pay: $" + amount);
		System.out.println("Select Payment Mode:");
		System.out.println("1. UPI");
		System.out.println("2. Card");
		System.out.print("Enter your choice: ");

		int choice = Integer.parseInt(br.readLine());

		switch (choice) {
		case 1 -> payWithUpi(amount, cart);
		case 2 -> payWithCard(amount, cart);
		default -> System.out.println("❌ Invalid payment option!");
		}
	}

	// UPI payment
	private void payWithUpi(double amount, Cart cart) throws IOException {
		System.out.println();
		System.out.print("Enter UPI ID: ");
		String upiId = br.readLine();
		System.out.println("Processing UPI payment...");
		System.out.println("✅ Payment Successful via UPI!");
		printBill(cart, amount, "upiId");
	}

	// Card payment
	private void payWithCard(double amount, Cart cart) throws IOException {
		System.out.println();
		System.out.print("Enter Card Number: ");
		String cardNo = br.readLine();

		// You can add validation later
		System.out.println("Processing Card payment...");
		System.out.println("✅ Payment Successful via Card!");
		printBill(cart, amount, "Card");
	}

	// Print final bill / invoice
	public void printBill(Cart cart, double amount, String paymentMode) {
		System.out.println("\n=======================================");
		System.out.println("           🧾 VAMSI MART BILL           ");
		System.out.println("=======================================");
		System.out.printf("%-5s %-20s %10s%n", "ID", "ITEM", "PRICE");
		System.out.println("---------------------------------------");

		for (Product p : cart.getItems()) {
			if (p != null) {
				System.out.printf("%-5d %-20s %10.2f%n", p.getId(), p.getName(), p.getPrice());
			}
		}

		System.out.println("---------------------------------------");
		System.out.printf("%-26s %10.2f%n", "TOTAL:", amount);
		System.out.println("Payment Mode : " + paymentMode);
		System.out.println("Status       : SUCCESS ✅");
		System.out.println("=======================================");
		System.out.println("      Thank you for shopping with us!");
		System.out.println("=======================================\n");
	}

}
