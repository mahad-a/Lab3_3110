import java.util.ArrayList;

public class AddressBook extends BuddyInfo {
    /**
     * Create an AddressBook class that contains a
     * collection of BuddyInfo objects (the choice of
     * the collection class is entirely up to you). Provide
     * an addBuddy() and a removeBuddy() method
     */
    private ArrayList<BuddyInfo> addressBook;

    public static void main(String[] args) {
        System.out.println("Address Book");
    }
    public AddressBook(BuddyInfo someBuddy) {
        this.addressBook = new ArrayList<BuddyInfo>(); // restarts address book
        addressBook.add(someBuddy);

    }

    public ArrayList<BuddyInfo> addBuddy(BuddyInfo newBuddy) {
        addressBook.add(newBuddy);
        return addressBook;
    }

    public ArrayList<BuddyInfo> removeBuddy(BuddyInfo delBuddy) {
        addressBook.remove(delBuddy);
        return addressBook;
    }


}
