import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


public class AddressBook extends DefaultListModel {

    private DefaultListModel<BuddyInfo> addressBook;
    private JList<BuddyInfo> buddyList;
    private Random random;


    /* public AddressBook() {
        addressBook = new DefaultListModel<>(); // restarts address book
    } */

    // GUI stuff
    public AddressBook()
    {
        addressBook = new DefaultListModel<>();
        buddyList = new JList<>(addressBook);

        JFrame frame = new JFrame("Address Book");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension (500, 500));


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
        newAddressBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBuddy();
            }
        });

        JMenuItem removeBuddy = new JMenuItem("Remove Buddy from AddressBook");
        newAddressBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBuddy();
            }
        });

        JMenuItem displayAddressBook = new JMenuItem("Display Current AddressBook");
        newAddressBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAddressBook();
            }
        });



        addressBookMenu.add(newAddressBook);
        addressBookMenu.add(displayAddressBook);
        buddyInfoMenu.add(addBuddy);
        buddyInfoMenu.add(removeBuddy);

        menu.add(addressBookMenu);
        menu.add(buddyInfoMenu);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(buddyList), BorderLayout.CENTER);

        frame.setJMenuBar(menu);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setTitle("Address Book");
        frame.setResizable(true);
        frame.setVisible(true);

        displayAddressBook();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddressBook addressBookMain = new AddressBook();
        });
    }


    public void addBuddy() {
        displayAddressBook();
        String buddyName = JOptionPane.showInputDialog("Please enter the name of the buddy you wish to add");
        String buddyAddress = JOptionPane.showInputDialog("Please enter the address of the buddy");

        Random random = new Random(); // generate random number for temporary
        int buddyNumber = random.nextInt(1000);

        if (buddyName != null || buddyAddress != null) { // user types in a name
            new BuddyInfo(buddyName, buddyAddress, buddyNumber);
        }

        displayAddressBook();
        System.out.println("Successfully added: " + buddyName);
    }

    public void removeBuddy() {
        displayAddressBook();
        String buddyName = JOptionPane.showInputDialog("Please enter the name of the buddy you wish to remove");

        if (buddyName != null) { // user types in a name
            // downsize since JList cannot remove
            DefaultListModel<BuddyInfo> tempBuddyList = (DefaultListModel<BuddyInfo>) buddyList.getModel();

            for (int i = 0; i < tempBuddyList.size(); i++) {
                if (tempBuddyList.getElementAt(i).getName().equals(buddyName)) {
                    addressBook.remove(i);
                    tempBuddyList.remove(i);
                    break; // only need to remove the first instance
                }
            }
        }

        displayAddressBook();
        System.out.println("Successfully removed: " + buddyName);
    }

    public void displayAddressBook() {
        DefaultListModel<BuddyInfo> display = (DefaultListModel<BuddyInfo>) buddyList.getModel();

        for (int i = 0; i < addressBook.size(); i++) {
            display.addElement(addressBook.getElementAt(i));
        }
    }

    public void createNewAddressBook() { // wipes current address book
        addressBook.clear();
    }


}
