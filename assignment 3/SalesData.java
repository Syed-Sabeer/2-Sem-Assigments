import java.io.*;
import java.util.*;

public class SalesReport {
    public static void main(String[] args) {
        String inputFile = "EVE01Sales.txt";

        double grandTotal = 0.0;
        Map<String, Double> repTotals = new HashMap<>();
        Map<String, Double> itemTotals = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            reader.readLine();

            String record;
            while ((record = reader.readLine()) != null) {
                String[] fields = record.split("\t");
                if (fields.length < 6) continue;

                String repCode = fields[2].trim();
                String itemName = fields[3].trim();
                int quantity = Integer.parseInt(fields[4].trim());
                double price = Double.parseDouble(fields[5].trim());

                double amount = quantity * price;
                grandTotal += amount;

                repTotals.put(repCode, repTotals.getOrDefault(repCode, 0.0) + amount);
                itemTotals.put(itemName, itemTotals.getOrDefault(itemName, 0.0) + amount);
            }

            System.out.println("=== Sales Report ===");
            System.out.println("Total Sales: " + grandTotal);

            System.out.println("\nSales by Representative:");
            for (Map.Entry<String, Double> entry : repTotals.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("\nSales by Product:");
            for (Map.Entry<String, Double> entry : itemTotals.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid data: " + e.getMessage());
        }
    }
}
