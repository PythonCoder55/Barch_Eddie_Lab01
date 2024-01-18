import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> productsData = new ArrayList<>();

        do {
            String id = SafeInput.getRegExString(scanner, "Enter ID", "[A-Za-z0-9]+");
            String name = SafeInput.getNonZeroLenString(scanner, "Enter Name");
            String description = SafeInput.getNonZeroLenString(scanner, "Enter Description");
            double cost = SafeInput.getRangedDouble(scanner, "Enter Cost", 0, Double.MAX_VALUE);

            // Instantiate Product object and add to ArrayList
            Product product = new Product(name, description, id, cost);
            productsData.add(product);

        } while (SafeInput.getYNConfirm(scanner, "Do you want to enter another product? (Y/N)"));

        // Write all records to the text file
        saveToFile(productsData, scanner);

        System.out.println("Data saved to file. Program terminated.");
    }

    private static void saveToFile(ArrayList<Product> data, Scanner scanner) {
        String filename = SafeInput.getRegExString(scanner, "Enter the file name to save data", "[A-Za-z0-9]+\\.txt");

        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Product product : data) {
                // Use the toCSVDataRecord method to generate the CSV record
                writer.println(product.toCSVDataRecord());
            }
        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }
    }
}
