public class BuddyInfo {
    private String name;
    private String address;
    private int number;
    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Homer", "123 Pebble", 1000);
        System.out.println("Hello, " + buddy.getName());
    }

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



    @Override
    public String toString() {
        return "Buddy Name: " + name + " Address: " + address + " Number: " + number;
    }
}
