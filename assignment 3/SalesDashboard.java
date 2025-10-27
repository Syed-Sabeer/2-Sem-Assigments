import java.io.*;
import java.util.*;

public class SalesDashboard {
    public static void main(String[] args) {
        String inputFile = "EVE01Sales.txt";
        List<String> columnHeaders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String headerRow = reader.readLine();
            if (headerRow == null) {
                System.err.println("File is empty!");
                return;
            }

            columnHeaders = Arrays.asList(headerRow.split("\t"));
            System.out.println("=== File Header Menu ===");
            for (int i = 0; i < columnHeaders.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, columnHeaders.get(i));
            }
            System.out.println("0. Exit");
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
            return;
        }

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
        } catch (IOException e) {
            System.err.println("Processing error: " + e.getMessage());
            return;
        }

        Scanner input = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. View Total Sales");
            System.out.println("2. View Sales by Representative");
            System.out.println("3. View Sales by Product");
            System.out.println("4. View File Header Menu");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\nTotal Sales: " + grandTotal);
                    break;
                case 2:
                    System.out.println("\nSales by Representative:");
                    repTotals.forEach((k, v) -> System.out.println(k + ": " + v));
                    break;
                case 3:
                    System.out.println("\nSales by Product:");
                    itemTotals.forEach((k, v) -> System.out.println(k + ": " + v));
                    break;
                case 4:
                    System.out.println("\n=== File Header Menu ===");
                    for (int i = 0; i < columnHeaders.size(); i++) {
                        System.out.printf("%d. %s%n", i + 1, columnHeaders.get(i));
                    }
                    System.out.println("0. Exit");
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 0);

        input.close();
    }
}
