import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Read data from the file and store Product objects in an ArrayList
            ArrayList<Product> productsData = readFromFile(String.valueOf(selectedFile));

            // Display the table of Product data generated from the ArrayList
            displayProductsTable(productsData);
        } else {
            System.out.println("File selection canceled.");
        }
    }

    private static ArrayList<Product> readFromFile(String filePath) {
        ArrayList<Product> productsData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Use String split function to separate fields into an array
                String[] parts = line.split(", ");

                // Create Product object and add to ArrayList
                Product product = new Product(parts[1], parts[2], parts[0], Double.parseDouble(parts[3]));
                productsData.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return productsData;
    }

    private static void displayProductsTable(ArrayList<Product> productsData) {
        System.out.println("ID            Name          Description                Cost");
        System.out.println("==========================================================");

        for (Product product : productsData) {
            String formattedLine = String.format("%-14s%-13s%-25s%-8.2f",
                    product.getID(), product.getName(), product.getDescription(), product.getCost());
            System.out.println(formattedLine);
        }
    }
}