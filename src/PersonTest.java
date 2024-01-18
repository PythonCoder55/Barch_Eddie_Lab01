import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void testFullName() {
        Person person = new Person("John", "Doe", "123456", "Mr.", 1985);
        assertEquals("John Doe", person.fullName());
    }

    @Test
    public void testFormalName() {
        Person person = new Person("John", "Doe", "123456", "Mr.", 1985);
        assertEquals("Mr. John Doe", person.formalName());
    }

    @Test
    public void testGetAge() {
        Person person = new Person("John", "Doe", "123456", "Mr.", 1985);
        int currentYear = 2024;
        assertEquals(currentYear - 1985, person.getAge());
    }

    @Test
    public void testGetAgeWithYear() {
        Person person = new Person("John", "Doe", "123456", "Mr.", 1985);
        int specificYear = 2000;
        assertEquals(specificYear - 1985, person.getAge(specificYear));
    }

    @Test
    public void testToCSVDataRecord() {
        Person person = new Person("John", "Doe", "123456", "Mr.", 1985);
        assertEquals("123456, John, Doe, Mr., 1985", person.toCSVDataRecord());
    }
}
