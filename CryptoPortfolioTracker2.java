import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CryptoPortfolioTracker {

    // Simulated database of cryptocurrencies and their prices (in USD)
    private static final Map<String, Double> cryptoPrices = new HashMap<>();
    
    // User's portfolio holding
    private Map<String, Double> portfolio;

    public CryptoPortfolioTracker() {
        this.portfolio = new HashMap<>();
    }

    // Simulated function to get current price of cryptocurrency
    public static void initCryptoPrices() {
        // Adding simulated crypto prices (could be fetched via API in real implementation)
        cryptoPrices.put("Bitcoin", 35000.0);  // $35,000 per Bitcoin
        cryptoPrices.put("Ethereum", 2400.0);  // $2400 per Ethereum
        cryptoPrices.put("Ripple", 0.75);      // $0.75 per Ripple
        cryptoPrices.put("Litecoin", 150.0);   // $150 per Litecoin
    }

    // Add or remove cryptocurrency from the portfolio
    public void addOrRemoveCrypto(String crypto, double amount) {
        if (amount == 0) {
            portfolio.remove(crypto);
            System.out.println("Removed " + crypto + " from your portfolio.");
        } else {
            portfolio.put(crypto, portfolio.getOrDefault(crypto, 0.0) + amount);
            System.out.println((amount > 0 ? "Added" : "Removed") + " " + amount + " " + crypto + " to your portfolio.");
        }
    }

    // Get the total value of the portfolio
    public double getPortfolioValue() {
        double totalValue = 0.0;

        for (Map.Entry<String, Double> entry : portfolio.entrySet()) {
            String crypto = entry.getKey();
            double amount = entry.getValue();
            if (cryptoPrices.containsKey(crypto)) {
                double price = cryptoPrices.get(crypto);
                totalValue += price * amount;
            }
        }

        return totalValue;
    }

    // Display portfolio
    public void displayPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("Your portfolio is empty.");
            return;
        }

        System.out.println("Your Crypto Portfolio:");
        for (Map.Entry<String, Double> entry : portfolio.entrySet()) {
            String crypto = entry.getKey();
            double amount = entry.getValue();
            System.out.println(crypto + ": " + amount + " units | Price: $"
                    + cryptoPrices.get(crypto) + " | Total: $"
                    + (cryptoPrices.get(crypto) * amount));
        }

        System.out.println("Total Portfolio Value: $" + getPortfolioValue());
    }

    public static void main(String[] args) {
        initCryptoPrices();  // Initialize crypto prices
        CryptoPortfolioTracker tracker = new CryptoPortfolioTracker();
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add/Remove Cryptocurrency");
            System.out.println("2. View Portfolio");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter cryptocurrency (e.g., Bitcoin, Ethereum): ");
                    String crypto = scanner.nextLine();
                    System.out.print("Enter amount (use negative value to remove): ");
                    double amount = scanner.nextDouble();
                    tracker.addOrRemoveCrypto(crypto, amount);
                    break;
                case 2:
                    tracker.displayPortfolio();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
