import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Read data from the file and store Person objects in an ArrayList
            ArrayList<Person> personsData = readFromFile(String.valueOf(selectedFile));

            // Display the table of Person data generated from the ArrayList
            displayPersonsTable(personsData);
        } else {
            System.out.println("File selection canceled.");
        }
    }

    private static ArrayList<Person> readFromFile(String filePath) {
        ArrayList<Person> personsData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Use String split function to separate fields into an array
                String[] parts = line.split(", ");

                // Create Person object and add to ArrayList
                Person person = new Person(parts[1], parts[2], parts[0], parts[3], Integer.parseInt(parts[4]));
                personsData.add(person);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return personsData;
    }

    private static void displayPersonsTable(ArrayList<Person> personsData) {
        System.out.println("ID#           Firstname     Lastname       Title    YOB");
        System.out.println("========================================================");

        for (Person person : personsData) {
            String formattedLine = String.format("%-14s%-14s%-15s%-9s%-4s",
                    person.getID(), person.getFirstName(), person.getLastName(),
                    person.getTitle(), person.getYOB());
            System.out.println(formattedLine);
        }
    }
}
