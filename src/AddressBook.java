import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;


public class AddressBook extends DefaultListModel {

    private DefaultListModel<BuddyInfo> addressBook;
    private JList<BuddyInfo> buddyList;


    // GUI stuff
    public AddressBook()
    {
        addressBook = new DefaultListModel<>();
        buddyList = new JList<>();

        buddyList.setModel(addressBook);

        JFrame frame = new JFrame("Address Book");
        frame.setSize(500, 500);


        JMenuBar menu = new JMenuBar();

        JMenu addressBookMenu = new JMenu("AddressBook");
        JMenu buddyInfoMenu = new JMenu("BuddyInfo");


        // Menu Items
        JMenuItem newAddressBook = new JMenuItem("Create New AddressBook");
        newAddressBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewAddressBook();
            }
        });

        JMenuItem addBuddy = new JMenuItem("Add Buddy to AddressBook");
        addBuddy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBuddy();
            }
        });

        JMenuItem removeBuddy = new JMenuItem("Remove Buddy from AddressBook");
        removeBuddy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBuddy();
            }
        });




        addressBookMenu.add(newAddressBook);

        buddyInfoMenu.add(addBuddy);
        buddyInfoMenu.add(removeBuddy);

        menu.add(addressBookMenu);
        menu.add(buddyInfoMenu);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(buddyList));

        frame.setJMenuBar(menu);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setTitle("Address Book");
        frame.setResizable(true);
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddressBook addressBookMain = new AddressBook();
        });
    }

    public void addBuddy() {
        String buddyName = JOptionPane.showInputDialog("Please enter the name of the buddy you wish to add");
        String buddyAddress = JOptionPane.showInputDialog("Please enter the address of the buddy");
        String buddyNumber = JOptionPane.showInputDialog("Please enter the number of the buddy");

        int buddyPhoneNumber = Integer.parseInt(buddyNumber);

        // user types in a name
        BuddyInfo newBuddy = new BuddyInfo(buddyName, buddyAddress, buddyPhoneNumber);
        addressBook.addElement(newBuddy);

        System.out.println("Successfully added: " + buddyName);
    }

    public void removeBuddy() {
        String buddyName = JOptionPane.showInputDialog("Please enter the name of the buddy you wish to remove");

        if (buddyName != null) { // user types in a name
            // downsize since JList cannot remove
            DefaultListModel<BuddyInfo> tempBuddyList = (DefaultListModel<BuddyInfo>) buddyList.getModel();

            for (int i = 0; i < tempBuddyList.size(); i++) {
                if (tempBuddyList.getElementAt(i).getName().equals(buddyName)) {
                    addressBook.remove(i);
                    break; // only need to remove the first instance
                }
            }
        }

        System.out.println("Successfully removed: " + buddyName);
    }

    public void createNewAddressBook() { // wipes current address book
        addressBook.clear();
    }


}
