import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.AWTEvent;
import java.util.*;
import java.awt.event.*;


public class AddressBook extends BuddyInfo {
    /**
     * Create an AddressBook class that contains a
     * collection of BuddyInfo objects (the choice of
     * the collection class is entirely up to you). Provide
     * an addBuddy() and a removeBuddy() method
     */
    private ArrayList<BuddyInfo> addressBook;
    private JMenuItem quitItem;

    // GUI stuff
    public TicTacToeGUI()
    {
        JFrame frame = new JFrame("Tic Tac Toe");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension (500, 500));

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu fileMenu = new JMenu("Options");
        menubar.add(fileMenu);
        fileMenu.addMouseListener(this);

        quitItem = new JMenuItem("Quit Game");
        fileMenu.add(quitItem);

        quitItem.addActionListener(new ActionListener() // create an anonymous inner class
                                   { // start of anonymous subclass of ActionListener
                                       // this allows us to put the code for this action here
                                       public void actionPerformed(ActionEvent event)
                                       {
                                           System.exit(0); // quit
                                       }
                                   } // end of anonymous subclass
        );


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }


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
