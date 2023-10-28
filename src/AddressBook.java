import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.AWTEvent;

public class AddressBook extends BuddyInfo {
    /**
     * Create an AddressBook class that contains a
     * collection of BuddyInfo objects (the choice of
     * the collection class is entirely up to you). Provide
     * an addBuddy() and a removeBuddy() method
     */
    private ArrayList<BuddyInfo> addressBook;

    // GUI stuff

    JFrame frame = new JFrame("Lab 5 for SYSC 3110");
    frame.setSize(400, 300);


    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", 613);
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(0);
        // check if changes are being updated
    }
    public AddressBook() {
        addressBook = new ArrayList<>(); // restarts address book
    }

    public void addBuddy(BuddyInfo newBuddy) {
        if (newBuddy != null) {
            addressBook.add(newBuddy);
        }
    }

    public BuddyInfo removeBuddy(int index) {
        if (index >= 0 && index < addressBook.size()) {
            return addressBook.remove(index);
        }
        return null;
    }

    public void displayAddressBook() {
        for (BuddyInfo buddy: addressBook) {
            buddy.displayBuddy(buddy);
        }
    }


}
