import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> personsData = new ArrayList<>();

        do {
            String id = SafeInput.getRegExString(scanner, "Enter ID", "\\d{6}");
            String firstName = SafeInput.getRegExString(scanner, "Enter First Name", "[A-Za-z]+");
            String lastName = SafeInput.getRegExString(scanner, "Enter Last Name", "[A-Za-z]+");
            String title = SafeInput.getNonZeroLenString(scanner, "Enter Title");
            int yearOfBirth = SafeInput.getInt(scanner, "Enter Year of Birth");

            // Instantiate Person object and add to ArrayList
            Person person = new Person(firstName, lastName, id, title, yearOfBirth);
            personsData.add(person);

        } while (SafeInput.getYNConfirm(scanner, "Do you want to enter another person? (Y/N)"));

        // Write all records to the text file
        saveToFile(personsData, scanner);

        System.out.println("Data saved to file. Program terminated.");
    }

    private static void saveToFile(ArrayList<Person> data, Scanner scanner) {
        String filename = SafeInput.getRegExString(scanner, "Enter the file name to save data", "[A-Za-z0-9]+\\.txt");

        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Person person : data) {
                // Use the toCSVDataRecord method to generate the CSV record
                writer.println(person.toCSVDataRecord());
            }
        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }
    }
}
