import org.junit.*;

import static org.junit.Assert.*;

import java.io.*;

public class AddressBookTest {
    private BuddyInfo buddy;
    @Before
    public void setUp() {
        buddy = new BuddyInfo("Jacob", "483 King St", 613);
    }

    @Test
    public void testSaveAddressBook() throws FileNotFoundException {
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy();
        File output = new File("output.txt");
        try {
            FileOutputStream ostream = new FileOutputStream(output, true);
            addressBook.saveAddressBook(ostream);
            ostream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File input = new File("output.txt");
        try {
            FileInputStream readFile = new FileInputStream(input);
            AddressBook newAddressBook = new AddressBook();
            AddressBook x = newAddressBook.importAddressBook(readFile);

            assertEquals(1, x.size());
            readFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSerialize() throws FileNotFoundException {
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy();
        addressBook.serialize(addressBook);
        File output = new File("output.ser");
        assertTrue(output.exists());
    }

    @Test
    public void testDeserialize() throws FileNotFoundException {
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy();
        addressBook.serialize(addressBook);  // Updated serialize method, see below
        AddressBook newAddressBook = new AddressBook();
        newAddressBook.deserialize();

        // Check the size of the newAddressBook
        assertEquals(addressBook.size(), newAddressBook.size());
    }



}