import java.io.Serializable;

public class BuddyInfo implements Serializable {
    private String name;
    private String address;
    private int number;
    /*public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Homer", "123 Pebble", 1000);
        System.out.println("Hello, " + buddy.getName());
    } */

    public BuddyInfo() {
    }

    public BuddyInfo(String name, String address, int number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getNumber() {
        return number;
    }

    public static BuddyInfo importBuddyInfo(String buddy){
        String[] words = buddy.split("#");
        BuddyInfo importedBuddy = new BuddyInfo(words[0], words[1], Integer.parseInt(words[2]));
        return importedBuddy;
    }

    @Override
    public String toString() {
        return "Mr." + name + "#" + address + "#" + number;
    }
}
