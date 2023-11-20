import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;


public class AddressBook extends DefaultListModel implements java.io.Serializable {

    private DefaultListModel<BuddyInfo> addressBook;
    private JList<BuddyInfo> buddyList;
    private FileOutputStream ostream;

    // GUI stuff
    public AddressBook() throws FileNotFoundException {
        addressBook = new DefaultListModel<>();
        buddyList = new JList<>();
        File output = new File("output.txt");
        FileOutputStream ostream = new FileOutputStream(output, true);

        buddyList.setModel(addressBook);

        JFrame frame = new JFrame("Address Book");
        frame.setSize(500, 500);


        JMenuBar menu = new JMenuBar();

        JMenu addressBookMenu = new JMenu("AddressBook");
        JMenu buddyInfoMenu = new JMenu("BuddyInfo");
        JMenu exportMenu = new JMenu("Export");
        JMenu importMenu = new JMenu("Import");
        JMenu serializationMenu = new JMenu("Serialization");


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
                saveAddressBook(ostream);
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
                saveAddressBook(newFile);

            }
        });

        JMenuItem importBook = new JMenuItem("Import Address Book");
        importBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object askFile = JOptionPane.showInputDialog(null, "Type in the name of the file.", "File to Export to", 3);
                FileInputStream newFile;
                try {
                    newFile = new FileInputStream(askFile + ".txt");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                importAddressBook(newFile);
            }
        });

        JMenuItem serialization = new JMenu("Serialize");
        serialization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serialize(AddressBook.this);
            }
        });
        JMenuItem deserialization = new JMenu("Deserialize");
        deserialization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deserialize();
            }
        });

        addressBookMenu.add(newAddressBook);

        buddyInfoMenu.add(addBuddy);
        buddyInfoMenu.add(removeBuddy);

        serializationMenu.add(serialization);
        serializationMenu.add(deserialization);

        exportMenu.add(exportPage);
        importMenu.add(importBook);

        menu.add(addressBookMenu);
        menu.add(buddyInfoMenu);
        menu.add(exportMenu);
        menu.add(serializationMenu);

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


    public void saveAddressBook(FileOutputStream userFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(userFile.getFD()))) {
            for (int i = 0; i < addressBook.size(); i++) {
                writer.println(addressBook.get(i).toString());
                System.out.println(addressBook.get(i).toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AddressBook importAddressBook(FileInputStream readFile) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(readFile))) {
            AddressBook newAddressBook = new AddressBook();
            while (reader.ready()) {
                String buddyInfoString = reader.readLine();
                System.out.println("Read line: " + buddyInfoString);

                BuddyInfo buddy = BuddyInfo.importBuddyInfo(buddyInfoString);
                System.out.println("Imported BuddyInfo: " + buddy);

                newAddressBook.addElement(buddy);
            }
            System.out.println(newAddressBook);
            return newAddressBook;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void serialize(AddressBook addressBook){
        try {
            FileOutputStream fileOut = new FileOutputStream("output.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(addressBook.toString());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public AddressBook deserialize() {
        AddressBook addressBook = null;
        try {
            FileInputStream fileIn = new FileInputStream("output.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Check if the object is an instance of AddressBook
            Object obj = in.readObject();
            if (obj instanceof AddressBook) {
                addressBook = (AddressBook) obj;
            } else {
                System.err.println("Unexpected object type in the serialized file.");
            }

            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return addressBook;
    }


}
