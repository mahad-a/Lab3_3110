import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;


public class AddressBook extends DefaultListModel {

    private DefaultListModel<BuddyInfo> addressBook;
    private JList<BuddyInfo> buddyList;
    private FileOutputStream ostream;

    // GUI stuff
    public AddressBook() throws FileNotFoundException {
        addressBook = new DefaultListModel<>();
        buddyList = new JList<>();
        FileOutputStream ostream = new FileOutputStream("output.txt");

        buddyList.setModel(addressBook);

        JFrame frame = new JFrame("Address Book");
        frame.setSize(500, 500);


        JMenuBar menu = new JMenuBar();

        JMenu addressBookMenu = new JMenu("AddressBook");
        JMenu buddyInfoMenu = new JMenu("BuddyInfo");
        JMenu exportMenu = new JMenu("Export");


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
                try {
                    saveAddressBook(ostream);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JMenuItem removeBuddy = new JMenuItem("Remove Buddy from AddressBook");
        removeBuddy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBuddy();
            }
        });

        JMenuItem exportPage = new JMenuItem(("Export Address Book"));
        exportPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object askFile = JOptionPane.showInputDialog(null, "Type in the name of the file.", "File to Export to", 3);
                FileOutputStream newFile;
                try {
                    newFile = new FileOutputStream(askFile + ".txt");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    saveAddressBook(newFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addressBookMenu.add(newAddressBook);

        buddyInfoMenu.add(addBuddy);
        buddyInfoMenu.add(removeBuddy);

        exportMenu.add(exportPage);

        menu.add(addressBookMenu);
        menu.add(buddyInfoMenu);
        menu.add(exportMenu);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(buddyList));

        frame.setJMenuBar(menu);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setTitle("Address Book");
        frame.setResizable(true);
        frame.setVisible(true);

    }

    public DefaultListModel<BuddyInfo> getAddressBook() {
        return addressBook;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                AddressBook addressBookMain = new AddressBook();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
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


    public void saveAddressBook(FileOutputStream ostream) throws IOException {
        ObjectOutputStream saveBook;
        try {
            saveBook = new ObjectOutputStream(ostream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < addressBook.size(); i++){
            saveBook.writeObject(addressBook.get(i));
            System.out.println(addressBook.get(i));
        }
        //ostream.close();
    }


}
